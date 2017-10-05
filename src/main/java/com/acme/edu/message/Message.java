package com.acme.edu.message;

import com.acme.edu.saver.SaveNotSuccessfulException;

public interface Message {
    /**
     * The method handles the Message
     *
     * @param previousMessage Message that was handled before this Message
     */
    void handle(Message previousMessage) throws SaveNotSuccessfulException;

    /**
     * The method formats the Message
     *
     * @return formatted String represents this Message
     */
    String format();

    /**
     * saves the Message
     */
    void save() throws SaveNotSuccessfulException;

}
