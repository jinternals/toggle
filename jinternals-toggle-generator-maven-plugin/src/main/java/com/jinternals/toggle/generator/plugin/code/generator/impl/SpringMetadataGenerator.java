package com.jinternals.toggle.generator.plugin.code.generator.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinternals.toggle.core.definition.ToggleDefinition;
import com.jinternals.toggle.core.definition.ToggleDefinitions;
import com.jinternals.toggle.generator.plugin.code.generator.ToggleCodeGenerator;
import com.jinternals.toggle.generator.plugin.code.generator.impl.properties.SpringMetadata;
import com.jinternals.toggle.generator.plugin.code.generator.impl.properties.SpringMetadataProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import static com.jinternals.toggle.core.constants.ToggleConstants.PREFIX;
import static com.jinternals.toggle.core.constants.ToggleConstants.SUFFIX;
import static java.util.Objects.nonNull;

public class SpringMetadataGenerator implements ToggleCodeGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringMetadataGenerator.class);
    private static final String TOGGLE_FORMAT = "%s.%s.%s";

    @Override
    public void generate(ToggleDefinitions toggleDefinitions, String packageName, File outputDirectory) {
        SpringMetadata springMetadata = new SpringMetadata();

        for (ToggleDefinition toggleDefinition : toggleDefinitions.getToggles()) {
            SpringMetadataProperty springMetadataProperty = new SpringMetadataProperty();
            springMetadataProperty.setName(String.format(TOGGLE_FORMAT, PREFIX, toggleDefinition.getName(), SUFFIX));
            springMetadataProperty.setSourceType(packageName + "." + "Toggles");
            springMetadata.getProperties().add(springMetadataProperty);
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            File resultFile = new File(outputDirectory + "/spring-configuration-metadata.json");

            if (nonNull(resultFile.getParentFile())) {
                resultFile.getParentFile().mkdirs();
            }

            mapper.writerWithDefaultPrettyPrinter().writeValue(resultFile, springMetadata);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }

    }

}
