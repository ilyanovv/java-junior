package com.acme.edu.message;


import com.acme.edu.encoder.Encoder;
import com.acme.edu.saver.SaveNotSuccessfulException;
import com.acme.edu.saver.Saver;

public class ReferenceMessage extends SummingMessage {
    private final String referencePrefix = "reference: ";
    private Object message;

    public ReferenceMessage(Object message, Saver saver, Encoder encoder) {
        this.message = message;
        this.saver = saver;
        this.encoder = encoder;
    }

    @Override
    protected void handleIfMessageTypesAreEqual(Message previousMessage) throws SaveNotSuccessfulException {
        previousMessage.save();
    }

    @Override
    public String format() {
        return referencePrefix + message;
    }

}
