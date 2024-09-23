package chunggyeong.bibliophile.domain.book.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class BookNotFoundException extends MainException {

    public static final MainException EXCEPTION = new BookNotFoundException();

    private BookNotFoundException() {
        super(ErrorCode.BOOK_NOT_FOUND);
    }
}
