package chunggyeong.bibliophile.domain.myBook.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class MyBookNotFoundException extends MainException {

    public static final MainException EXCEPTION = new MyBookNotFoundException();

    private MyBookNotFoundException() {
        super(ErrorCode.MY_BOOK_NOT_FOUND);
    }
}
