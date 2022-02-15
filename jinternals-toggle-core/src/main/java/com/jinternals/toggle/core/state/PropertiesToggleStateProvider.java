package com.jinternals.toggle.core.state;

import java.util.Properties;

import static java.lang.Boolean.parseBoolean;

public class PropertiesToggleStateProvider extends AbstractToggleStateProvider {

    private Properties properties;

    public PropertiesToggleStateProvider(Properties properties) {
        this.properties = properties;
    }

    @Override
    public boolean getToggleState(String toggle) {
        return parseBoolean(properties.getProperty(toggle));
    }

}
