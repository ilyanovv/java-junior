package com.acme.edu.message;

import com.acme.edu.formatter.Formatter;
import com.acme.edu.saver.Saver;

public class StringMessage extends SummingMessage implements Message {
    private final String stringPrefix = "string: ";
    private String messageString;
    private int subsequentStringsCount;
    private Formatter formatter;
    private Saver saver;

    public StringMessage(String messageString) {
        this.messageString = messageString;
        this.subsequentStringsCount = 1;
    }

    public StringMessage(String messageString, Formatter formatter, Saver saver) {
        this.messageString = messageString;
        this.subsequentStringsCount = 1;
        this.formatter = formatter;
        this.saver = saver;
    }

    @Override
    protected void handleIfMessageTypesAreEqual(Message previousMessage) {
        if (previousMessage.getClass() == StringMessage.class) {
            if (messageString.equals(((StringMessage) previousMessage).messageString)) {
                subsequentStringsCount = ((StringMessage) previousMessage).subsequentStringsCount + 1;
            } else {
                previousMessage.save();
            }
        }
    }

    @Override
    public String format() {
        if (subsequentStringsCount > 1) {
            return formatter.format(stringPrefix + messageString + " (x" + subsequentStringsCount + ")");
        } else return formatter.format(stringPrefix + messageString);
    }

    @Override
    public void save() {
        saver.save(format());
    }

    public Formatter getFormatter() {
        return formatter;
    }

    public void setFormatter(Formatter formatter) {
        this.formatter = formatter;
    }

    public Saver getSaver() {
        return saver;
    }

    public void setSaver(Saver saver) {
        this.saver = saver;
    }

    public String getMessageString() {
        return messageString;
    }

    public void setMessageString(String messageString) {
        this.messageString = messageString;
    }

    public int getSubsequentStringsCount() {
        return subsequentStringsCount;
    }

    public void setSubsequentStringsCount(int subsequentStringsCount) {
        this.subsequentStringsCount = subsequentStringsCount;
    }
}
