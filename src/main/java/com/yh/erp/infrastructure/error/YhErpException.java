package com.yh.erp.infrastructure.error;

public class YhErpException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public YhErpException(Throwable throwable) {
        super(throwable);
    }

    public YhErpException(String message) {
        super(message);
    }

    public YhErpException(String message, Object... args) {
        super(String.format(message, args));
    }

    public YhErpException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public YhErpException(String message, Throwable throwable, Object... args) {
        super(String.format(message, args), throwable);
    }

}
