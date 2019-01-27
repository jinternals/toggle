package com.jinternals.toggle.api.state;

import com.jinternals.toggle.api.constants.ToggleConstants;

import static java.lang.String.format;

public abstract class AbstractToggleStateProvider implements ToggleStateProvider {


    @Override
    public boolean getState(String toggle) {
        return this.getToggleState(format("%s.%s.%s", ToggleConstants.PREFIX,toggle,ToggleConstants.SUFFIX));
    }

    public abstract boolean getToggleState(String toggle);
}
