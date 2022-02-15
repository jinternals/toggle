package com.jinternals.toggle.core.decider;

import com.jinternals.toggle.core.repository.ToggleRepository;


public class DefaultToggleDecider implements ToggleDecider {

    private ToggleRepository toggleRepository;

    public DefaultToggleDecider(ToggleRepository toggleRepository) {
        this.toggleRepository = toggleRepository;
    }

    @Override
    public Boolean isToggleOn(String toggle) {
        return toggleRepository.isToggleOn(toggle);
    }

    @Override
    public Boolean isToggleOff(String toggle) {
        return !toggleRepository.isToggleOn(toggle);
    }

    @Override
    public boolean isToggleDefined(String key) {
        return toggleRepository.isDefined(key);
    }

}
