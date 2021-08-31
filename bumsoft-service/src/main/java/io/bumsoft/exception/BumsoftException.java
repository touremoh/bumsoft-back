package io.bumsoft.exception;


public class BumsoftException extends Exception {
    public BumsoftException(String message) {
        super(message);
    }
    public BumsoftException(Throwable e) {
        super(e);
    }
}
