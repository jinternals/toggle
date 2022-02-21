package com.jinternals.toggle.core.services;

public interface ToggleService {

    boolean isToggleOn(String toggle);

    boolean isToggleOff(String toggle);

    boolean isToggleDefined(String toggle);

}
