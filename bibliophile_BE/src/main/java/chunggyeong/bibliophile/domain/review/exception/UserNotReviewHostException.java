package chunggyeong.bibliophile.domain.review.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class UserNotReviewHostException extends MainException {

    public static final MainException EXCEPTION = new UserNotReviewHostException();

    private UserNotReviewHostException() {
        super(ErrorCode.USER_NOT_REVIEW_HOST);
    }
}
