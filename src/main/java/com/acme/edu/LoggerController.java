package com.acme.edu;

import com.acme.edu.formatter.*;
import com.acme.edu.message.*;
import com.acme.edu.saver.*;

public class LoggerController {
    private Message previousMessage = null;

    /**
     * The method prepares int or the sum of sequential ints for writing.
     *
     * @param currentMessage The int to be written or summed up.
     */
    public void log(Message currentMessage) {
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
