package com.jinternals.toggle.core.repository;

import com.jinternals.toggle.api.Toggle;

import java.util.Map;
import java.util.Set;

public interface ToggleRepository {

    public Map<String, Toggle> getAllToggles();

    public Set<String> getAllEnabledToggles();

    public Boolean isToggleOn(String key);

    public boolean isDefined(String key);

}
