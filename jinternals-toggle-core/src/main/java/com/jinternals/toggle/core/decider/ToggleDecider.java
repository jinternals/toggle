package com.jinternals.toggle.core.decider;

public interface ToggleDecider {

    Boolean isToggleOn(String toggle);

    Boolean isToggleOff(String toggle);

    boolean isToggleDefined(String toggle);

}
