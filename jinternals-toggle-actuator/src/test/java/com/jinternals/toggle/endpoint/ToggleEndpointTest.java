package com.jinternals.toggle.endpoint;


import com.jinternals.toggle.api.Toggle;
import com.jinternals.toggle.core.repository.DefaultToggleRepository;
import com.jinternals.toggle.core.repository.ToggleRepository;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class ToggleEndpointTest {

    @Autowired
    private ToggleEndpoint toggleEndpoint;

    @Autowired
    private ToggleRepository toggleRepository;

    @Test
    public void shouldTestEndpoint() {

        Map<String, Toggle> toggles = new HashMap<>();
        toggles.put("some.toggle",new Toggle("some.toggle","some.toggle.description", Toggle.State.DEV));

        Set<String> enabledToggles = new HashSet<>();
        enabledToggles.add("some.toggle");

        when(toggleRepository.getAllToggles()).thenReturn(toggles);
        when(toggleRepository.getAllEnabledToggles()).thenReturn(enabledToggles);

        Map<String, Object> result = toggleEndpoint.invoke();

        assertThat(result).containsKeys("enabled");
        assertThat((Set)result.get("enabled")).contains("some.toggle");

        assertThat(result).containsKeys("available");
        assertThat((Map<String,Toggle>)result.get("available")).containsKeys("some.toggle");

    }
}