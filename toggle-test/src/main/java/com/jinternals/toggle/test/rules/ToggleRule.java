package com.jinternals.toggle.test.rules;

import com.jinternals.toggle.api.decider.ToggleDecider;
import com.jinternals.toggle.test.rules.annotations.GivenToggle;
import com.jinternals.toggle.test.rules.annotations.GivenToggles;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static java.util.Objects.nonNull;
import static java.util.stream.Stream.of;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ToggleRule implements TestRule {

    private ToggleDecider toggleDecider;

    @Override
    public Statement apply(Statement statement, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {

                GivenToggle[] toggles = getGivenToggles(description);

                if (nonNull(toggles)) {
                    ToggleRule.this.setupMockToggleDecider(statement, toggles);
                } else {
                    statement.evaluate();
                }

            }
        };
    }

    public ToggleDecider getToggleDecider() {
        return toggleDecider;
    }

    private void setupMockToggleDecider(Statement base, GivenToggle... givenToggles) throws Throwable {
        this.toggleDecider = mock(ToggleDecider.class);

        of(givenToggles)
                .forEach(this::mockGivenToggle);

        base.evaluate();
    }

    private void mockGivenToggle(GivenToggle givenToggle) {
        when(toggleDecider.isToggleOn(givenToggle.name())).thenReturn(givenToggle.value().getValue());
        when(toggleDecider.isToggleOff(givenToggle.name())).thenReturn(!givenToggle.value().getValue());
    }

    private GivenToggle[] getGivenToggles(Description description) {

        final GivenToggles givenToggles = description.getAnnotation(GivenToggles.class);

        if (nonNull(givenToggles)) {
            return givenToggles.value();
        }

        final GivenToggle givenToggle = description.getAnnotation(GivenToggle.class);

        if (nonNull(givenToggle)) {
            return new GivenToggle[]{givenToggle};
        }

        return null;
    }


}
