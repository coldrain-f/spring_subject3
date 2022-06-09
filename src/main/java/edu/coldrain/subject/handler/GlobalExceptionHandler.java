package edu.coldrain.subject.handler;

import edu.coldrain.subject.handler.error.ErrorCode;
import edu.coldrain.subject.handler.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 모든 예외를 잡을 수 있는 핸들러
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected ErrorResponse handleException(Exception e) {
        log.error("handleException", e);
        return ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 여러 Exception 을 처리할 수도 있다.
     * 대신 여러 Exception 을 전부 받을 수 있는 타입으로 매개변수를 잡아줘야 한다.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            IllegalArgumentException.class,
            IllegalStateException.class
    })
    protected ErrorResponse handleIllegalException(RuntimeException e) {
        log.error("handleIllegalArgumentException", e);
        return ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE);
    }
}
