package com.jinternals.toggle.core.repository;

import com.jinternals.toggle.core.definition.ToggleDefinition;

import java.util.Map;
import java.util.Set;

public interface ToggleRepository {

    public Map<String, ToggleDefinition> getAllToggles();

    public Set<String> getAllEnabledToggles();

    public boolean isToggleOn(String key);

    public boolean isDefined(String key);

}
