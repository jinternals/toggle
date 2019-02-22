package com.jinternals.toggle.generator.plugin.code.generator;

import com.jinternals.toggle.core.defination.parser.ToggleDefinitions;

import java.io.File;

public interface ToggleCodeGenerator {
    void generate(ToggleDefinitions toggles, String packageName, File outputDirectory);
}
