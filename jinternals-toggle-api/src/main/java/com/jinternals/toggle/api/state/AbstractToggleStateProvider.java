package com.jinternals.toggle.api.state;

import static com.jinternals.toggle.api.utils.ToggleUtils.toggleName;

public abstract class AbstractToggleStateProvider implements ToggleStateProvider {


    @Override
    public boolean getState(String toggle) {
        return this.getToggleState(toggleName(toggle));
    }

    public abstract boolean getToggleState(String toggle);
}
