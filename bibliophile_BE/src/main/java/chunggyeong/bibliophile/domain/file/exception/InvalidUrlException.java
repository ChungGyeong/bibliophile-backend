package chunggyeong.bibliophile.domain.file.exception;


import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class InvalidUrlException extends MainException {

    public static final MainException EXCEPTION = new InvalidUrlException();

    private InvalidUrlException() {
        super(ErrorCode.INVALID_URL);
    }
}