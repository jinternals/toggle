package com.jinternals.toggle.generator.plugin.factory;

import com.jinternals.toggle.core.definition.ToggleDefinitionParser;
import com.jinternals.toggle.core.definition.impl.YamlToggleDefinitionParser;
import com.jinternals.toggle.generator.plugin.code.generator.impl.SpringMetadataGenerator;
import com.jinternals.toggle.generator.plugin.code.generator.impl.ToggleCodeGenerator;

public class Factory {

    public static ToggleCodeGenerator toggleCodeGenerator() {
        return new ToggleCodeGenerator();
    }

    public static SpringMetadataGenerator springMetadataGenerator() {
        return new SpringMetadataGenerator();
    }

    public static ToggleDefinitionParser toggleDefinitionParser() {
        return new YamlToggleDefinitionParser();
    }


}
