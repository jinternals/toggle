package com.jinternals.toggle.annotation.condition;

import com.jinternals.toggle.annotation.Toggle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.HashMap;
import java.util.Map;

import static com.jinternals.toggle.api.utils.ToggleUtils.toggleName;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ToggleDefinitionConditionTest {

    @Mock
    private ConditionContext conditionContext;

    @Mock
    private Environment environment;

    @Mock
    private AnnotatedTypeMetadata annotatedTypeMetadata;


    private ToggleCondition toggleCondition;


    private Map<String, Object> attributes;

    @Before
    public void setUp() {
        toggleCondition = new ToggleCondition();

        attributes = new HashMap<>();
        attributes.put("name", "some-name");
        attributes.put("expectedToBeOn", "true");


        when(annotatedTypeMetadata.isAnnotated(Toggle.class.getCanonicalName())).thenReturn(true);
        when(annotatedTypeMetadata.getAnnotationAttributes(Toggle.class.getCanonicalName())).thenReturn(attributes);

    }

    @Test
    public void shouldReturnTrueIfFeatureToggleAnnotationIsPresentAndConditionIsTrue() {

        when(conditionContext.getEnvironment()).thenReturn(environment);
        when(environment.getProperty(toggleName("some-name"))).thenReturn("true");

        boolean result = toggleCondition.matches(conditionContext, annotatedTypeMetadata);

        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueIfFeatureToggleAnnotationIsNotPresent() {
        when(annotatedTypeMetadata.isAnnotated(Toggle.class.getCanonicalName())).thenReturn(false);

        boolean result = toggleCondition.matches(conditionContext, annotatedTypeMetadata);

        assertThat(result).isTrue();

    }

    @Test
    public void shouldReturnFalseIfFeatureToggleAnnotationIsPresentAndExpectedToBeFalseAndIsFalse() {

        when(conditionContext.getEnvironment()).thenReturn(environment);
        when(environment.getProperty(toggleName("some-name"))).thenReturn("false");

        boolean result = toggleCondition.matches(conditionContext, annotatedTypeMetadata);

        assertThat(result).isFalse();

    }

    @Test
    public void shouldReturnFalseIfFeatureToggleIsNotDefined() {

        when(conditionContext.getEnvironment()).thenReturn(environment);
        when(environment.getProperty(toggleName("some-name"))).thenReturn("false");
        boolean result = toggleCondition.matches(conditionContext, annotatedTypeMetadata);

        assertThat(result).isFalse();

    }
}