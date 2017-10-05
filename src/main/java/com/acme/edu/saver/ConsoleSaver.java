package com.acme.edu.saver;

public class ConsoleSaver implements Saver {
    /**
     * @param message the String to be written into the console
     */
    @Override
    public void save(String message) {
        System.out.println(message);
    }
}
