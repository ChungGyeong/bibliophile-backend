package chunggyeong.bibliophile.domain.myBook.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class UserNotMyBookHostException extends MainException {

    public static final MainException EXCEPTION = new UserNotMyBookHostException();

    private UserNotMyBookHostException() {
        super(ErrorCode.USER_NOT_STORYBOOK_HOST);
    }
}
