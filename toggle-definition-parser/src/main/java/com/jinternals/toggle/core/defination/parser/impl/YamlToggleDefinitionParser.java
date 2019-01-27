package com.jinternals.toggle.core.defination.parser.impl;

import com.jinternals.toggle.core.defination.parser.ToggleDefinition;
import com.jinternals.toggle.core.defination.parser.ToggleDefinitionParser;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class YamlToggleDefinitionParser implements ToggleDefinitionParser {

    public ToggleDefinition parse(InputStream stream) {
        Yaml yaml = new Yaml();
        try {
            return yaml.loadAs(stream, ToggleDefinition.class);
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

    public ToggleDefinition parse(File file) {
        try (InputStream inputStream = new FileInputStream(file)) {
            Yaml yaml = new Yaml();
            return yaml.loadAs(inputStream, ToggleDefinition.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
