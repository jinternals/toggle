package com.jinternals.toggle.annotation.condition;

import com.jinternals.toggle.Constants;
import com.jinternals.toggle.annotation.Toggle;
import com.jinternals.toggle.core.definition.impl.YamlToggleDefinitionParser;
import com.jinternals.toggle.defination.ClasspathToggleDefinitionProvider;
import com.jinternals.toggle.exception.ToggleNotDefinedException;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

import static com.jinternals.toggle.core.constants.ToggleConstants.TOGGLE_FILE_NAME;
import static com.jinternals.toggle.core.utils.ToggleUtils.toggleName;
import static java.lang.Boolean.parseBoolean;
import static java.lang.String.valueOf;

public class ToggleCondition implements Condition {


    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        if (!metadata.isAnnotated(Toggle.class.getCanonicalName())) {
            return true;
        }

        String toggleDefinitionFile = conditionContext.getEnvironment().getProperty(Constants.TOGGLE_CONFIG_PREFIX + ".definitionFile", TOGGLE_FILE_NAME);

        ClasspathToggleDefinitionProvider classpathToggleDefinitionProvider
                = new ClasspathToggleDefinitionProvider(toggleDefinitionFile, new YamlToggleDefinitionParser());


        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(Toggle.class.getCanonicalName());

        String toggleName = (String) annotationAttributes.get("name");
        boolean expectedToBeOn = parseBoolean(valueOf(annotationAttributes.get("expectedToBeOn")));

        boolean contains = classpathToggleDefinitionProvider.getToggleDefinitions().stream().anyMatch(toggleDefinition -> toggleDefinition.getName().equals(toggleName));
        if (!contains) {
            throw new ToggleNotDefinedException(toggleName, "Toggle not defined");
        }

        boolean isOn = Boolean.parseBoolean(conditionContext.getEnvironment().getProperty(toggleName(toggleName)));

        return expectedToBeOn == isOn;
    }
}
