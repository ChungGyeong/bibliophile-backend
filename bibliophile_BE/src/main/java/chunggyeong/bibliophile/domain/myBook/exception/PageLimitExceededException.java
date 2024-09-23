package chunggyeong.bibliophile.domain.myBook.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class PageLimitExceededException extends MainException {

    public static final MainException EXCEPTION = new PageLimitExceededException();

    private PageLimitExceededException() {
        super(ErrorCode.PAGE_LIMIT_EXCEEDED);
    }
}
