package com.jinternals.toggle.api.decider;

public interface ToggleDecider {

    Boolean isToggleOn(String toggle);

    Boolean isToggleOff(String toggle);

    boolean isToggleDefined(String toggle);

}
