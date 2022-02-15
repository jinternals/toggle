package com.jinternals.toggle.generator.plugin.code.generator;

import com.jinternals.toggle.core.definition.ToggleDefinitions;

import java.io.File;

public interface ToggleCodeGenerator {
    void generate(ToggleDefinitions toggles, String packageName, File outputDirectory);
}
