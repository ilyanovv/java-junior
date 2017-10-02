package com.acme.edu.message;

public interface Message {
    /**
     * The method handles the Message
     *
     * @param previousMessage Message that was handled before this Message
     */
    void handle(Message previousMessage);

    /**
     * The method formats the Message
     *
     * @return formatted String represents this Message
     */
    String format();

    /**
     * saves the Message
     */
    void save();

}
