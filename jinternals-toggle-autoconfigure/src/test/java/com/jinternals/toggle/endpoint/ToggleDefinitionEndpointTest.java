package com.jinternals.toggle.endpoint;


import com.jinternals.toggle.core.definition.ToggleDefinition;
import com.jinternals.toggle.core.repository.ToggleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class ToggleDefinitionEndpointTest {

    @Autowired
    private ToggleEndpoint toggleEndpoint;

    @Autowired
    private ToggleRepository toggleRepository;

    @Test
    public void shouldTestEndpoint() {

        Map<String, ToggleDefinition> toggles = new HashMap<>();
        toggles.put("some.toggle",new ToggleDefinition("some.toggle","some.toggle.description", ToggleDefinition.State.DEV));

        Set<String> enabledToggles = new HashSet<>();
        enabledToggles.add("some.toggle");

        when(toggleRepository.getAllToggles()).thenReturn(toggles);
        when(toggleRepository.getAllEnabledToggles()).thenReturn(enabledToggles);

        Map<String, Object> result = toggleEndpoint.invoke();

        assertThat(result).containsKeys("enabled");
        assertThat((Set)result.get("enabled")).contains("some.toggle");

        assertThat(result).containsKeys("available");
        assertThat((Map<String, ToggleDefinition>)result.get("available")).containsKeys("some.toggle");

    }
}
