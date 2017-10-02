package com.acme.edu.message;

import com.acme.edu.encoder.Encoder;
import com.acme.edu.saver.Saver;

public class BooleanMessage extends SummingMessage implements Message {
    private final String primitivePrefix = "primitive: ";
    private boolean message;

    public BooleanMessage(boolean message, Saver saver, Encoder encoder) {
        this.message = message;
        this.saver = saver;
        this.encoder = encoder;
    }

    @Override
    protected void handleIfMessageTypesAreEqual(Message previousMessage) {
        previousMessage.save();

    }

    @Override
    public String format() {
        return primitivePrefix + message;
    }
}
