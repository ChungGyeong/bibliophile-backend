package chunggyeong.bibliophile.domain.oauth.presentation.dto.response;


import chunggyeong.bibliophile.domain.oauth.domain.OauthMember;
import chunggyeong.bibliophile.domain.oauth.domain.OauthServerType;

public record OauthLoginResponse(
        String email,
        String name,
        OauthServerType oauthServerType,
        Boolean isFirst
) {
    public OauthLoginResponse (OauthMember oauthMember, Boolean isFirst) {
        this(oauthMember.getEmail(), oauthMember.getName(), oauthMember.getOauthServerType(), isFirst);
    }
}
