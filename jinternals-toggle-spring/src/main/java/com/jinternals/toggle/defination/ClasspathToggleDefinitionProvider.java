package com.jinternals.toggle.defination;

import com.jinternals.toggle.api.definition.ToggleDefinition;
import com.jinternals.toggle.api.definition.ToggleDefinitionProvider;
import com.jinternals.toggle.core.defination.parser.ToggleDefinitionParser;
import com.jinternals.toggle.core.defination.parser.ToggleDefinitions;
import org.springframework.core.io.ClassPathResource;

import java.util.Set;

import static java.io.File.separatorChar;
import static java.util.Objects.isNull;

public class ClasspathToggleDefinitionProvider implements ToggleDefinitionProvider {


    private String toggleDefinitionFile;
    private ToggleDefinitionParser toggleDefinitionParser;
    private ToggleDefinitions toggleDefinitions;

    public ClasspathToggleDefinitionProvider(String toggleDefinitionFile, ToggleDefinitionParser toggleDefinitionParser) {
        this.toggleDefinitionFile = separatorChar + toggleDefinitionFile;
        this.toggleDefinitionParser = toggleDefinitionParser;
    }

    public Set<ToggleDefinition> getToggleDefinitions() {
        return toggleDefinitions().getToggles();
    }

    private ToggleDefinitions toggleDefinitions() {

        try {
            if (isNull(toggleDefinitions)) {
                ClassPathResource classPathResource = new ClassPathResource(toggleDefinitionFile);
                toggleDefinitions = toggleDefinitionParser.parse(classPathResource.getInputStream());
            }
        } catch (Exception e) {
            //TODO: custom exception
            throw new RuntimeException(e);
        }

        return toggleDefinitions;
    }

}
