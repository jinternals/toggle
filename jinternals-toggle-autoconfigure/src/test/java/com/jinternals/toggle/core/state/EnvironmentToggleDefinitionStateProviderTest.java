package com.jinternals.toggle.core.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnvironmentToggleDefinitionStateProviderTest {

    private EnvironmentToggleStateProvider provider;

    @Mock
    private Environment environment;

    @BeforeEach
    public void setUp() {
        provider = new EnvironmentToggleStateProvider(environment);
    }

    @Test
    void shouldReturnTrueIfToggleIsSetTrue() {
        when(environment.getProperty("toggle.some-toggle.enabled", Boolean.class, false)).thenReturn(true);

        boolean result = provider.getState("some-toggle");

        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseIfToggleIsSetFalse() {
        when(environment.getProperty("toggle.some-toggle.enabled", Boolean.class, false)).thenReturn(false);

        boolean result = provider.getState("some-toggle");

        assertThat(result).isFalse();
    }

}
