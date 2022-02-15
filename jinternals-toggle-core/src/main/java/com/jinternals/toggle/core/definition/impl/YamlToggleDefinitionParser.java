package com.jinternals.toggle.core.definition.impl;

import com.jinternals.toggle.core.definition.ToggleDefinitions;
import com.jinternals.toggle.core.definition.ToggleDefinitionParser;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class YamlToggleDefinitionParser implements ToggleDefinitionParser {

    public ToggleDefinitions parse(InputStream stream) {
        Yaml yaml = new Yaml();
        try {
            return yaml.loadAs(stream, ToggleDefinitions.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ToggleDefinitions parse(File file) {
        try (InputStream inputStream = new FileInputStream(file)) {
            Yaml yaml = new Yaml();
            return yaml.loadAs(inputStream, ToggleDefinitions.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
