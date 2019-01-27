package com.jinternals.toggle.core.state;

import com.jinternals.toggle.api.state.AbstractToggleStateProvider;

import java.util.Map;

public class MapToggleStateProvider extends AbstractToggleStateProvider {

    private Map<String, Boolean> toggleState;

    public MapToggleStateProvider(Map<String, Boolean> toggleState) {
        this.toggleState = toggleState;
    }

    @Override
    public boolean getToggleState(String toggle) {
        return toggleState.get(toggle);
    }

}
