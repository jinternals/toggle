package com.jinternals.toggle.core.defination;

import com.jinternals.toggle.core.defination.parser.ToggleDefinition;
import com.jinternals.toggle.core.defination.parser.ToggleDefinitionParser;
import com.jinternals.toggle.api.Toggle;
import com.jinternals.toggle.api.definition.ToggleDefinitionProvider;

import java.util.List;

import static java.io.File.separatorChar;
import static java.util.Objects.isNull;

public class ClasspathToggleDefinitionProvider implements ToggleDefinitionProvider {

    private String classPathFileLocation;
    private ToggleDefinitionParser toggleDefinitionParser;
    private ToggleDefinition toggleDefinition;

    public ClasspathToggleDefinitionProvider(String classPathFileLocation, ToggleDefinitionParser toggleDefinitionParser) {
        this.classPathFileLocation = separatorChar + classPathFileLocation;
        this.toggleDefinitionParser = toggleDefinitionParser;
    }

    @Override
    public List<Toggle> getToggles() {
        return getToggleDefinition().getToggles();
    }

    private ToggleDefinition getToggleDefinition() {

        if (isNull(toggleDefinition)) {
            toggleDefinition = toggleDefinitionParser.parse(getClass().getResourceAsStream(classPathFileLocation));
        }

        return toggleDefinition;
    }

}
