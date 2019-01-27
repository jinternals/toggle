package com.jinternals.toggle.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.jinternals.toggle.api.constants.ToggleConstants.TOGGLE_FILE_NAME;

@ConfigurationProperties(prefix = "toggles")
public class ToggleConfiguration {

    private String definitionFile = TOGGLE_FILE_NAME;

    public void setDefinitionFile(String definitionFile) {
        this.definitionFile = definitionFile;
    }

    public String getDefinitionFile() {
        return definitionFile;
    }
}
