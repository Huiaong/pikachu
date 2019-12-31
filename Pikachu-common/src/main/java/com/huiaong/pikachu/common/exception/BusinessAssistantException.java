package com.huiaong.pikachu.common.exception;

public class BusinessAssistantException extends RuntimeException {
    private int status = 500;
    private String message = "unknown exception";

    public BusinessAssistantException() {
    }

    public BusinessAssistantException(String message) {
        this.message = message;
    }

    public BusinessAssistantException(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public BusinessAssistantException(int status, String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.status = status;
    }

    public BusinessAssistantException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public BusinessAssistantException(int status, Throwable cause) {
        super(cause);
        this.message = cause.getMessage();
        this.status = status;
    }

    public BusinessAssistantException(Throwable cause) {
        super(cause);
        this.message = cause.getMessage();
    }
}
