package com.acme.edu;

import com.acme.edu.encoder.SimpleEncoder;
import com.acme.edu.message.*;
import com.acme.edu.saver.ConsoleSaver;

import static java.lang.Math.abs;

public class TypedSummingLogger {
    private static LoggerController loggerController = new LoggerController();

    /**
     * The method prepares int or the sum of sequential ints for writing.
     *
     * @param message The int to be written or summed up.
     */
    public static void log(int message) throws LoggerException {
        validate(message);
        Message currentMessage = new IntegerMessage(message, new ConsoleSaver(), new SimpleEncoder());
        loggerController.log(currentMessage);
    }

    /**
     * The method prepares byte or the sum of sequential bytes for writing.
     *
     * @param message The byte to be written or summed up.
     */
    public static void log(byte message) throws LoggerException {
        validate(message);
        Message currentMessage = new ByteMessage(message, new ConsoleSaver(), new SimpleEncoder());
        loggerController.log(currentMessage);
    }

    /**
     * The method prepares char for writing.
     *
     * @param message The char to be written.
     */
    public static void log(char message) throws LoggerException {
        validate(message);
        Message currentMessage = new CharMessage(message, new ConsoleSaver(), new SimpleEncoder());
        loggerController.log(currentMessage);
    }

    /**
     * The method prepares sequential Strings for writing.
     * If the length of the sequence is equal to 1, only String will be written.
     * If the length of the sequence is more than 1, the repetitive String and the length of the sequence will be
     * written.
     *
     * @param message The String to be written.
     */
    public static void log(String message) throws LoggerException {
        validate(message);
        Message currentMessage = new StringMessage(message, new ConsoleSaver(), new SimpleEncoder());
        loggerController.log(currentMessage);
    }

    /**
     * The method prepares boolean for writing.
     *
     * @param message The boolean to be written.
     */
    public static void log(boolean message) throws LoggerException {
        validate(message);
        Message currentMessage = new BooleanMessage(message, new ConsoleSaver(), new SimpleEncoder());
        loggerController.log(currentMessage);
    }

    /**
     * The method prepares object reference for writing.
     *
     * @param message The object reference to be written.
     */
    public static void log(Object message) throws LoggerException {
        validate(message);
        Message currentMessage = new ReferenceMessage(message, new ConsoleSaver(), new SimpleEncoder());
        loggerController.log(currentMessage);
    }

    /**
     * The method prepares array of ints for writing into console.
     *
     * @param message The array of ints to be printed.
     */
    public static void log(int[] message) throws LoggerException {
        validate(message);
        Message currentMessage = new PrimitivesArrayMessage(message, new ConsoleSaver(), new SimpleEncoder());
        loggerController.log(currentMessage);
    }

    /**
     * The method writes all that has been accumulated in the buffer into console.
     */
    public static void close() throws LoggerException {
        loggerController.close();
    }

    private static void validate(Object argument) throws LoggerException {
        if (argument == null){
            throw new LoggerException(LoggerException.NULL_POINTER_ARGUMENT_MESSAGE, new IllegalArgumentException());
        }
    }
}
