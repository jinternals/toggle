package com.jinternals.toggle.core.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;


public class PropertiesToggleDefinitionStateProviderTest {

    private PropertiesToggleStateProvider propertiesToggleStateProvider;

    private Properties properties = new Properties();

    @BeforeEach
    public void setUp() throws Exception {

        this.properties.put("toggle.abc.enabled", "true");
        this.properties.put("toggle.xyz.enabled", "false");

        this.propertiesToggleStateProvider = new PropertiesToggleStateProvider(this.properties);
    }

    @Test
    public void shouldCheckToggleState() {

        boolean abcToggleState = this.propertiesToggleStateProvider.getState("abc");
        boolean xyzToggleState = this.propertiesToggleStateProvider.getState("xyz");

        assertThat(abcToggleState).isTrue();
        assertThat(xyzToggleState).isFalse();


    }
}
