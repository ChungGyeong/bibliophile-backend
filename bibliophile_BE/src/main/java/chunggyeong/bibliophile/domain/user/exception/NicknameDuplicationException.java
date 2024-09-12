package chunggyeong.bibliophile.domain.user.exception;


import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class NicknameDuplicationException extends MainException {

    public static final MainException EXCEPTION = new NicknameDuplicationException();

    private NicknameDuplicationException() {
        super(ErrorCode.NICKNAME_DUPLICATION);
    }
}
