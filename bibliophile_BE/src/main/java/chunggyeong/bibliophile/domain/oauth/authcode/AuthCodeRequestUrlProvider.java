package chunggyeong.bibliophile.domain.oauth.authcode;


import chunggyeong.bibliophile.domain.oauth.domain.OauthServerType;

public interface AuthCodeRequestUrlProvider {

    OauthServerType supportServer();

    String provide();
}
