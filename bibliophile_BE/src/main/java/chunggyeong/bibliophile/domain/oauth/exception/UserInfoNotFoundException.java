package chunggyeong.bibliophile.domain.oauth.exception;


import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class UserInfoNotFoundException extends MainException {

    public static final MainException EXCEPTION = new UserInfoNotFoundException();

    private UserInfoNotFoundException() {
        super(ErrorCode.USER_INFO_NOT_FOUND);
    }
}
