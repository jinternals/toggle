package com.jinternals.toggle.core.state;

import com.jinternals.toggle.core.utils.ToggleUtils;

public abstract class AbstractToggleStateProvider implements ToggleStateProvider {


    @Override
    public boolean getState(String toggle) {
        return this.getToggleState(ToggleUtils.toggleName(toggle));
    }

    public abstract boolean getToggleState(String toggle);
}
