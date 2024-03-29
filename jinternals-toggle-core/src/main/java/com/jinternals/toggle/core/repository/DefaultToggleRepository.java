package com.jinternals.toggle.core.repository;


import com.jinternals.toggle.core.definition.ToggleDefinition;
import com.jinternals.toggle.core.definition.ToggleDefinitionProvider;
import com.jinternals.toggle.core.state.ToggleStateProvider;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

public class DefaultToggleRepository implements ToggleRepository {

    private Map<String, ToggleDefinition> toggles;
    private final ToggleStateProvider toggleStateProvider;
    private final ToggleDefinitionProvider toggleDefinitionProvider;

    public DefaultToggleRepository(ToggleDefinitionProvider toggleDefinitionProvider, ToggleStateProvider toggleStateProvider) {
        this.toggleStateProvider = toggleStateProvider;
        this.toggleDefinitionProvider = toggleDefinitionProvider;
    }

    public Map<String, ToggleDefinition> getAllToggles() {
        if (isNull(toggles)) {
            toggles = toggleDefinitionProvider
                    .getToggleDefinitions()
                    .stream()
                    .collect(toMap(ToggleDefinition::getName, toggle -> toggle));
        }
        return toggles;
    }

    public Set<String> getAllEnabledToggles() {
        return getAllToggles()
                .keySet()
                .stream()
                .filter(f -> isToggleOn(f))
                .collect(toSet());
    }

    public boolean isToggleOn(String key) {
        return toggleStatus().getOrDefault(key, false);
    }


    public boolean isDefined(String key) {
        return getAllToggles().containsKey(key);
    }


    private Map<String, Boolean> toggleStatus() {
        return getAllToggles().keySet()
                .stream()
                .collect(toMap(toggleName -> toggleName, toggleState()));
    }

    private Function<String, Boolean> toggleState() {
        return toggleName -> toggleStateProvider.getState(toggleName);
    }
}

