package com.huiaong.pikachu.common.exception;

public class DataPersistenceException extends RuntimeException {
    private int status = 500;
    private String message = "unknown exception";

    public DataPersistenceException() {
    }

    public DataPersistenceException(String message) {
        this.message = message;
    }

    public DataPersistenceException(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public DataPersistenceException(int status, String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.status = status;
    }

    public DataPersistenceException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public DataPersistenceException(int status, Throwable cause) {
        super(cause);
        this.message = cause.getMessage();
        this.status = status;
    }

    public DataPersistenceException(Throwable cause) {
        super(cause);
        this.message = cause.getMessage();
    }
}
