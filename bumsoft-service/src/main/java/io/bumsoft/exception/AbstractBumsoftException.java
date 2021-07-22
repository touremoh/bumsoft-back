package io.bumsoft.exception;

public abstract class AbstractBumsoftException extends Exception implements BumsoftException{
    private static final String MESSAGE = "Unable to find resource with criteria [";
    private final Long id;

    public AbstractBumsoftException(Long id) {
        super();
        this.id = id;
    }

    @Override
    public String getMessage() {
        return MESSAGE + "ID=" + id + "]";
    }
}
