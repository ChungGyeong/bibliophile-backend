package chunggyeong.bibliophile.global.oauth.naver;

import chunggyeong.bibliophile.domain.oauth.client.OauthMemberClient;
import chunggyeong.bibliophile.domain.oauth.domain.OauthMember;
import chunggyeong.bibliophile.domain.oauth.domain.OauthServerType;
import chunggyeong.bibliophile.global.oauth.naver.client.NaverApiClient;
import chunggyeong.bibliophile.global.oauth.naver.dto.NaverMemberResponse;
import chunggyeong.bibliophile.global.oauth.naver.dto.NaverToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
@Slf4j
public class NaverMemberClient implements OauthMemberClient {
    private final NaverApiClient naverApiClient;
    private final NaverOauthConfig naverOauthConfig;

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.NAVER;
    }

    @Override
    public OauthMember fetch(String authCode) {
        NaverToken tokenInfo = naverApiClient.fetchToken(tokenRequestParams(authCode));
        NaverMemberResponse naverMemberResponse = naverApiClient.fetchMember("Bearer " + tokenInfo.accessToken());
        return naverMemberResponse.toDomain();
    }

    private MultiValueMap<String, String> tokenRequestParams(String authCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", naverOauthConfig.clientId());
        params.add("client_secret", naverOauthConfig.clientSecret());
        params.add("code", authCode);
        params.add("state", naverOauthConfig.state());
        return params;
    }
}
