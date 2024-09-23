package chunggyeong.bibliophile.domain.bookmark.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class UserNotBookmarkHostException extends MainException {

    public static final MainException EXCEPTION = new UserNotBookmarkHostException();

    private UserNotBookmarkHostException() {
        super(ErrorCode.USER_NOT_STORYBOOK_HOST);
    }
}
