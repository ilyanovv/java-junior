package com.acme.edu.message;


import com.acme.edu.formatter.Formatter;
import com.acme.edu.saver.Saver;

public class ReferenceMessage implements Message {
    private final String REFERENCE_PREFIX = "reference: ";
    private Object message;
    private Formatter formatter;
    private Saver saver;

    public ReferenceMessage(Object message, Formatter formatter, Saver saver) {
        this.message = message;
        this.formatter = formatter;
        this.saver = saver;
    }

    @Override
    public void handle(Message previousMessage) {
        if (previousMessage != null)
            previousMessage.save();
    }

    @Override
    public String format() {
        return formatter.format(REFERENCE_PREFIX + message);
    }

    @Override
    public void save() {
        saver.save(format());
    }
}
