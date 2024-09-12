package chunggyeong.bibliophile.domain.oauth.service;

import chunggyeong.bibliophile.domain.oauth.authcode.AuthCodeRequestUrlProviderComposite;
import chunggyeong.bibliophile.domain.oauth.client.OauthMemberClientComposite;
import chunggyeong.bibliophile.domain.oauth.domain.OauthMember;
import chunggyeong.bibliophile.domain.oauth.domain.OauthServerType;
import chunggyeong.bibliophile.domain.oauth.domain.repository.OauthMemberRepository;
import chunggyeong.bibliophile.domain.oauth.presentation.dto.response.OauthLoginResponse;
import chunggyeong.bibliophile.domain.user.domain.RefreshToken;
import chunggyeong.bibliophile.domain.user.domain.User;
import chunggyeong.bibliophile.domain.user.domain.repository.RefreshTokenRepository;
import chunggyeong.bibliophile.domain.user.domain.repository.UserRepository;
import chunggyeong.bibliophile.global.exception.InvalidTokenException;
import chunggyeong.bibliophile.global.exception.RefreshTokenExpiredException;
import chunggyeong.bibliophile.global.security.JwtTokenProvider;
import chunggyeong.bibliophile.global.utils.user.UserUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OauthService {

    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final UserRepository userRepository;
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final OauthMemberRepository oauthMemberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserUtils userUtils;

    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }

    public OauthLoginResponse signIn(OauthServerType oauthServerType, String authCode, HttpServletResponse response) {
        OauthMember oauthMember = oauthMemberClientComposite.fetch(oauthServerType, authCode);
        OauthMember saved = oauthMemberRepository.findByOauthServerTypeAndEmail(oauthMember.getOauthServerType(), oauthMember.getEmail())
                .orElseGet(() -> oauthMemberRepository.save(oauthMember));
        Optional<User> savedUser = userRepository.findByOauthServerTypeAndEmail(saved.getOauthServerType(), saved.getEmail());

        if (!savedUser.isPresent()) {
            return new OauthLoginResponse(saved, true);
        }

        String accessToken = jwtTokenProvider.generateAccessToken(savedUser.get().getId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(savedUser.get().getId());

        refreshTokenRepository.findByUserId(savedUser.get().getId())
                .ifPresent(refreshTokenRepository::delete);

        refreshTokenRepository.save(new RefreshToken(refreshToken, savedUser.get().getId()));

        jwtTokenProvider.setHeaderAccessToken(response, accessToken);
        jwtTokenProvider.setHeaderRefreshToken(response, refreshToken);

        return new OauthLoginResponse(saved, false);
    }

    @Transactional
    public void tokenRefresh(HttpServletResponse response, String requestRefreshToken) {
        RefreshToken getRefreshToken = refreshTokenRepository.findByRefreshToken(requestRefreshToken).orElseThrow(() -> RefreshTokenExpiredException.EXCEPTION);

        Long userId = jwtTokenProvider.parseRefreshToken(requestRefreshToken);

        if (!userId.equals(getRefreshToken.getUserId())) {
            throw InvalidTokenException.EXCEPTION;
        }

        User user = userUtils.getUserById(userId);

        String accessToken = jwtTokenProvider.generateAccessToken(user.getId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        refreshTokenRepository.deleteByRefreshToken(requestRefreshToken);
        refreshTokenRepository.save(new RefreshToken(refreshToken, userId));

        jwtTokenProvider.setHeaderAccessToken(response, accessToken);
        jwtTokenProvider.setHeaderRefreshToken(response, refreshToken);

    }
}
