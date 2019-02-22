package com.jinternals.toggle.core.defination.parser;

import com.jinternals.toggle.api.definition.ToggleDefinition;

import java.util.HashSet;
import java.util.Set;

public class ToggleDefinitions {

    public ToggleDefinitions() {
    }

    private Set<ToggleDefinition> toggles = new HashSet<>();

    public Set<ToggleDefinition> getToggles() {
        return toggles;
    }

    public void setToggles(Set<ToggleDefinition> toggles) {
        this.toggles = toggles;
    }
}
