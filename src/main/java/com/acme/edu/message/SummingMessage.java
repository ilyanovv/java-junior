package com.acme.edu.message;

import com.acme.edu.encoder.Encoder;
import com.acme.edu.saver.Saver;

public abstract class SummingMessage implements Message {
    protected Saver saver;
    protected Encoder encoder;

    @Override
    public void handle(Message previousMessage) {
        if (previousMessage == null) {
            return;
        }
        if (this.getClass() == previousMessage.getClass()) {
            handleIfMessageTypesAreEqual(previousMessage);
        } else {
            previousMessage.save();
        }
    }

    @Override
    public void save() {
        saver.save(encoder.encode(format()));
    }

    abstract protected void handleIfMessageTypesAreEqual(Message previousMessage);

}
