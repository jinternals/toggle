package com.jinternals.toggle.test;

public enum  MockToggleValue {

    TRUE(true),
    FALSE(false);

    private boolean value;

    MockToggleValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
