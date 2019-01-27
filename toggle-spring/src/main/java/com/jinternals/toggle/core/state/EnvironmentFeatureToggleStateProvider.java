package com.jinternals.toggle.core.state;

import com.jinternals.toggle.api.state.AbstractToggleStateProvider;
import org.springframework.core.env.Environment;

import static java.lang.Boolean.parseBoolean;

public class EnvironmentFeatureToggleStateProvider extends AbstractToggleStateProvider {

    private Environment environment;

    public EnvironmentFeatureToggleStateProvider(Environment environment) {
        this.environment = environment;
    }

    @Override
    public boolean getToggleState(String featureToggle) {
        return parseBoolean(environment.getProperty(featureToggle));
    }

}
