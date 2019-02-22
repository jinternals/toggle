package com.jinternals.toggle.core.defination.parser;

import java.io.File;
import java.io.InputStream;

public interface ToggleDefinitionParser {
    public ToggleDefinitions parse(InputStream stream);
    public ToggleDefinitions parse(File stream);

}
