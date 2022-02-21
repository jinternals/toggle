package com.jinternals.toggle.core.state;

import org.springframework.core.env.Environment;

public class EnvironmentToggleStateProvider extends AbstractToggleStateProvider {

    private Environment environment;

    public EnvironmentToggleStateProvider(Environment environment) {
        this.environment = environment;
    }

    @Override
    public boolean getToggleState(String toggle) {
        return environment.getProperty(toggle, Boolean.class, false);
    }

}
