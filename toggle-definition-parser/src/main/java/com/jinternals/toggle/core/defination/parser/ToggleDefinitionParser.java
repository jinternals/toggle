package com.jinternals.toggle.core.defination.parser;

import java.io.File;
import java.io.InputStream;

public interface ToggleDefinitionParser {
    public ToggleDefinition parse(InputStream stream);
    public ToggleDefinition parse(File stream);
}
