package com.jinternals.toggle.core.configuration;

import com.jinternals.toggle.core.decider.DefaultToggleDecider;
import com.jinternals.toggle.core.repository.ToggleRepository;
import com.jinternals.toggle.core.repository.DefaultToggleRepository;
import com.jinternals.toggle.api.decider.ToggleDecider;
import com.jinternals.toggle.api.definition.ToggleDefinitionProvider;
import com.jinternals.toggle.api.state.ToggleStateProvider;

public class ToggleConfiguration {

    private ToggleDefinitionProvider toggleDefinitionProvider;
    private ToggleRepository toggleRepository;
    private ToggleStateProvider toggleStateProvider;


    public ToggleConfiguration setToggleDefinitionProvider(ToggleDefinitionProvider toggleDefinitionProvider) {
        this.toggleDefinitionProvider = toggleDefinitionProvider;
        return this;
    }

    public ToggleConfiguration setToggleStateProvider(ToggleStateProvider toggleStateProvider) {
        this.toggleStateProvider = toggleStateProvider;
        return this;
    }

    public ToggleConfiguration configure() {

        if (toggleStateProvider == null || toggleDefinitionProvider == null) {
            throw new RuntimeException("Please set toggleStateProvider and toggleDefinitionProvider");
        }

        toggleRepository = new DefaultToggleRepository(toggleDefinitionProvider, toggleStateProvider);
        return this;
    }

    public ToggleDecider toggleDecider() {

        if (toggleStateProvider == null || toggleDefinitionProvider == null) {
            throw new RuntimeException("Please set toggleStateProvider and toggleDefinitionProvider");
        }

        return new DefaultToggleDecider(toggleRepository);
    }

}
