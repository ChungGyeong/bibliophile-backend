package chunggyeong.bibliophile.domain.interest.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class InterestLimitExceededException extends MainException {

    public static final MainException EXCEPTION = new InterestLimitExceededException();

    private InterestLimitExceededException() {
        super(ErrorCode.MAX_INTEREST_LIMIT_EXCEEDED);
    }
}
