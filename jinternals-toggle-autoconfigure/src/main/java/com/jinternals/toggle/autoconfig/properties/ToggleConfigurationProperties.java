package com.jinternals.toggle.autoconfig.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.jinternals.toggle.Constants.TOGGLE_CONFIG_PREFIX;
import static com.jinternals.toggle.core.constants.ToggleConstants.TOGGLE_FILE_NAME;

@ConfigurationProperties(prefix = TOGGLE_CONFIG_PREFIX)
public class ToggleConfigurationProperties {

    private Boolean enabled = true;

    private String definitionFile = TOGGLE_FILE_NAME;

    public void setDefinitionFile(String definitionFile) {
        this.definitionFile = definitionFile;
    }

    public String getDefinitionFile() {
        return definitionFile;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
