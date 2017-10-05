package com.acme.edu.message;

import com.acme.edu.encoder.Encoder;
import com.acme.edu.saver.SaveNotSuccessfulException;
import com.acme.edu.saver.Saver;

public class PrimitivesArrayMessage extends SummingMessage implements Message {
    private final String primitivesArrayPrefix = "primitives array: ";
    private int[] message;

    public PrimitivesArrayMessage(int[] message, Saver saver, Encoder encoder) {
        this.message = message;
        this.saver = saver;
        this.encoder = encoder;
    }

    @Override
    protected void handleIfMessageTypesAreEqual(Message previousMessage) throws SaveNotSuccessfulException {
        previousMessage.save();
    }

    @Override
    public String format() {
        return primitivesArrayPrefix + arrayToString(message);
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
