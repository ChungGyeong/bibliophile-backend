package chunggyeong.bibliophile.domain.fox.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class FoxNotFoundException extends MainException {
    public static final MainException EXCEPTION = new FoxNotFoundException();

    private FoxNotFoundException() { super(ErrorCode.FOX_NOT_FOUND); }
}
