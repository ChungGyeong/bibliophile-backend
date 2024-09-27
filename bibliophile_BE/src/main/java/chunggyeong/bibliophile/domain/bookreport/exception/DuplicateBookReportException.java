package chunggyeong.bibliophile.domain.bookreport.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class DuplicateBookReportException extends MainException {
    public static final MainException EXCEPTION = new DuplicateBookReportException();

    private DuplicateBookReportException() {
        super(ErrorCode.BOOKMARK_DUPLICATION);
    }
}
