package com.acme.edu;

public class LoggerException extends Exception {
    public static final String NULL_POINTER_ARGUMENT_MESSAGE = "Argument is null!";
    public LoggerException(String message) {
        super(message);
    }

    public LoggerException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoggerException(Throwable cause) {
        super(cause);
    }
}
