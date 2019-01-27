package com.jinternals.toggle.generator.plugin.factory;

import com.jinternals.toggle.core.defination.parser.ToggleDefinitionParser;
import com.jinternals.toggle.core.defination.parser.impl.YamlToggleDefinitionParser;
import com.jinternals.toggle.generator.plugin.code.generator.ToggleCodeGenerator;
import com.jinternals.toggle.generator.plugin.code.generator.impl.ToggleCodeGeneratorImpl;

public class Factory {

    public static ToggleCodeGenerator toggleCodeGenerator() {
        return new ToggleCodeGeneratorImpl();
    }

    public static ToggleDefinitionParser toggleDefinitionParser() {
        return new YamlToggleDefinitionParser();
    }

}
