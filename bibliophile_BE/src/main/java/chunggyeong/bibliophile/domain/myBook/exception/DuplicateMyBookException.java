package chunggyeong.bibliophile.domain.myBook.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class DuplicateMyBookException extends MainException {

    public static final MainException EXCEPTION = new DuplicateMyBookException();

    private DuplicateMyBookException() {
        super(ErrorCode.MY_BOOK_DUPLICATION);
    }
}
