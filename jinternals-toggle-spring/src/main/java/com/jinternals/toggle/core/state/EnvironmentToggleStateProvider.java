package com.jinternals.toggle.core.state;

import com.jinternals.toggle.api.state.AbstractToggleStateProvider;
import org.springframework.core.env.Environment;

import static java.lang.Boolean.parseBoolean;

public class EnvironmentToggleStateProvider extends AbstractToggleStateProvider {

    private Environment environment;

    public EnvironmentToggleStateProvider(Environment environment) {
        this.environment = environment;
    }

    @Override
    public boolean getToggleState(String toggle) {
        return parseBoolean(environment.getProperty(toggle));
    }

}
