package com.jinternals.toggle.test.rules;


import com.jinternals.toggle.core.decider.ToggleService;
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
        return parameterContext.getParameter().getType() == ToggleService.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext) {
        return buildDeciderToggleMock(getGivenToggles(parameterContext));
    }



    private ToggleService buildDeciderToggleMock(GivenToggle[] givenToggles) {
        ToggleService toggleService = Mockito.mock(ToggleService.class);

        for (GivenToggle givenToggle: givenToggles) {
            when(toggleService.isToggleOn(givenToggle.name())).thenReturn(givenToggle.value().getValue());
            when(toggleService.isToggleOff(givenToggle.name())).thenReturn(!givenToggle.value().getValue());
            when(toggleService.isToggleDefined(givenToggle.name())).thenReturn(givenToggle.defined().getValue());
        }

        return toggleService;
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
