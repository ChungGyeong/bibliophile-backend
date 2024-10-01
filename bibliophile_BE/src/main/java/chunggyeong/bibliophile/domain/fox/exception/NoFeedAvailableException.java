package chunggyeong.bibliophile.domain.fox.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class NoFeedAvailableException extends MainException {
    public static final MainException EXCEPTION = new NoFeedAvailableException();

    public NoFeedAvailableException() { super(ErrorCode.NO_FEED_AVAILABLE); }
}
