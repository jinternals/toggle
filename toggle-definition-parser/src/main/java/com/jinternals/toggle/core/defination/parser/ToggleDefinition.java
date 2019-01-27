package com.jinternals.toggle.core.defination.parser;

import com.jinternals.toggle.api.Toggle;

import java.util.ArrayList;
import java.util.List;

public class ToggleDefinition {

    public ToggleDefinition() {
    }

    private List<Toggle> toggles = new ArrayList<>();

    public void setToggles(List<Toggle> toggles) {
        this.toggles = toggles;
    }

    public List<Toggle> getToggles() {
        return toggles;
    }

}
