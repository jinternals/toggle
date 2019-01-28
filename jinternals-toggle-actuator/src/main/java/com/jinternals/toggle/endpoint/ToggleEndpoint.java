package com.jinternals.toggle.endpoint;

import com.jinternals.toggle.core.repository.ToggleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.HashMap;
import java.util.Map;

@Endpoint(id = "toggle")
public class ToggleEndpoint {

    @Autowired
    private ToggleRepository toggleRepository;

    @ReadOperation
    public Map<String, Object> invoke() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("available", toggleRepository.getAllToggles());
        map.put("enabled", toggleRepository.getAllEnabledToggles());
        return map;
    }

}
