package chunggyeong.bibliophile.domain.interest.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class DuplicateClassificationException extends MainException {
    public static final MainException EXCEPTION = new DuplicateClassificationException();

    private DuplicateClassificationException() {
        super(ErrorCode.INTEREST_DUPLICATION);
    }
}
