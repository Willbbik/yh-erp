package com.yh.erp.infrastructure.error;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
public class YhErpErrorResponse {

    private String code;

    private String message;

    public YhErpErrorResponse(String message) {
        this.message = message;
    }

    public YhErpErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseEntity<YhErpErrorResponse> toResponseEntity(YhErpException e) {
        YhErpErrorCode errorCode = YhErpErrorCode.of(e);
        YhErpErrorResponse response = new YhErpErrorResponse(errorCode.getCode(), e.getMessage());

        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    public static ResponseEntity<YhErpErrorResponse> toResponseEntity(String message, HttpStatus status) {
        YhErpErrorResponse response = new YhErpErrorResponse(String.valueOf(status.value()), message);
        return ResponseEntity.status(status).body(response);
    }

}
