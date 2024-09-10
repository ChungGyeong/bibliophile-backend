package chunggyeong.bibliophile.global.error.exception;

import chunggyeong.bibliophile.global.error.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.HashMap;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MainException.class)
    public ResponseEntity<ErrorResponse> mainExceptionHandler(
            MainException e, HttpServletRequest request) throws IOException {

        ErrorCode code = e.getErrorCode();

        ErrorResponse errorResponse =
                new ErrorResponse(
                        code.getStatus(),
                        code.getReason());

        return ResponseEntity.status(HttpStatus.valueOf(code.getStatus())).body(errorResponse);
    }

    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<ErrorResponse> bibliophileHandleException(Exception e, HttpServletRequest request)
            throws IOException {

        log.error("INTERNAL_SERVER_ERROR", e);
        ErrorCode internalServerError = ErrorCode.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse =
                new ErrorResponse(
                        internalServerError.getStatus(),
                        internalServerError.getReason());

        return ResponseEntity.status(HttpStatus.valueOf(internalServerError.getStatus()))
                .body(errorResponse);
    }
}
