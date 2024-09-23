package chunggyeong.bibliophile.domain.book.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class InvalidBookITargetException extends MainException{
    public static final MainException EXCEPTION = new InvalidBookITargetException();

    private InvalidBookITargetException() {
        super(ErrorCode.INTEREST_DUPLICATION);
    }
}
