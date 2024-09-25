package chunggyeong.bibliophile.domain.memo.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class MemoNotFoundException extends MainException {

    public static final MainException EXCEPTION = new MemoNotFoundException();

    private MemoNotFoundException() {
        super(ErrorCode.MEMO_NOT_FOUND);
    }
}
