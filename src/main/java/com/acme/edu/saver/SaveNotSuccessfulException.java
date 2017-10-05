package com.acme.edu.saver;

import java.io.IOException;

public class SaveNotSuccessfulException extends IOException {
    public SaveNotSuccessfulException(String message) {
        super(message);
    }

    public SaveNotSuccessfulException(String message, Throwable cause) {
        super(message, cause);
    }
}
