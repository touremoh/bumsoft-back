package io.bumsoft.exception;


public class UserNotFoundException extends Exception implements BumsoftException {
    private static final String MESSAGE = "User not found with ID";
    private final Long userId;

    public UserNotFoundException(Long userId) {
        super();
        this.userId = userId;
    }
    @Override
    public String getMessage() {
        return MESSAGE + "["+userId+"]";
    }
}
