package com.acme.edu.message;

import com.acme.edu.formatter.Formatter;
import com.acme.edu.saver.Saver;

public class BooleanMessage extends SummingMessage implements Message {
    private final String primitivePrefix = "primitive: ";
    private boolean message;
    private Formatter formatter;
    private Saver saver;

    public BooleanMessage(boolean message, Formatter formatter, Saver saver) {
        this.message = message;
        this.formatter = formatter;
        this.saver = saver;
    }

    @Override
    protected void handleIfMessageTypesAreEqual(Message previousMessage) {
        previousMessage.save();
    }

    @Override
    public String format() {
        return formatter.format(primitivePrefix + message);
    }

    @Override
    public void save() {
        saver.save(format());
    }
}
