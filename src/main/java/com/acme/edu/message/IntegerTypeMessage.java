package com.acme.edu.message;

import com.acme.edu.formatter.Formatter;
import com.acme.edu.saver.Saver;

import static java.lang.Math.abs;

public abstract class IntegerTypeMessage implements Message {
    private final String PRIMITIVE_PREFIX = "primitive: ";
    protected int sum;
    protected int overflowCount;
    private Formatter formatter;
    private Saver saver;

    public IntegerTypeMessage(int message) {
        this.sum = message;
        this.overflowCount = 0;
    }

    public IntegerTypeMessage(int message, Formatter formatter, Saver saver) {
        this.sum = message;
        this.overflowCount = 0;
        this.formatter = formatter;
        this.saver = saver;
    }

    abstract int getOverflowValue();

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

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getOverflowCount() {
        return overflowCount;
    }

    public void setOverflowCount(int overflowCount) {
        this.overflowCount = overflowCount;
    }

    public String format() {
        String formattedString = "";
        if (abs(overflowCount) > 0) {
            formattedString = formatter.format(PRIMITIVE_PREFIX + getOverflowValue() + " x "
                    + overflowCount + System.lineSeparator());
        }
        formattedString += formatter.format(PRIMITIVE_PREFIX + Integer.toString(sum));
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
