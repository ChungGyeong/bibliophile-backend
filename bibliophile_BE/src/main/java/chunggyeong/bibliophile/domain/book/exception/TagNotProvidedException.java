package chunggyeong.bibliophile.domain.book.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class TagNotProvidedException extends MainException {
    public static final MainException EXCEPTION = new TagNotProvidedException();

    private TagNotProvidedException() {
        super(ErrorCode.NO_TAG_PROVIDED);
    }
}
