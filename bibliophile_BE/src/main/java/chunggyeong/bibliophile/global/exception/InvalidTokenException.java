package chunggyeong.bibliophile.global.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class InvalidTokenException extends MainException {

    public static final MainException EXCEPTION = new InvalidTokenException();

    private InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
