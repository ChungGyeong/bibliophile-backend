package chunggyeong.bibliophile.domain.fox.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class AlreadyHasFoxException extends MainException {
    public static final MainException EXCEPTION = new AlreadyHasFoxException();

    private AlreadyHasFoxException() {
        super(ErrorCode.ALREADY_HAS_FOX);
    }
}
