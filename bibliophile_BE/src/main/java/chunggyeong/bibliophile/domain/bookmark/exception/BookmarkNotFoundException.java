package chunggyeong.bibliophile.domain.bookmark.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class BookmarkNotFoundException extends MainException {

    public static final MainException EXCEPTION = new BookmarkNotFoundException();

    private BookmarkNotFoundException() {
        super(ErrorCode.BOOKMARK_NOT_FOUND);
    }
}
