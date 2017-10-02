package com.acme.edu.message;

import com.acme.edu.formatter.Formatter;
import com.acme.edu.saver.Saver;

public class IntegerMessage extends IntegerTypeMessage {

    public IntegerMessage(int message) {
        super(message);
    }

    public IntegerMessage(int message, Formatter formatter, Saver saver) {
        super(message, formatter, saver);
    }

    @Override
    public int getOverflowValue() {
        return Integer.MAX_VALUE;
    }

    @Override
    protected void handleIfMessageTypesAreEqual(Message previousMessage) {
        if (previousMessage.getClass() == IntegerMessage.class) {
            overflowCount = ((IntegerMessage) previousMessage).overflowCount;
            sum = modOverflowValue(((IntegerMessage) previousMessage).sum, sum,
                    Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
    }
}
