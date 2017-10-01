package com.acme.edu;

import com.acme.edu.formatter.*;
import com.acme.edu.message.*;
import com.acme.edu.saver.*;

public class LoggerController {
    private Message previousMessage = null;

    /**
     * The method prepares int or the sum of sequential ints for writing.
     *
     * @param message The int to be written or summed up.
     */
    public void log(int message) {
        IntegerMessage currentMessage = new IntegerMessage(message, new SimpleFormatter(), new ConsoleSaver());
        currentMessage.handle(previousMessage);
        previousMessage = currentMessage;
    }

    /**
     * The method prepares byte or the sum of sequential bytes for writing.
     *
     * @param message The byte to be written or summed up.
     */
    public void log(byte message) {
        ByteMessage currentMessage = new ByteMessage(message, new SimpleFormatter(), new ConsoleSaver());
        currentMessage.handle(previousMessage);
        previousMessage = currentMessage;
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
        StringMessage currentMessage = new StringMessage(message, new SimpleFormatter(), new ConsoleSaver());
        currentMessage.handle(previousMessage);
        previousMessage = currentMessage;
    }


    /**
     * The method prepares array of ints for writing into console.
     *
     * @param message The array of ints to be printed.
     */
    public void log(int[] message) {
        PrimitivesArrayMessage currentMessage = new PrimitivesArrayMessage(message,
                new SimpleFormatter(),
                new ConsoleSaver());
        currentMessage.handle(previousMessage);
        previousMessage = currentMessage;
    }


    /**
     * The method prepares char for writing.
     *
     * @param message The char to be written.
     */
    public void log(char message) {
        CharMessage currentMessage = new CharMessage(message, new SimpleFormatter(), new ConsoleSaver());
        currentMessage.handle(previousMessage);
        previousMessage = currentMessage;
    }

    /**
     * The method prepares boolean for writing.
     *
     * @param message The boolean to be written.
     */
    public void log(boolean message) {
        BooleanMessage currentMessage = new BooleanMessage(message, new SimpleFormatter(), new ConsoleSaver());
        currentMessage.handle(previousMessage);
        previousMessage = currentMessage;
    }

    /**
     * The method prepares object reference for writing.
     *
     * @param message The object reference to be written.
     */
    public void log(Object message) {
        ReferenceMessage currentMessage = new ReferenceMessage(message, new SimpleFormatter(), new ConsoleSaver());
        currentMessage.handle(previousMessage);
        previousMessage = currentMessage;
    }

    /**
     * The method writes all that has been accumulated in the buffer into console.
     */
    public void close() {
        previousMessage.save();
        previousMessage = null;
    }
}
