package com.acme.edu.message;

import com.acme.edu.encoder.Encoder;
import com.acme.edu.saver.Saver;

public class IntegerMessage extends IntegerTypeMessage {

    public IntegerMessage(int message, Saver saver, Encoder encoder) {
        super(message, saver, encoder);
    }

    @Override
    protected int getOverflowValue() {
        return Integer.MAX_VALUE;
    }

    @Override
    protected void handleIfMessageTypesAreEqual(Message previousMessage) {
        IntegerMessage castedPreviousMessage = (IntegerMessage) previousMessage;
        overflowCount = castedPreviousMessage.overflowCount;
        sum = modOverflowValue(castedPreviousMessage.sum, sum,
                Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}
