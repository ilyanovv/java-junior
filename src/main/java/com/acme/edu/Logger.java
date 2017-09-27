package com.acme.edu;


import java.io.PrintStream;

public class Logger {
    private final static String PRIMITIVE_PREFIX = "primitive: ";
    private final static String REFERENCE_PREFIX = "reference: ";
    private final static String CHAR_PREFIX = "char: ";
    private final static String STRING_PREFIX = "string: ";
    private static PrintStream printStream = System.out;

    public static void log(int message) {
        System.out.println(PRIMITIVE_PREFIX + message);
    }

    public static void log(byte message) {
        printStream.println(PRIMITIVE_PREFIX + message);
    }

    public static void log(char message){
        printStream.println(CHAR_PREFIX + message);
    }

    public static void log(String message){
        printStream.println(STRING_PREFIX + message);
    }

    public static void log(boolean message) {
        printStream.println(PRIMITIVE_PREFIX + message);
    }

    public static void log(Object message) {
        printStream.println(REFERENCE_PREFIX + message.toString());
    }

    public static void main(String[] args) {

    }

}
