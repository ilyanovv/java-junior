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
    public void log(Message currentMessage) {
        currentMessage.handle(previousMessage);
        previousMessage = currentMessage;
    }

    /**
     * The method writes all that has been accumulated in the buffer.
     */
    public void close() {
        previousMessage.save();
        previousMessage = null;
    }
}
