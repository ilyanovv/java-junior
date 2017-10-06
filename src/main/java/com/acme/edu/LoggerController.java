package com.acme.edu;

import com.acme.edu.message.*;
import com.acme.edu.saver.*;

public class LoggerController {
    private Message previousMessage = null;

    /**
     * The method logs the Message.
     *
     * @param currentMessage The Message to be logged
     */
    public void log(Message currentMessage) throws LoggerException {
        try {
            currentMessage.handle(previousMessage);
        } catch (SaveNotSuccessfulException e) {
            throw new LoggerException(e);
        }
        previousMessage = currentMessage;
    }

    /**
     * The method writes all that has been accumulated in the buffer.
     */
    public void close() throws LoggerException {
        try {
            previousMessage.save();
        } catch (SaveNotSuccessfulException e) {
            throw new LoggerException(e);
        }
        previousMessage = null;
    }
}
