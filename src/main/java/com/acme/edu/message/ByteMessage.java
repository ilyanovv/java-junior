package com.acme.edu.message;

import com.acme.edu.formatter.Formatter;
import com.acme.edu.saver.Saver;

public class ByteMessage extends IntegerTypeMessage {

    public ByteMessage(byte message) {
        super(message);
    }

    public ByteMessage(byte message, Formatter formatter, Saver saver) {
        super(message, formatter, saver);
    }

    @Override
    public int getOverflowValue() {
        return Byte.MAX_VALUE;
    }

    @Override
    public void handle(Message previousMessage) {
        if (previousMessage == null) {
            return;
        }
        if (previousMessage.getClass() == ByteMessage.class) {
            overflowCount = ((ByteMessage) previousMessage).overflowCount;
            sum = modOverflowValue(((ByteMessage) previousMessage).sum, sum,
                    Byte.MIN_VALUE, Byte.MAX_VALUE);
        } else {
            previousMessage.save();
        }
    }
}
