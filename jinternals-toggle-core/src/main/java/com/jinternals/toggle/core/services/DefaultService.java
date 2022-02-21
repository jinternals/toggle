package com.jinternals.toggle.core.services;

import com.jinternals.toggle.core.repository.ToggleRepository;


public class DefaultService implements ToggleService {

    private ToggleRepository toggleRepository;

    public DefaultService(ToggleRepository toggleRepository) {
        this.toggleRepository = toggleRepository;
    }

    @Override
    public boolean isToggleOn(String toggle) {
        return toggleRepository.isToggleOn(toggle);
    }

    @Override
    public boolean isToggleOff(String toggle) {
        return !toggleRepository.isToggleOn(toggle);
    }

    @Override
    public boolean isToggleDefined(String key) {
        return toggleRepository.isDefined(key);
    }

}
