package chunggyeong.bibliophile.domain.oauth.domain.vo;


import chunggyeong.bibliophile.domain.oauth.domain.OauthServerType;

public record OauthMemberInfoVO(
        String email,
        String name,
        OauthServerType oauthServerType
) {
}
