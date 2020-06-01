package com.github.sejoung.hmac.api.common.exceptionhandler;

import com.github.sejoung.hmac.api.common.constants.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorCode> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        var error = ErrorCode.PARAMETER_UNKNOWN;
        return ResponseEntity.status(error.getCode()).body(error);
    }

}
