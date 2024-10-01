package chunggyeong.bibliophile.domain.streak.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class BadPageException extends MainException {
    public static final MainException EXCEPTION = new BadPageException();
    public BadPageException() { super(ErrorCode.BAD_PAGE); }
}
