package com.jinternals.toggle.core.state;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MapToggleDefinitionStateProviderTest {

    private MapToggleStateProvider mapToggleStateProvider;

    private Map<String,Boolean> toogleState = new HashMap<>();

    @Before
    public void setUp() throws Exception {

        this.toogleState.put("toggle.toggle1.enabled",true);
        this.toogleState.put("toggle.toggle2.enabled",false);

        mapToggleStateProvider = new MapToggleStateProvider(this.toogleState);
    }

    @Test
    public void shouldCheckToggleState() {
        boolean toggle1State = this.mapToggleStateProvider.getState("toggle1");
        boolean toggle2State = this.mapToggleStateProvider.getState("toggle2");

        assertEquals(true,toggle1State);
        assertEquals(false,toggle2State);
    }
}