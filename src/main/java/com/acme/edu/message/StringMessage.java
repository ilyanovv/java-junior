package com.acme.edu.message;

import com.acme.edu.encoder.Encoder;
import com.acme.edu.saver.SaveNotSuccessfulException;
import com.acme.edu.saver.Saver;

public class StringMessage extends SummingMessage implements Message {
    private final String stringPrefix = "string: ";
    private String messageString;
    private int subsequentStringsCount;

    public StringMessage(String messageString, Saver saver, Encoder encoder) {
        this.messageString = messageString;
        this.subsequentStringsCount = 1;
        this.saver = saver;
        this.encoder = encoder;
    }

    @Override
    protected void handleIfMessageTypesAreEqual(Message previousMessage) throws SaveNotSuccessfulException {
        StringMessage castedPreviousMessage = (StringMessage) previousMessage;
        if (messageString.equals(castedPreviousMessage.messageString)) {
            subsequentStringsCount = castedPreviousMessage.subsequentStringsCount + 1;
        } else {
            previousMessage.save();
        }
    }

    @Override
    public String format() {
        if (subsequentStringsCount > 1) {
            return stringPrefix + messageString + " (x" + subsequentStringsCount + ")";
        } else return stringPrefix + messageString;
    }

    public Saver getSaver() {
        return saver;
    }

    public void setSaver(Saver saver) {
        this.saver = saver;
    }

}
