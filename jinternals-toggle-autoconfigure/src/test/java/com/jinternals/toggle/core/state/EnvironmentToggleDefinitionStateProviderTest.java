package com.jinternals.toggle.core.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnvironmentToggleDefinitionStateProviderTest {

    private EnvironmentToggleStateProvider provider;

    @Mock
    private Environment environment;

    @BeforeEach
    public void setUp() throws Exception {
        provider = new EnvironmentToggleStateProvider(environment);
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
