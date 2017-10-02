package com.acme.edu.message;

import com.acme.edu.encoder.Encoder;
import com.acme.edu.saver.Saver;

public class ByteMessage extends IntegerTypeMessage {

    public ByteMessage(byte message, Saver saver, Encoder encoder) {
        super(message, saver, encoder);
    }

    @Override
    protected int getOverflowValue() {
        return Byte.MAX_VALUE;
    }

    @Override
    protected void handleIfMessageTypesAreEqual(Message previousMessage) {
        ByteMessage castedPreviousMessage = (ByteMessage) previousMessage;
        overflowCount = castedPreviousMessage.overflowCount;
        sum = modOverflowValue(castedPreviousMessage.sum, sum,
                Byte.MIN_VALUE, Byte.MAX_VALUE);
    }
}
