package chunggyeong.bibliophile.domain.user.service;

import chunggyeong.bibliophile.domain.interest.domain.Classification;
import chunggyeong.bibliophile.domain.interest.exception.DuplicateClassificationException;
import chunggyeong.bibliophile.domain.interest.exception.InterestLimitExceededException;
import chunggyeong.bibliophile.domain.interest.service.InterestServiceUtils;
import chunggyeong.bibliophile.domain.user.domain.RefreshToken;
import chunggyeong.bibliophile.domain.user.domain.User;
import chunggyeong.bibliophile.domain.user.domain.repository.RefreshTokenRepository;
import chunggyeong.bibliophile.domain.user.domain.repository.UserRepository;
import chunggyeong.bibliophile.domain.user.exception.NicknameDuplicationException;
import chunggyeong.bibliophile.domain.user.exception.UserDuplicationException;
import chunggyeong.bibliophile.domain.user.presentation.dto.request.CheckNicknameRequest;
import chunggyeong.bibliophile.domain.user.presentation.dto.request.SignUpUserRequest;
import chunggyeong.bibliophile.domain.user.presentation.dto.response.CheckNicknameResponse;
import chunggyeong.bibliophile.domain.user.presentation.dto.response.UserProfileResponse;
import chunggyeong.bibliophile.global.security.JwtTokenProvider;
import chunggyeong.bibliophile.global.utils.security.SecurityUtils;
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

        classificationList.forEach(classification ->
                interestServiceUtils.addInterest(user, classification)
        );

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

    // 회원가입 시 검증
    private void validateSignUpRequest(SignUpUserRequest signUpUserRequest, List<Classification> classificationList) {
        if (userRepository.existsByEmailAndOauthServerType(signUpUserRequest.email(), signUpUserRequest.oauthServerType())) {
            throw UserDuplicationException.EXCEPTION;
        }

        if (userRepository.existsByNickname(signUpUserRequest.nickname())) {
            throw NicknameDuplicationException.EXCEPTION;
        }

        if (4 <= classificationList.size()) {
            throw InterestLimitExceededException.EXCEPTION;
        }

        boolean hasDuplicates = classificationList.size() != new HashSet<>(classificationList).size();

        if (hasDuplicates) {
            throw DuplicateClassificationException.EXCEPTION;
        }
    }
}
