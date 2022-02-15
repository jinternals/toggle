package com.jinternals.toggle.annotation.condition;

import com.jinternals.toggle.annotation.Toggle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.HashMap;
import java.util.Map;

import static com.jinternals.toggle.core.utils.ToggleUtils.toggleName;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ToggleDefinitionConditionTest {

    @Mock
    private ConditionContext conditionContext;

    @Mock
    private Environment environment;

    @Mock
    private AnnotatedTypeMetadata annotatedTypeMetadata;


    private ToggleCondition toggleCondition;


    private Map<String, Object> attributes;

    @BeforeEach
    public void setUp() {
        toggleCondition = new ToggleCondition();

        attributes = new HashMap<>();
        attributes.put("name", "some-name");
        attributes.put("expectedToBeOn", "true");


        when(annotatedTypeMetadata.isAnnotated(Toggle.class.getCanonicalName())).thenReturn(true);
        when(annotatedTypeMetadata.getAnnotationAttributes(Toggle.class.getCanonicalName())).thenReturn(attributes);

    }

    @Test
    public void shouldReturnTrueIfToggleAnnotationIsPresentAndConditionIsTrue() {

        when(conditionContext.getEnvironment()).thenReturn(environment);
        when(environment.getProperty(toggleName("some-name"))).thenReturn("true");

        boolean result = toggleCondition.matches(conditionContext, annotatedTypeMetadata);

        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueIfToggleAnnotationIsNotPresent() {
        when(annotatedTypeMetadata.isAnnotated(Toggle.class.getCanonicalName())).thenReturn(false);

        boolean result = toggleCondition.matches(conditionContext, annotatedTypeMetadata);

        assertThat(result).isTrue();

    }

    @Test
    public void shouldReturnFalseIfToggleAnnotationIsPresentAndExpectedToBeFalseAndIsFalse() {

        when(conditionContext.getEnvironment()).thenReturn(environment);
        when(environment.getProperty(toggleName("some-name"))).thenReturn("false");

        boolean result = toggleCondition.matches(conditionContext, annotatedTypeMetadata);

        assertThat(result).isFalse();

    }

    @Test
    public void shouldReturnFalseIfToggleIsNotDefined() {

        when(conditionContext.getEnvironment()).thenReturn(environment);
        when(environment.getProperty(toggleName("some-name"))).thenReturn("false");
        boolean result = toggleCondition.matches(conditionContext, annotatedTypeMetadata);

        assertThat(result).isFalse();

    }
}
