package com.jinternals.toggle.generator.plugin.code.generator;

import com.jinternals.toggle.core.defination.parser.ToggleDefinition;

import java.io.File;

public interface ToggleCodeGenerator {
    void generate(ToggleDefinition toggles, String packageName, File outputDirectory);
}
