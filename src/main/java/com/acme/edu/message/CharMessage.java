package com.acme.edu.message;

import com.acme.edu.formatter.Formatter;
import com.acme.edu.saver.Saver;

public class CharMessage implements Message {
    private final String CHAR_PREFIX = "char: ";
    private char message;
    private Formatter formatter;
    private Saver saver;

    public CharMessage(char message, Formatter formatter, Saver saver) {
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
        return formatter.format(CHAR_PREFIX + message);
    }

    @Override
    public void save() {
        saver.save(format());
    }
}
