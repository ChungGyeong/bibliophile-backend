package chunggyeong.bibliophile.domain.review.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class ReviewNotFoundException extends MainException {

    public static final MainException EXCEPTION = new ReviewNotFoundException();

    private ReviewNotFoundException() {
        super(ErrorCode.REVIEW_NOT_FOUND);
    }
}
