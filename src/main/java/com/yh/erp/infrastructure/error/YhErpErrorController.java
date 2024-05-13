package com.yh.erp.infrastructure.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class YhErpErrorController {

    @ExceptionHandler(YhErpException.class)
    public ResponseEntity<YhErpErrorResponse> handleYhErpException(YhErpException e) {
        return YhErpErrorResponse.toResponseEntity(e);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<YhErpErrorResponse> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return YhErpErrorResponse.toResponseEntity("시스템 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
