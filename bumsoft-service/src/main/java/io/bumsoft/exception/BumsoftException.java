package io.bumsoft.exception;


import java.util.List;

public class BumsoftException extends Exception {
    public BumsoftException(String message) {
        super(message);
    }
    public BumsoftException(Throwable e) {
        super(e);
    }
    public BumsoftException(List<String> s) {
        super(s.toString());
    }
}
