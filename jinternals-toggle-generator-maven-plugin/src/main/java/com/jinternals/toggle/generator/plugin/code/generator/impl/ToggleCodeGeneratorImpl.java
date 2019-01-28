package com.jinternals.toggle.generator.plugin.code.generator.impl;

import com.jinternals.toggle.core.defination.parser.ToggleDefinition;
import com.jinternals.toggle.generator.plugin.code.generator.ToggleCodeGenerator;
import com.jinternals.toggle.api.Toggle;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.File;
import java.io.IOException;

import static com.jinternals.toggle.generator.plugin.utils.Utils.normalizeName;
import static com.squareup.javapoet.TypeSpec.interfaceBuilder;
import static javax.lang.model.element.Modifier.*;

public class ToggleCodeGeneratorImpl implements ToggleCodeGenerator {

    @Override
    public void generate(ToggleDefinition toggles, String packageName, File outputDirectory) {
        final TypeSpec.Builder featureInterfaceBuilder = interfaceBuilder("Toggles")
                .addModifiers(PUBLIC);

        for (Toggle Toggle : toggles.getToggles()) {
            FieldSpec fieldSpec = FieldSpec.builder(String.class, normalizeName(Toggle.getName()), PUBLIC, STATIC, FINAL)
                    .initializer("$S", Toggle.getName())
                    .build();
            featureInterfaceBuilder.addField(fieldSpec);
        }

        TypeSpec typeSpec = featureInterfaceBuilder.build();

        JavaFile javaFile = JavaFile.builder(packageName, typeSpec)
                .build();

        try {
            javaFile.writeTo(outputDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
