package com.jinternals.toggle.test;

import com.jinternals.toggle.core.decider.ToggleService;
import com.jinternals.toggle.test.rules.ToggleExtension;
import com.jinternals.toggle.test.rules.annotations.GivenToggle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.jinternals.toggle.test.ToggleValue.FALSE;
import static com.jinternals.toggle.test.ToggleValue.TRUE;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(ToggleExtension.class)
public class ToggleDefinitionRuleTest {



    @Test
    @GivenToggle(name = "SOME.TOGGLE1", value = TRUE)
    @GivenToggle(name = "SOME.TOGGLE2", value = TRUE)
    public void shouldTestMultipleToggleIsEnabled(ToggleService toggleService) {

        Boolean toggle1On = toggleService.isToggleOn("SOME.TOGGLE1");
        Boolean toggle1OFF = toggleService.isToggleOff("SOME.TOGGLE1");
        Boolean toggle2On = toggleService.isToggleOn("SOME.TOGGLE2");
        Boolean toggle2OFF = toggleService.isToggleOff("SOME.TOGGLE2");

        assertThat(toggle1On).isTrue();
        assertThat(toggle1OFF).isFalse();

        assertThat(toggle2On).isTrue();
        assertThat(toggle2OFF).isFalse();

    }

    @Test
    @GivenToggle(name = "SOME.TOGGLE1", value = FALSE)
    @GivenToggle(name = "SOME.TOGGLE2", value = FALSE)
    public void shouldTestMultipleToggleIsDisabled(ToggleService toggleService) {
        Boolean toggle1On = toggleService.isToggleOn("SOME.TOGGLE1");
        Boolean toggle1OFF = toggleService.isToggleOff("SOME.TOGGLE1");
        Boolean toggle2On = toggleService.isToggleOn("SOME.TOGGLE2");
        Boolean toggle2OFF = toggleService.isToggleOff("SOME.TOGGLE2");

        assertThat(toggle1On).isFalse();
        assertThat(toggle1OFF).isTrue();

        assertThat(toggle2On).isFalse();
        assertThat(toggle2OFF).isTrue();

    }

    @Test
    @GivenToggle(name = "SOME.TOGGLE1", value = TRUE)
    @GivenToggle(name = "SOME.TOGGLE2", value = FALSE)
    public void shouldTestMultipleToggleIsEnabledAndDisabled(ToggleService toggleService) {
        Boolean toggle1On = toggleService.isToggleOn("SOME.TOGGLE1");
        Boolean toggle1OFF = toggleService.isToggleOff("SOME.TOGGLE1");
        Boolean toggle2On = toggleService.isToggleOn("SOME.TOGGLE2");
        Boolean toggle2OFF = toggleService.isToggleOff("SOME.TOGGLE2");

        assertThat(toggle1On).isTrue();
        assertThat(toggle1OFF).isFalse();

        assertThat(toggle2On).isFalse();
        assertThat(toggle2OFF).isTrue();
    }

    @Test
    @GivenToggle(name = "SOME.TOGGLE1", value = TRUE)
    public void shouldTestToggleIsEnabled(ToggleService toggleService) {
        Boolean toggle1On = toggleService.isToggleOn("SOME.TOGGLE1");
        Boolean toggle1OFF = toggleService.isToggleOff("SOME.TOGGLE1");

        assertThat(toggle1On).isTrue();
        assertThat(toggle1OFF).isFalse();

    }
}
