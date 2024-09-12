package chunggyeong.bibliophile.domain.oauth.exception;


import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class OauthMemberNotFoundException extends MainException {

    public static final MainException EXCEPTION = new OauthMemberNotFoundException();

    private OauthMemberNotFoundException() {
        super(ErrorCode.OAUTH_MEMBER_NOT_FOUND);
    }
}
