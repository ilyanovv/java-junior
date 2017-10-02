package com.acme.edu.message;

import com.acme.edu.formatter.Formatter;
import com.acme.edu.saver.Saver;

public class PrimitivesArrayMessage extends SummingMessage implements Message {
    private final String primitivesArrayPrefix = "primitives array: ";
    private int[] message;
    private Formatter formatter;
    private Saver saver;

    public PrimitivesArrayMessage(int[] message, Formatter formatter, Saver saver) {
        this.message = message;
        this.formatter = formatter;
        this.saver = saver;
    }

    public PrimitivesArrayMessage(int[] message) {
        this.message = message;
    }

    @Override
    protected void handleIfMessageTypesAreEqual(Message previousMessage) {
        previousMessage.save();
    }

    @Override
    public String format() {
        return formatter.format(primitivesArrayPrefix + arrayToString(message));
    }

    @Override
    public void save() {
        saver.save(format());
    }

    private String arrayToString(int[] array) {
        StringBuilder arrayToStringBuilder = new StringBuilder();
        arrayToStringBuilder.append("{");
        for (int i = 0; i < array.length - 1; i++) {
            arrayToStringBuilder.append(array[i]).append(", ");
        }
        arrayToStringBuilder.append(array[array.length - 1]).append("}");
        return arrayToStringBuilder.toString();
    }
}
