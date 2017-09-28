package com.acme.edu;


import java.io.PrintStream;
import java.util.Map;
import java.util.TreeMap;
import static java.lang.Math.abs;


enum LoggerState{ ST_DEFAULT, ST_INTEGER, ST_BYTE, ST_STRING, ST_ARRAY }

public class Logger {
    public final static String PRIMITIVE_PREFIX = "primitive: ";
    public final static String REFERENCE_PREFIX = "reference: ";
    public final static String CHAR_PREFIX = "char: ";
    public final static String STRING_PREFIX = "string: ";
    public final static String PRIMITIVES_ARRAY_PREFIX = "primitives array: ";
    private static PrintStream printStream = System.out;
    private static int sumInteger;
    private static String currentString;
    private static int subsequentStringsCount;
    private static byte sumByte;
    private static LoggerState state = LoggerState.ST_DEFAULT;


    public static void log(int message) {
           if(state != LoggerState.ST_INTEGER){
               printBuffer();
           }
           sumInteger = checkOverflow(message);
           state = LoggerState.ST_INTEGER;
    }




    public static void log(byte message) {
        if(state != LoggerState.ST_BYTE){
            printBuffer();
        }
        sumByte = checkOverflow(message);
        state = LoggerState.ST_BYTE;
    }

    public static void log(char message){
        printStream.println(CHAR_PREFIX + message);
    }

    public static void log(String message){
        if(state != LoggerState.ST_STRING || !message.equals(currentString)){
            printBuffer();
            currentString = message;
            subsequentStringsCount = 1;
        } else {
            subsequentStringsCount++;
        }
        state = LoggerState.ST_STRING;
    }

    public static void log(boolean message) {
        printStream.println(PRIMITIVE_PREFIX + message);
    }

    public static void log(Object message) {
        printStream.println(REFERENCE_PREFIX + message.toString());
    }

    public static void log(int[] message){
        printStream.println(PRIMITIVES_ARRAY_PREFIX + arrayToString(message));
    }

    public static void close(){
        printBuffer();
    }

    private static int checkOverflow(int message){
        if(message > 0 && sumInteger > 0){
            if(message > Integer.MAX_VALUE - sumInteger){
                print(PRIMITIVE_PREFIX + Integer.MAX_VALUE);
                return sumInteger - Integer.MAX_VALUE + message;
            }
        } else if(message < 0 && sumInteger < 0){
            if(message < Integer.MIN_VALUE - sumInteger){
                print(PRIMITIVE_PREFIX + Integer.MIN_VALUE);
                return sumInteger - Integer.MIN_VALUE + message;
            }
        }
        return sumInteger + message;
    }

    private static byte checkOverflow(byte message){
        if(message > 0 && sumByte > 0){
            if(message > Byte.MAX_VALUE - sumByte){
                print(PRIMITIVE_PREFIX + Byte.MAX_VALUE);
                return (byte) (sumByte - Byte.MAX_VALUE + message);
            }
        } else if(message < 0 && sumByte < 0){
            if(message < Byte.MIN_VALUE - sumByte){
                print(PRIMITIVE_PREFIX + Byte.MIN_VALUE);
                return (byte) (sumByte - Byte.MIN_VALUE + message);
            }
        }
        return (byte) (sumByte + message);
    }

    private static void printBuffer(){
        switch (state){
            case ST_INTEGER:
                print(PRIMITIVE_PREFIX + sumInteger);
                sumInteger = 0;
                break;
            case ST_BYTE:
                print(PRIMITIVE_PREFIX + sumByte);
                sumByte = 0;
                break;
            case ST_STRING:
                String stringToPrint = STRING_PREFIX + currentString;
                if(subsequentStringsCount > 1){
                    stringToPrint += " (x" + subsequentStringsCount + ")";
                }
                print(stringToPrint);
                break;
            case ST_ARRAY:
                print(PRIMITIVE_PREFIX + sumByte);
                break;
            default:
                break;
        }
    }

    private static void print(String message){
        printStream.println(message);
    }

    private static String arrayToString(int[] array){
        StringBuilder arrayToStringBuilder = new StringBuilder();
        arrayToStringBuilder.append("{");
        for(int i  = 0; i < array.length - 1; i++){
            arrayToStringBuilder.append(array[i]).append(", ");
        }
        arrayToStringBuilder.append(array[array.length-1]).append("}");
        return arrayToStringBuilder.toString();
    }

    public static void main(String[] args) {

    }

}
