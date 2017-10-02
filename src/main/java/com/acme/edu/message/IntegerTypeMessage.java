package com.acme.edu.message;

import com.acme.edu.encoder.Encoder;
import com.acme.edu.saver.Saver;

import static java.lang.Math.abs;

public abstract class IntegerTypeMessage extends SummingMessage {
    private final String primitivePrefix = "primitive: ";
    protected int sum;
    protected int overflowCount;

    /**
     * @param message The int value of Message
     */
    public IntegerTypeMessage(int message) {
        this.sum = message;
        this.overflowCount = 0;
    }


    public IntegerTypeMessage(int message, Saver saver, Encoder encoder) {
        this.sum = message;
        this.overflowCount = 0;
        this.saver = saver;
        this.encoder = encoder;
    }

    abstract int getOverflowValue();

    public String format() {
        String formattedString = "";
        if (abs(overflowCount) > 0) {
            formattedString = primitivePrefix + getOverflowValue() + " x "
                    + overflowCount + System.lineSeparator();
        }
        formattedString += primitivePrefix + Integer.toString(sum);
        return formattedString;
    }

    public Saver getSaver() {
        return saver;
    }

    public void setSaver(Saver saver) {
        this.saver = saver;
    }

    protected int modOverflowValue(int a, int b, int minValue, int maxValue) {
        if (a > 0 && b > 0) {
            if (a > maxValue - b) {
                overflowCount++;
                return a - maxValue + b;
            }
        } else if (a < 0 && b < 0) {
            if (a < minValue - b) {
                overflowCount--;
                return a - minValue + b;
            }
        }
        return a + b;
    }
}
