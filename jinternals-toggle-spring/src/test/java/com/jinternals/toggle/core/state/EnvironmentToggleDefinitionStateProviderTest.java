package com.jinternals.toggle.core.state;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EnvironmentToggleDefinitionStateProviderTest {

    private EnvironmentFeatureToggleStateProvider provider;

    @Mock
    private Environment environment;

    @Before
    public void setUp() throws Exception {
        provider = new EnvironmentFeatureToggleStateProvider(environment);
    }

    @Test
    public void shouldReturnTrueIfToggleIsSetTrue() {
        when(environment.getProperty("toggle.some-toggle.enabled")).thenReturn("true");

        boolean result = provider.getState("some-toggle");

        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseIfToggleIsSetFalse() {
        when(environment.getProperty("toggle.some-toggle.enabled")).thenReturn("false");

        boolean result = provider.getState("some-toggle");

        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseIfToggleIsNotDefine() {
        when(environment.getProperty("toggle.some-toggle.enabled")).thenReturn(null);

        boolean result = provider.getState("some-toggle");

        assertThat(result).isFalse();
    }
}