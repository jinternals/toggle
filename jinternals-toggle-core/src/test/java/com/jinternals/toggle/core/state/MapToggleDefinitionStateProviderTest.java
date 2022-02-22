package com.jinternals.toggle.core.state;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class MapToggleDefinitionStateProviderTest {

    private MapToggleStateProvider mapToggleStateProvider;

    private Map<String, Boolean> toogleState = new HashMap<>();

    @BeforeEach
     void setUp() throws Exception {

        this.toogleState.put("toggle.toggle1.enabled", true);
        this.toogleState.put("toggle.toggle2.enabled", false);

        mapToggleStateProvider = new MapToggleStateProvider(this.toogleState);
    }

    @Test
    void shouldCheckToggleState() {
        boolean toggle1State = this.mapToggleStateProvider.getState("toggle1");
        boolean toggle2State = this.mapToggleStateProvider.getState("toggle2");

        assertThat(toggle1State).isTrue();
        assertThat(toggle2State).isFalse();

    }
}
