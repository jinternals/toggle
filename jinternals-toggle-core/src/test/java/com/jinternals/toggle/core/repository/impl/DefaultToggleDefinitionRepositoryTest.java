package com.jinternals.toggle.core.repository.impl;

import com.jinternals.toggle.core.definition.ToggleDefinition;
import com.jinternals.toggle.core.definition.ToggleDefinitionProvider;
import com.jinternals.toggle.core.repository.DefaultToggleRepository;
import com.jinternals.toggle.core.state.ToggleStateProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static com.jinternals.toggle.core.definition.ToggleDefinition.State.WIP;
import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultToggleDefinitionRepositoryTest {

    private DefaultToggleRepository toggleRepository;
    @Mock
    private ToggleDefinitionProvider toggleDefinitionProvider;
    @Mock
    private ToggleStateProvider toggleStateProvider;

    @BeforeEach
    public void setUp() {

        Set<ToggleDefinition> toggleDefinitions = Sets.newSet(
                new ToggleDefinition("demo.toggle1", "Demo Toggle1", WIP),
                new ToggleDefinition("demo.toggle2", "Demo Toggle2", WIP)
        );


        when(toggleDefinitionProvider.getToggleDefinitions()).thenReturn(toggleDefinitions);


        this.toggleRepository = new DefaultToggleRepository(toggleDefinitionProvider, toggleStateProvider);
    }

    @Test
    public void shouldCheckToggleIsOn() {
        when(toggleStateProvider.getState("demo.toggle1")).thenReturn(TRUE);
        when(toggleStateProvider.getState("demo.toggle2")).thenReturn(TRUE);

        boolean isToggleOn = this.toggleRepository.isToggleOn("demo.toggle1");

        assertThat(isToggleOn).isTrue();
    }

    @Test
    public void shouldCheckToggleIsDefined() {

        boolean isToggleOn = this.toggleRepository.isDefined("demo.toggle1");

        assertThat(isToggleOn).isTrue();

    }

    @Test
    public void shouldCheckToggleIsNotDefined() {

        boolean isToggleOn = this.toggleRepository.isDefined("undefined.toggle");

        assertThat(isToggleOn).isFalse();
    }

    @Test
    public void shouldReturnedAllTheDefinedToggle() {
        when(toggleStateProvider.getState("demo.toggle1")).thenReturn(TRUE);
        when(toggleStateProvider.getState("demo.toggle2")).thenReturn(TRUE);

        Set<String> allEnabledToggles = this.toggleRepository.getAllEnabledToggles();

        assertThat(allEnabledToggles).hasSize(2);
        assertThat(allEnabledToggles.contains("demo.toggle1")).isTrue();
        assertThat(allEnabledToggles.contains("demo.toggle2")).isTrue();

    }
}
