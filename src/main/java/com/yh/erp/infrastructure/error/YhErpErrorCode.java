package com.yh.erp.infrastructure.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum YhErpErrorCode {

    E000001(YhErpException.class, HttpStatus.BAD_REQUEST);

    private Class<? extends YhErpException> exception;

    private HttpStatus status;

    public String getCode() {
        return this.toString();
    }

    public static YhErpErrorCode of(YhErpException e) {
        YhErpErrorCode[] codes = YhErpErrorCode.values();

        for (YhErpErrorCode code : codes) {
            if (e.getClass().isAssignableFrom(code.exception)) {
                return code;
            }
        }

        throw new IllegalArgumentException("정의되지 않은 예외 입니다.");
    }

}
