package com.acme.edu.saver;

public class SaveNotSuccessfulException extends Exception {
    public static final String SAVE_NOT_SUCCESFUL_MESSAGE = "Message was not saved";
    public SaveNotSuccessfulException(String message) {
        super(message);
    }

    public SaveNotSuccessfulException(String message, Throwable cause) {
        super(message, cause);
    }
}
