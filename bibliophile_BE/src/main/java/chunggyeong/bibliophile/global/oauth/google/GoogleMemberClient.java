package chunggyeong.bibliophile.global.oauth.google;

import chunggyeong.bibliophile.domain.oauth.client.OauthMemberClient;
import chunggyeong.bibliophile.domain.oauth.domain.OauthMember;
import chunggyeong.bibliophile.domain.oauth.domain.OauthServerType;
import chunggyeong.bibliophile.global.oauth.google.client.GoogleApiClient;
import chunggyeong.bibliophile.global.oauth.google.dto.GoogleMemberResponse;
import chunggyeong.bibliophile.global.oauth.google.dto.GoogleToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class GoogleMemberClient implements OauthMemberClient {

    private final GoogleApiClient googleApiClient;
    private final GoogleOauthConfig googleOauthConfig;

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.GOOGLE;
    }

    @Override
    public OauthMember fetch(String authCode) {
        String code = URLDecoder.decode(authCode, StandardCharsets.UTF_8);
        GoogleToken tokenInfo = googleApiClient.fetchToken(tokenRequestParams(code));
        GoogleMemberResponse googleMemberResponse = googleApiClient.fetchMember("Bearer " + tokenInfo.accessToken());
        return googleMemberResponse.toDomain();
    }

    private MultiValueMap<String, String> tokenRequestParams(String authCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", authCode);
        params.add("client_id", googleOauthConfig.clientId());
        params.add("client_secret", googleOauthConfig.clientSecret());
        params.add("redirect_uri", googleOauthConfig.redirectUri());
        params.add("grant_type", "authorization_code");
        return params;
    }
}
