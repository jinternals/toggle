package com.jinternals.toggle.core.state;

import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.*;

public class PropertiesToggleStateProviderTest {

    private PropertiesToggleStateProvider propertiesToggleStateProvider;

    private Properties properties = new Properties ();

    @Before
    public void setUp() throws Exception {

        this.properties.put("toggle.abc.enabled","true");
        this.properties.put("toggle.xyz.enabled","false");

        this.propertiesToggleStateProvider = new PropertiesToggleStateProvider(this.properties);
    }

    @Test
    public void shouldCheckToggleState() {

        boolean abcToggleState = this.propertiesToggleStateProvider.getState("abc");
        boolean xyzToggleState = this.propertiesToggleStateProvider.getState("xyz");

        assertEquals(true,abcToggleState);
        assertEquals(false,xyzToggleState);

    }
}