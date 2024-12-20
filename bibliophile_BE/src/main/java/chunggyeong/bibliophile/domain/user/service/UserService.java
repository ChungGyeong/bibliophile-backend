package chunggyeong.bibliophile.domain.user.service;

import chunggyeong.bibliophile.domain.fox.domain.Fox;
import chunggyeong.bibliophile.domain.fox.domain.repository.FoxRepository;
import chunggyeong.bibliophile.domain.interest.domain.Classification;
import chunggyeong.bibliophile.domain.interest.exception.DuplicateClassificationException;
import chunggyeong.bibliophile.domain.interest.exception.InterestLimitExceededException;
import chunggyeong.bibliophile.domain.interest.service.InterestServiceUtils;
import chunggyeong.bibliophile.domain.oauth.service.OauthServiceUtils;
import chunggyeong.bibliophile.domain.user.domain.RefreshToken;
import chunggyeong.bibliophile.domain.user.domain.User;
import chunggyeong.bibliophile.domain.user.domain.repository.RefreshTokenRepository;
import chunggyeong.bibliophile.domain.user.domain.repository.UserRepository;
import chunggyeong.bibliophile.domain.user.exception.NicknameDuplicationException;
import chunggyeong.bibliophile.domain.user.exception.UserDuplicationException;
import chunggyeong.bibliophile.domain.user.presentation.dto.request.SignUpUserRequest;
import chunggyeong.bibliophile.domain.user.presentation.dto.request.UpdateUserRequest;
import chunggyeong.bibliophile.domain.user.presentation.dto.response.UserProfileResponse;
import chunggyeong.bibliophile.domain.user.presentation.dto.response.UserWordCloudResponse;
import chunggyeong.bibliophile.global.security.JwtTokenProvider;
import chunggyeong.bibliophile.global.utils.security.SecurityUtils;
import chunggyeong.bibliophile.global.utils.user.UserUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final InterestServiceUtils interestServiceUtils;
    private final UserUtils userUtils;
    private final OauthServiceUtils oauthServiceUtils;
    private final FoxRepository foxRepository;

    // 회원 가입
    @Transactional
    public UserProfileResponse signUp(SignUpUserRequest signUpUserRequest, HttpServletResponse response) {
        User user = User.createUser(
                signUpUserRequest.nickname(),
                signUpUserRequest.email(),
                signUpUserRequest.profileImage(),
                signUpUserRequest.birthday(),
                signUpUserRequest.gender(),
                signUpUserRequest.oauthServerType()
        );

        List<Classification> classificationList = signUpUserRequest.classification();

        validateSignUpRequest(signUpUserRequest, classificationList);

        userRepository.save(user);

        addClassification(classificationList, user);

        List<String> classificationStringList = convertClassificationToStringList(classificationList);

        Fox fox = Fox.createFox(user);
        foxRepository.save(fox);

        String accessToken = jwtTokenProvider.generateAccessToken(user.getId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        refreshTokenRepository.save(new RefreshToken(refreshToken, user.getId()));

        jwtTokenProvider.setHeaderRefreshToken(response, refreshToken);
        jwtTokenProvider.setHeaderAccessToken(response, accessToken);

        return new UserProfileResponse(user, classificationStringList);
    }

    // 로그아웃
    @Transactional
    public void logout(HttpServletResponse response) {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        refreshTokenRepository.deleteByUserId(currentUserId);

        jwtTokenProvider.setHeaderAccessTokenEmpty(response);
        jwtTokenProvider.setHeaderRefreshTokenEmpty(response);
    }

    // 회원 정보 조회
    public UserProfileResponse findUser() {
        User user = userUtils.getUserFromSecurityContext();

        List<Classification> classificationList = interestServiceUtils.findInterestsByUser(user);
        List<String> classificationStringList = convertClassificationToStringList(classificationList);

        return new UserProfileResponse(user, classificationStringList);
    }

    //회원 정보 수정
    @Transactional
    public UserProfileResponse updateUser(UpdateUserRequest updateUserRequest) {
        User user = userUtils.getUserFromSecurityContext();
        List<Classification> classificationList = interestServiceUtils.findInterestsByUser(user);

        user.updateUser(updateUserRequest.nickname(), updateUserRequest.profileImage());
        interestServiceUtils.deleteAllByUser(user);
        addClassification(updateUserRequest.classification(), user);

        List<Classification> updateClassificationList = interestServiceUtils.findInterestsByUser(user);
        List<String> classificationStringList = convertClassificationToStringList(updateClassificationList);

        return new UserProfileResponse(user, classificationStringList);
    }

    // 회원 탈퇴
    @Transactional
    public void withdraw(HttpServletResponse response) {
        User user = userUtils.getUserFromSecurityContext();

        userRepository.delete(user);

        oauthServiceUtils.deleteByUser(user);

        refreshTokenRepository.deleteByUserId(user.getId());

        jwtTokenProvider.setHeaderAccessTokenEmpty(response);
        jwtTokenProvider.setHeaderRefreshTokenEmpty(response);
    }

    // 워드클라우드 조회
    public UserWordCloudResponse getWordCloud() {
        User user = userUtils.getUserFromSecurityContext();

        return new UserWordCloudResponse(user.getWordCloudImgUrl());
    }

    // 회원가입 시 검증
    private void validateSignUpRequest(SignUpUserRequest signUpUserRequest, List<Classification> classificationList) {
        if (userRepository.existsByEmailAndOauthServerType(signUpUserRequest.email(), signUpUserRequest.oauthServerType())) {
            throw UserDuplicationException.EXCEPTION;
        }

        if (userRepository.existsByNickname(signUpUserRequest.nickname())) {
            throw NicknameDuplicationException.EXCEPTION;
        }

        boolean hasDuplicates = classificationList.size() != new HashSet<>(classificationList).size();

        if (hasDuplicates) {
            throw DuplicateClassificationException.EXCEPTION;
        }
    }

    // 관심사 추가시 검증
    private void addClassification(List<Classification> classificationList, User user) {
        if (4 <= classificationList.size()) {
            throw InterestLimitExceededException.EXCEPTION;
        }

        classificationList.forEach(classification ->
                interestServiceUtils.addInterest(user, classification)
        );
    }

    // 관심사 타입 변경
    private List<String> convertClassificationToStringList(List<Classification> classificationList) {
        return classificationList.stream()
                .map(Classification::getValue)
                .collect(Collectors.toList());
    }
}
