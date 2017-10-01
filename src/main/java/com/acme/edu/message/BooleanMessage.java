package com.acme.edu.message;

import com.acme.edu.formatter.Formatter;
import com.acme.edu.saver.Saver;

public class BooleanMessage implements Message {
    private final String PRIMITIVE_PREFIX = "primitive: ";
    private boolean message;
    private Formatter formatter;
    private Saver saver;

    public BooleanMessage(boolean message, Formatter formatter, Saver saver) {
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
        return formatter.format(PRIMITIVE_PREFIX + message);
    }

    @Override
    public void save() {
        saver.save(format());
    }
}
