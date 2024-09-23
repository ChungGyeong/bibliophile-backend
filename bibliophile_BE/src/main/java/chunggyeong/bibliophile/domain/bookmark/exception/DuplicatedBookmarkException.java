package chunggyeong.bibliophile.domain.bookmark.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class DuplicatedBookmarkException extends MainException{

    public static final MainException EXCEPTION = new DuplicatedBookmarkException();

    private DuplicatedBookmarkException() {
        super(ErrorCode.BOOKMARK_DUPLICATION);
    }
}
