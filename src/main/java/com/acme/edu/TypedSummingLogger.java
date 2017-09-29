package com.acme.edu;

import static java.lang.Math.abs;

public class TypedSummingLogger {
    public final static String PRIMITIVE_PREFIX = "primitive: ";
    public final static String REFERENCE_PREFIX = "reference: ";
    public final static String CHAR_PREFIX = "char: ";
    public final static String STRING_PREFIX = "string: ";
    public final static String PRIMITIVES_ARRAY_PREFIX = "primitives array: ";

    private Saver saver;

    private int sumInteger;
    private byte sumByte;
    private int overflowCount;
    private String currentString;
    private int subsequentStringsCount;
    private char currentChar;
    private boolean currentBoolean;
    private Object currentReference;
    private int[] currentArray;

    private static LoggerState state = LoggerState.DEFAULT_STATE;

    public TypedSummingLogger() {
        saver = new Saver();
    }

    public TypedSummingLogger(Saver saver) {
        this.saver = saver;
    }

    /**
     * The method prepares int or the sum of sequential ints for writing.
     *
     * @param message The int to be written or summed up.
     */
    public void log(int message) {
        if (state != LoggerState.INTEGER_STATE) {
            printBuffer();
            state = LoggerState.INTEGER_STATE;
            sumInteger = 0;
            overflowCount = 0;
        }
        sumInteger = modOverflowValue(sumInteger, message, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * The method prepares byte or the sum of sequential bytes for writing.
     *
     * @param message The byte to be written or summed up.
     */
    public void log(byte message) {
        if (state != LoggerState.BYTE_STATE) {
            printBuffer();
            state = LoggerState.BYTE_STATE;
            sumByte = 0;
            overflowCount = 0;
        }
        sumByte = (byte) modOverflowValue(sumByte, message, Byte.MIN_VALUE, Byte.MAX_VALUE);
    }

    /**
     * The method prepares char for writing.
     *
     * @param message The char to be written.
     */
    public void log(char message) {
        printBuffer();
        currentChar = message;
        state = LoggerState.CHAR_STATE;
    }

    /**
     * The method prepares sequential Strings for writing.
     * If the length of the sequence is equal to 1, only String will be written.
     * If the length of the sequence is more than 1, the repetitive String and the length of the sequence will be
     * written.
     *
     * @param message The String to be written.
     */
    public void log(String message) {
        boolean isCurrentStateStringState = (state == LoggerState.STRING_STATE);
        if (!isCurrentStateStringState || !message.equals(currentString)) {
            printBuffer();
            currentString = message;
            subsequentStringsCount = 1;
        } else {
            subsequentStringsCount++;
        }
        state = LoggerState.STRING_STATE;
    }

    /**
     * The method prepares boolean for writing.
     *
     * @param message The boolean to be written.
     */
    public void log(boolean message) {
        printBuffer();
        currentBoolean = message;
        state = LoggerState.BOOLEAN_STATE;
    }

    /**
     * The method prepares object reference for writing.
     *
     * @param message The object reference to be written.
     */
    public void log(Object message) {
        printBuffer();
        currentReference = message;
        state = LoggerState.REFERENCE_STATE;
    }

    /**
     * The method prepares array of ints for writing into console.
     *
     * @param message The array of ints to be printed.
     */
    public void log(int[] message) {
        printBuffer();
        currentArray = message;
        state = LoggerState.ARRAY_STATE;
    }

    /**
     * The method writes all that has been accumulated in the buffer into console.
     */
    public void close() {
        printBuffer();
    }

    private int modOverflowValue(int a, int b, int minValue, int maxValue) {
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


    private void printBuffer() {
        String stringToPrint = "";
        switch (state) {
            case INTEGER_STATE:
                stringToPrint = prepareIntegerPrimitiveForPrinting(sumInteger, Integer.MAX_VALUE);
                break;
            case BYTE_STATE:
                stringToPrint = prepareIntegerPrimitiveForPrinting(sumByte, Byte.MAX_VALUE);
                break;
            case STRING_STATE:
                stringToPrint = prepareStringForPrinting(STRING_PREFIX + currentString);
                break;
            case ARRAY_STATE:
                stringToPrint = PRIMITIVES_ARRAY_PREFIX + arrayToString(currentArray);
                break;
            case CHAR_STATE:
                stringToPrint = CHAR_PREFIX + currentChar;
                break;
            case BOOLEAN_STATE:
                stringToPrint = PRIMITIVE_PREFIX + currentBoolean;
                break;
            case REFERENCE_STATE:
                stringToPrint = REFERENCE_PREFIX + currentReference.toString();
            default:
                break;
        }
        if (stringToPrint.length() > 0)
            saver.print(stringToPrint);
        state = LoggerState.DEFAULT_STATE;
    }

    private String prepareIntegerPrimitiveForPrinting(int sum, int overflowValue) {
        if (abs(overflowCount) > 0) {
            return PRIMITIVE_PREFIX + overflowValue + " x " + overflowCount + System.lineSeparator()
                    + PRIMITIVE_PREFIX + sum;
        }
        return PRIMITIVE_PREFIX + sum;
    }

    private String prepareStringForPrinting(String stringToPrint) {
        if (subsequentStringsCount > 1) {
            stringToPrint += " (x" + subsequentStringsCount + ")";
        }
        return stringToPrint;
    }

    private static String arrayToString(int[] array) {
        StringBuilder arrayToStringBuilder = new StringBuilder();
        arrayToStringBuilder.append("{");
        for (int i = 0; i < array.length - 1; i++) {
            arrayToStringBuilder.append(array[i]).append(", ");
        }
        arrayToStringBuilder.append(array[array.length - 1]).append("}");
        return arrayToStringBuilder.toString();
    }

}
