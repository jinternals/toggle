package com.jinternals.toggle.interceptor;

import com.jinternals.toggle.annotation.Toggle;
import com.jinternals.toggle.api.decider.ToggleDecider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;

import java.lang.annotation.Annotation;

import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ToggleInterceptorTest {

    private MockHttpServletRequest request = new MockHttpServletRequest();
    private MockHttpServletResponse response = new MockHttpServletResponse();
    private String featureToggleName = "some-feature";

    @Mock
    private ToggleDecider featureToggleDecider;

    @Mock
    private HandlerMethod handlerMethod;

    @Before
    public void setUp() throws Exception {
        when(handlerMethod.getMethodAnnotation(Toggle.class)).thenReturn(new Toggle() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }

            @Override
            public String feature() {
                return featureToggleName;
            }

            @Override
            public boolean expectedToBeOn() {
                return true;
            }
        });

        when(featureToggleDecider.isToggleDefined(featureToggleName)).thenReturn(true);

    }

    @Test
    public void shouldReturnTrueIfMethodIsAnnotatedAndToggleIsOn() throws Exception {
        when(featureToggleDecider.isToggleOn(featureToggleName)).thenReturn(true);

        ToggleInterceptor interceptor = new ToggleInterceptor(featureToggleDecider);

        boolean result = interceptor.preHandle(request, response, handlerMethod);

        assertThat(result).isTrue();

    }

    @Test
    public void shouldReturnFalseIfMethodIsAnnotatedAndToggleIsOff() throws Exception {
        when(featureToggleDecider.isToggleOn(featureToggleName)).thenReturn(false);

        ToggleInterceptor interceptor = new ToggleInterceptor(featureToggleDecider);

        boolean result = interceptor.preHandle(request, response, handlerMethod);

        assertThat(result).isFalse();

        assertThat(response.getStatus()).isEqualTo(SC_NOT_FOUND);
    }

    @Test
    public void shouldReturnTrueIfMethodIsNotAnnotated() throws Exception {
        when(handlerMethod.getMethodAnnotation(Toggle.class)).thenReturn(null);

        ToggleInterceptor interceptor = new ToggleInterceptor(featureToggleDecider);

        boolean result = interceptor.preHandle(request, response, handlerMethod);

        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseIfFeatureToggleIsNotDefined() throws Exception {

        ToggleInterceptor interceptor = new ToggleInterceptor(featureToggleDecider);

        boolean result = interceptor.preHandle(request, response, handlerMethod);


        assertThat(result).isFalse();
    }
}