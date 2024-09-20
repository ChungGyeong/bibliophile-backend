package chunggyeong.bibliophile.domain.file.exception;


import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class FileSizeException extends MainException {

    public static final MainException EXCEPTION = new FileSizeException();

    private FileSizeException() {
        super(ErrorCode.FILE_SIZE);
    }
}