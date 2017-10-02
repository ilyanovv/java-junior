package com.acme.edu.message;


import com.acme.edu.formatter.Formatter;
import com.acme.edu.saver.Saver;

public class ReferenceMessage extends SummingMessage implements Message {
    private final String referencePrefix = "reference: ";
    private Object message;
    private Formatter formatter;
    private Saver saver;

    public ReferenceMessage(Object message, Formatter formatter, Saver saver) {
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
        return formatter.format(referencePrefix + message);
    }

    @Override
    public void save() {
        saver.save(format());
    }
}
