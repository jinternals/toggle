package com.jinternals.toggle.annotation.condition;

import com.jinternals.toggle.annotation.Toggle;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

import static com.jinternals.toggle.core.utils.ToggleUtils.toggleName;
import static java.lang.Boolean.parseBoolean;
import static java.lang.String.valueOf;

public class ToggleCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        if (!metadata.isAnnotated(Toggle.class.getCanonicalName())) {
            return true;
        }

        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(Toggle.class.getCanonicalName());

        String toggleName = (String) annotationAttributes.get("name");
        boolean expectedToBeOn = parseBoolean(valueOf(annotationAttributes.get("expectedToBeOn")));


        boolean isOn = Boolean.parseBoolean(conditionContext.getEnvironment().getProperty(toggleName(toggleName)));

        return expectedToBeOn == isOn;
    }
}
