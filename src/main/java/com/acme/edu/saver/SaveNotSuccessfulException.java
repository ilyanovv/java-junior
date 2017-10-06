package com.acme.edu.saver;

import java.io.IOException;

public class SaveNotSuccessfulException extends IOException {
    public static final String SAVE_NOT_SUCCESFUL_MESSAGE = "Message was not saved";
    public SaveNotSuccessfulException(String message) {
        super(message);
    }

    public SaveNotSuccessfulException(String message, Throwable cause) {
        super(message, cause);
    }
}
