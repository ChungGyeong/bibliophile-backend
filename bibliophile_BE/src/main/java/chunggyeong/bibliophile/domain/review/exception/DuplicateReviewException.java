package chunggyeong.bibliophile.domain.review.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class DuplicateReviewException extends MainException {

    public static final MainException EXCEPTION = new DuplicateReviewException();

    private DuplicateReviewException() {
        super(ErrorCode.REVIEW_DUPLICATION);
    }
}
