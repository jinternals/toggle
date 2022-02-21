package com.jinternals.toggle.exception;

public class ToggleNotDefinedException extends RuntimeException{
    private final String toggleName;
    public ToggleNotDefinedException(String toggleName, String message) {
        super(message);
        this.toggleName = toggleName;
    }

    public String getToggleName() {
        return toggleName;
    }
}
