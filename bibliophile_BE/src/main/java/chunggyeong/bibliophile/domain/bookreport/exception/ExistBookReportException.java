package chunggyeong.bibliophile.domain.bookreport.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class ExistBookReportException extends MainException {
    public static final MainException EXCEPTION = new ExistBookReportException();

    private ExistBookReportException() {
        super(ErrorCode.BOOK_REPORT_EXIST);
    }
}
