package com.acme.edu.saver;

public class ExceptionSaver implements Saver {
    @Override
    public void save(String message) throws SaveNotSuccessfulException {
        throw new SaveNotSuccessfulException(SaveNotSuccessfulException.SAVE_NOT_SUCCESFUL_MESSAGE);
    }
}
