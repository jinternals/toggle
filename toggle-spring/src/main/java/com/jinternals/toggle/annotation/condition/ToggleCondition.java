package com.jinternals.toggle.annotation.condition;

import com.jinternals.toggle.annotation.Toggle;
import com.jinternals.toggle.api.decider.ToggleDecider;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

import static java.lang.Boolean.parseBoolean;
import static java.lang.String.valueOf;

public class ToggleCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        if (!metadata.isAnnotated(Toggle.class.getCanonicalName())) {
            return true;
        }

        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(Toggle.class.getCanonicalName());
        ToggleDecider toggleDecider = conditionContext.getBeanFactory().getBean(ToggleDecider.class);

        String featureToggleName = (String) annotationAttributes.get("feature");
        boolean expectedToBeOn = parseBoolean(valueOf(annotationAttributes.get("expectedToBeOn")));

        boolean isToggleDefined = toggleDecider.isToggleDefined(featureToggleName);

        if (isToggleDefined) {
            boolean isToggleOn = toggleDecider.isToggleOn(featureToggleName);
            return expectedToBeOn == isToggleOn;
        }


        return false;

    }
}