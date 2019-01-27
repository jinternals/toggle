package com.jinternals.toggle.core.repository.impl;

import com.jinternals.toggle.api.Toggle;
import com.jinternals.toggle.api.definition.ToggleDefinitionProvider;
import com.jinternals.toggle.api.state.ToggleStateProvider;
import com.jinternals.toggle.core.repository.DefaultToggleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.jinternals.toggle.api.Toggle.State.WIP;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultToggleRepositoryTest {

    private DefaultToggleRepository toggleRepository;
    @Mock
    private ToggleDefinitionProvider toggleDefinitionProvider;
    @Mock
    private ToggleStateProvider toggleStateProvider;

    @Before
    public void setUp() {

        List<Toggle> toggles = Arrays.asList(
                new Toggle("demo.toggle1", "Demo Toggle1", WIP),
                new Toggle("demo.toggle2", "Demo Toggle2", WIP)
        );


        when(toggleDefinitionProvider.getToggles()).thenReturn(toggles);

        when(toggleStateProvider.getState("demo.toggle1")).thenReturn(TRUE);
        when(toggleStateProvider.getState("demo.toggle2")).thenReturn(TRUE);

        this.toggleRepository = new DefaultToggleRepository(toggleDefinitionProvider, toggleStateProvider);
    }

    @Test
    public void shouldCheckToggleIsOn() {

        boolean isToggleOn = this.toggleRepository.isToggleOn("demo.toggle1");

        assertEquals(isToggleOn, TRUE);
    }

    @Test
    public void shouldCheckToggleIsDefined() {

        boolean isToggleOn = this.toggleRepository.isDefined("demo.toggle1");

        assertEquals(isToggleOn, TRUE);
    }

    @Test
    public void shouldCheckToggleIsNotDefined() {

        boolean isToggleOn = this.toggleRepository.isDefined("undefined.toggle");

        assertEquals(isToggleOn, FALSE);
    }

    @Test
    public void shouldReturnedAllTheDefinedToggle() {

        Set<String> allEnabledToggles = this.toggleRepository.getAllEnabledToggles();

        assertEquals(allEnabledToggles.size(), 2);
        assertEquals(allEnabledToggles.contains("demo.toggle1"), TRUE);
        assertEquals(allEnabledToggles.contains("demo.toggle2"), TRUE);
    }
}