package io.bumsoft.exception;

public class ResourceNotFoundException extends AbstractBumsoftException {
    public ResourceNotFoundException(Long id) {
        super(id);
    }
}
