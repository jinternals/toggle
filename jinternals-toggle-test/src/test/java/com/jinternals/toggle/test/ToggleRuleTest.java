package com.jinternals.toggle.test;

import com.jinternals.toggle.test.rules.ToggleRule;
import com.jinternals.toggle.test.rules.annotations.GivenToggle;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import static com.jinternals.toggle.test.ToggleValue.FALSE;
import static com.jinternals.toggle.test.ToggleValue.TRUE;

public class ToggleRuleTest {

    @Rule
    public ToggleRule toggleRule = new ToggleRule();

    @Test
    @GivenToggle(name = "SOME.TOGGLE1", value = TRUE)
    @GivenToggle(name = "SOME.TOGGLE2", value = TRUE)
    public void shouldTestMultipleToggleIsEnabled() {

        Boolean toggle1On = toggleRule.getToggleDecider().isToggleOn("SOME.TOGGLE1");
        Boolean toggle1OFF = toggleRule.getToggleDecider().isToggleOff("SOME.TOGGLE1");
        Boolean toggle2On = toggleRule.getToggleDecider().isToggleOn("SOME.TOGGLE2");
        Boolean toggle2OFF = toggleRule.getToggleDecider().isToggleOff("SOME.TOGGLE2");

        Assert.assertTrue(toggle1On);
        Assert.assertFalse(toggle1OFF);

        Assert.assertTrue(toggle2On);
        Assert.assertFalse(toggle2OFF);
    }

    @Test
    @GivenToggle(name = "SOME.TOGGLE1", value = FALSE)
    @GivenToggle(name = "SOME.TOGGLE2", value = FALSE)
    public void shouldTestMultipleToggleIsDisabled() {
        Boolean toggle1On = toggleRule.getToggleDecider().isToggleOn("SOME.TOGGLE1");
        Boolean toggle1OFF = toggleRule.getToggleDecider().isToggleOff("SOME.TOGGLE1");
        Boolean toggle2On = toggleRule.getToggleDecider().isToggleOn("SOME.TOGGLE2");
        Boolean toggle2OFF = toggleRule.getToggleDecider().isToggleOff("SOME.TOGGLE2");

        Assert.assertFalse(toggle1On);
        Assert.assertTrue(toggle1OFF);

        Assert.assertFalse(toggle2On);
        Assert.assertTrue(toggle2OFF);
    }

    @Test
    @GivenToggle(name = "SOME.TOGGLE1", value = TRUE)
    @GivenToggle(name = "SOME.TOGGLE2", value = FALSE)
    public void shouldTestMultipleToggleIsEnabledAndDisabled() {
        Boolean toggle1On = toggleRule.getToggleDecider().isToggleOn("SOME.TOGGLE1");
        Boolean toggle1OFF = toggleRule.getToggleDecider().isToggleOff("SOME.TOGGLE1");
        Boolean toggle2On = toggleRule.getToggleDecider().isToggleOn("SOME.TOGGLE2");
        Boolean toggle2OFF = toggleRule.getToggleDecider().isToggleOff("SOME.TOGGLE2");

        Assert.assertTrue(toggle1On);
        Assert.assertFalse(toggle1OFF);

        Assert.assertFalse(toggle2On);
        Assert.assertTrue(toggle2OFF);
    }

    @Test
    @GivenToggle(name = "SOME.TOGGLE1", value = TRUE)
    public void shouldTestToggleIsEnabled() {
        Boolean toggle1On = toggleRule.getToggleDecider().isToggleOn("SOME.TOGGLE1");
        Boolean toggle1OFF = toggleRule.getToggleDecider().isToggleOff("SOME.TOGGLE1");

        Assert.assertTrue(toggle1On);
        Assert.assertFalse(toggle1OFF);
    }
}