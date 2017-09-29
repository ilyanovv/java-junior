package com.acme.edu;

import static java.lang.Math.abs;

public class TypedSummingLogger {
    public final static String PRIMITIVE_PREFIX = "primitive: ";
    public final static String REFERENCE_PREFIX = "reference: ";
    public final static String CHAR_PREFIX = "char: ";
    public final static String STRING_PREFIX = "string: ";
    public final static String PRIMITIVES_ARRAY_PREFIX = "primitives array: ";

    private static int sumInteger;
    private static byte sumByte;
    private static int overflowCount;
    private static String currentString;
    private static int subsequentStringsCount;
    private static char currentChar;
    private static boolean currentBoolean;
    private static Object currentReference;
    private static int[] currentArray;

    private static LoggerState state = LoggerState.DEFAULT_STATE;

    /**
     * The method prepares int or the sum of sequential ints for writing.
     * @param message The int to be written or summed up.
     */
    public static void log(int message) {
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
     * @param message The byte to be written or summed up.
     */
    public static void log(byte message) {
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
     * @param message The char to be written.
     */
    public static void log(char message) {
        printBuffer();
        currentChar = message;
        state = LoggerState.CHAR_STATE;
    }

    /**
     * The method prepares sequential Strings for writing.
     * If the length of the sequence is equal to 1, only String will be written.
     * If the length of the sequence is more than 1, the repetitive String and the length of the sequence will be
     * written.
     * @param message The String to be written.
     */
    public static void log(String message) {
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
     * @param message The boolean to be written.
     */
    public static void log(boolean message) {
        printBuffer();
        currentBoolean = message;
        state = LoggerState.BOOLEAN_STATE;
    }

    /**
     * The method prepares object reference for writing.
     * @param message The object reference to be written.
     */
    public static void log(Object message) {
        printBuffer();
        currentReference = message;
        state = LoggerState.REFERENCE_STATE;
    }

    /**
     * The method prepares array of ints for writing into console.
     * @param message The array of ints to be printed.
     */
    public static void log(int[] message) {
        printBuffer();
        currentArray = message;
        state = LoggerState.ARRAY_STATE;
    }

    /**
     * The method writes all that has been accumulated in the buffer into console.
     */
    public static void close() {
        printBuffer();
    }

    private static int modOverflowValue(int a, int b, int minValue, int maxValue) {
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

    private static void printIntegerPrimitive(int sum, int overflowValue) {
        if (abs(overflowCount) > 0) {
            print(PRIMITIVE_PREFIX + overflowValue + " x " + overflowCount);
        }
        print(PRIMITIVE_PREFIX + sum);
    }

    private static void printBuffer() {
        switch (state) {
            case INTEGER_STATE:
                printIntegerPrimitive(sumInteger, Integer.MAX_VALUE);
                break;
            case BYTE_STATE:
                printIntegerPrimitive(sumByte, Byte.MAX_VALUE);
                break;
            case STRING_STATE:
                String stringToPrint = prepareStringForPrinting(STRING_PREFIX + currentString);
                print(stringToPrint);
                break;
            case ARRAY_STATE:
                print(PRIMITIVES_ARRAY_PREFIX + arrayToString(currentArray));
                break;
            case CHAR_STATE:
                print(CHAR_PREFIX + currentChar);
                break;
            case BOOLEAN_STATE:
                print(PRIMITIVE_PREFIX + currentBoolean);
                break;
            case REFERENCE_STATE:
                print(REFERENCE_PREFIX + currentReference.toString());
            default:
                break;
        }
        state = LoggerState.DEFAULT_STATE;
    }

    private static void print(String message) {
        System.out.println(message);
    }

    private static String prepareStringForPrinting(String stringToPrint) {
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
