package chunggyeong.bibliophile.domain.user.service;

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
import chunggyeong.bibliophile.domain.user.presentation.dto.request.CheckNicknameRequest;
import chunggyeong.bibliophile.domain.user.presentation.dto.request.SignUpUserRequest;
import chunggyeong.bibliophile.domain.user.presentation.dto.request.UpdateUserRequest;
import chunggyeong.bibliophile.domain.user.presentation.dto.response.CheckNicknameResponse;
import chunggyeong.bibliophile.domain.user.presentation.dto.response.UserProfileResponse;
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

        String accessToken = jwtTokenProvider.generateAccessToken(user.getId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        refreshTokenRepository.save(new RefreshToken(refreshToken, user.getId()));

        jwtTokenProvider.setHeaderRefreshToken(response, refreshToken);
        jwtTokenProvider.setHeaderAccessToken(response, accessToken);

        return new UserProfileResponse(user, classificationList);
    }


    // 닉네임 중복 확인
    public CheckNicknameResponse checkNickname(CheckNicknameRequest checkNicknameRequest) {
        return new CheckNicknameResponse(userRepository.existsByNickname(checkNicknameRequest.nickname()));
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

        return new UserProfileResponse(user, classificationList);
    }

    //회원 정보 수정
    @Transactional
    public UserProfileResponse updateUser(UpdateUserRequest updateUserRequest) {
        User user = userUtils.getUserFromSecurityContext();
        List<Classification> classificationList = interestServiceUtils.findInterestsByUser(user);

        if (userRepository.existsByNickname(updateUserRequest.nickname())) {
            throw NicknameDuplicationException.EXCEPTION;
        }

        user.updateUser(updateUserRequest.nickname(), updateUserRequest.profileImage());
        interestServiceUtils.deleteAllByUser(user);
        addClassification(updateUserRequest.classification(), user);

        List<Classification> updateClassificationList = interestServiceUtils.findInterestsByUser(user);

        return new UserProfileResponse(user, updateClassificationList);
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
}
