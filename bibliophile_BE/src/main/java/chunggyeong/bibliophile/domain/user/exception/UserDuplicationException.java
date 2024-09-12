package chunggyeong.bibliophile.domain.user.exception;


import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class UserDuplicationException extends MainException {

    public static final MainException EXCEPTION = new UserDuplicationException();

    private UserDuplicationException() {
        super(ErrorCode.USER_DUPLICATION);
    }
}
