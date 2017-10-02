package com.acme.edu.encoder;

public class SimpleEncoder implements Encoder {
    @Override
    public String encode(String pureString) {
        return pureString;
    }
}
