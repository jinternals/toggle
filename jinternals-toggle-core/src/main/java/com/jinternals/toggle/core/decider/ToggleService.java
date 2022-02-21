package com.jinternals.toggle.core.decider;

public interface ToggleService {

    Boolean isToggleOn(String toggle);

    Boolean isToggleOff(String toggle);

    boolean isToggleDefined(String toggle);

}
