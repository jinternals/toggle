package com.jinternals.toggle.core.services;

import com.jinternals.toggle.core.repository.ToggleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DefaultServiceTest {

    ToggleService toggleService;

    @Mock
    ToggleRepository toggleRepository;

    @BeforeEach
    void setUp() {
        toggleService = new DefaultService(toggleRepository);
    }

    @Test
    void shouldVerifyToggleOn() {
        Mockito.when(toggleRepository.isToggleOn("some-toggle")).thenReturn(true);
        Boolean toggleOn = toggleService.isToggleOn("some-toggle");

        assertThat(toggleOn).isTrue();
        Mockito.verify(toggleRepository).isToggleOn("some-toggle");
    }

    @Test
    void shouldVerifyToggleOff() {
        Mockito.when(toggleRepository.isToggleOn("some-toggle")).thenReturn(false);
        Boolean toggleOff = toggleService.isToggleOff("some-toggle");

        assertThat(toggleOff).isTrue();
        Mockito.verify(toggleRepository).isToggleOn("some-toggle");
    }

    @Test
    void shouldVerifyToggleDefine() {
        Mockito.when(toggleRepository.isDefined("some-toggle")).thenReturn(true);
        Boolean toggleDefined= toggleService.isToggleDefined("some-toggle");

        assertThat(toggleDefined).isTrue();
        Mockito.verify(toggleRepository).isDefined("some-toggle");
    }

    @Test
    void shouldVerifyToggleNotDefine() {
        Mockito.when(toggleRepository.isDefined("some-toggle")).thenReturn(false);
        Boolean toggleDefined= toggleService.isToggleDefined("some-toggle");

        assertThat(toggleDefined).isFalse();
        Mockito.verify(toggleRepository).isDefined("some-toggle");
    }
}
