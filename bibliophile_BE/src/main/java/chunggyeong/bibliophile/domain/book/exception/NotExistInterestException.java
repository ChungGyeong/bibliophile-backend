package chunggyeong.bibliophile.domain.book.exception;

import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class NotExistInterestException extends MainException  {

    public static final MainException EXCEPTION = new NotExistInterestException();

    public NotExistInterestException() { super(ErrorCode.NO_EXIST_INTEREST); }
}
