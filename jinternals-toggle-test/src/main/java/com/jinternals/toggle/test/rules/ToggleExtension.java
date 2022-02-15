package com.jinternals.toggle.test.rules;


import com.jinternals.toggle.core.decider.ToggleDecider;
import com.jinternals.toggle.test.rules.annotations.GivenToggle;
import com.jinternals.toggle.test.rules.annotations.GivenToggles;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.mockito.Mockito;

import static java.util.Objects.nonNull;
import static org.mockito.Mockito.when;

public class ToggleExtension implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) {
        return parameterContext.getParameter().getType() == ToggleDecider.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext) {
        return buildDeciderToggleMock(getGivenToggles(parameterContext));
    }



    private ToggleDecider buildDeciderToggleMock(GivenToggle[] givenToggles) {
        ToggleDecider toggleDecider = Mockito.mock(ToggleDecider.class);

        for (GivenToggle givenToggle: givenToggles) {
            when(toggleDecider.isToggleOn(givenToggle.name())).thenReturn(givenToggle.value().getValue());
            when(toggleDecider.isToggleOff(givenToggle.name())).thenReturn(!givenToggle.value().getValue());
            when(toggleDecider.isToggleDefined(givenToggle.name())).thenReturn(givenToggle.defined().getValue());
        }

        return  toggleDecider;
    }

    private GivenToggle[] getGivenToggles(ParameterContext description) {

        final GivenToggles givenToggles = description.getDeclaringExecutable().getAnnotation(GivenToggles.class);

        if (nonNull(givenToggles)) {
            return givenToggles.value();
        }

        final GivenToggle givenToggle = description.getDeclaringExecutable().getAnnotation(GivenToggle.class);

        if (nonNull(givenToggle)) {
            return new GivenToggle[]{givenToggle};
        }

        return new GivenToggle[]{};
    }

}
