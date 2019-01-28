package com.jinternals.toggle.annotation.condition;

import com.jinternals.toggle.annotation.Toggle;
import com.jinternals.toggle.api.decider.ToggleDecider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ToggleConditionTest {

    @Mock
    private ConditionContext conditionContext;

    @Mock
    private AnnotatedTypeMetadata annotatedTypeMetadata;

    @Mock
    private ToggleDecider toggleDecider;

    @Mock
    private ConfigurableListableBeanFactory beanFactory;


    private ToggleCondition toggleCondition;


    private Map<String, Object> attributes;

    @Before
    public void setUp() throws Exception {
        toggleCondition = new ToggleCondition();

        attributes = new HashMap<>();
        attributes.put("name", "some-name");
        attributes.put("expectedToBeOn", "true");


        when(annotatedTypeMetadata.isAnnotated(Toggle.class.getCanonicalName())).thenReturn(true);
        when(annotatedTypeMetadata.getAnnotationAttributes(Toggle.class.getCanonicalName())).thenReturn(attributes);
        when(conditionContext.getBeanFactory()).thenReturn(beanFactory);
        when(conditionContext.getBeanFactory().getBean(ToggleDecider.class)).thenReturn(toggleDecider);
        when(toggleDecider.isToggleDefined("some-name")).thenReturn(true);
        when(toggleDecider.isToggleOn("some-name")).thenReturn(true);


    }

    @Test
    public void shouldReturnTrueIfFeatureToggleAnnotationIsPresentAndConditionIsTrue() {

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

        when(toggleDecider.isToggleOn("some-name")).thenReturn(false);

        boolean result = toggleCondition.matches(conditionContext, annotatedTypeMetadata);

        assertThat(result).isFalse();

    }

    @Test
    public void shouldReturnFalseIfFeatureToggleIsNotDefined() {

        when(toggleDecider.isToggleDefined("some-name")).thenReturn(false);

        boolean result = toggleCondition.matches(conditionContext, annotatedTypeMetadata);

        assertThat(result).isFalse();

    }
}