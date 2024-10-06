package chunggyeong.bibliophile.domain.bookreport.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class BookReportNotFoundException extends MainException {

    public static final MainException EXCEPTION = new BookReportNotFoundException();

    private BookReportNotFoundException() { super(ErrorCode.BOOK_REPORT_NOT_FOUND); }
}
