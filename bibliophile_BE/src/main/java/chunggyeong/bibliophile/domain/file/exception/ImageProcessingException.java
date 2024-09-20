package chunggyeong.bibliophile.domain.file.exception;


import chunggyeong.bibliophile.global.error.exception.ErrorCode;
import chunggyeong.bibliophile.global.error.exception.MainException;

public class ImageProcessingException extends MainException {

    public static final MainException EXCEPTION = new ImageProcessingException();

    private ImageProcessingException() {
        super(ErrorCode.IMAGE_PROCESSING);
    }
}