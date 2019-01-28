package com.jinternals.toggle.test;

public enum ToggleValue {

    TRUE(true),
    FALSE(false);

    private boolean value;

    ToggleValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
