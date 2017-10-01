package com.acme.edu.formatter;

public class SimpleFormatter implements Formatter {
    @Override
    public String format(String pureMessage) {
        return pureMessage;
    }
}
