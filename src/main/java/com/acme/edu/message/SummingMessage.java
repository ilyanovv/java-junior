package com.acme.edu.message;

public abstract class SummingMessage implements Message{
     public void handle(Message previousMessage){
        if(previousMessage == null){
            return;
        }
        if(this.getClass() == previousMessage.getClass()){
            handleIfMessageTypesAreEqual(previousMessage);
        } else {
            previousMessage.save();
        }
    }

    abstract protected void handleIfMessageTypesAreEqual(Message previousMessage);

}
