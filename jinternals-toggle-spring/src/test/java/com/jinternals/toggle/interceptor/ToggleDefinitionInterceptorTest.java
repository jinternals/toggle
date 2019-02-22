package com.jinternals.toggle.interceptor;

import com.jinternals.toggle.annotation.Toggle;
import com.jinternals.toggle.test.rules.ToggleRule;
import com.jinternals.toggle.test.rules.annotations.GivenToggle;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;

import java.lang.annotation.Annotation;

import static com.jinternals.toggle.test.ToggleValue.FALSE;
import static com.jinternals.toggle.test.ToggleValue.TRUE;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.Silent.class)
public class ToggleDefinitionInterceptorTest {

    private MockHttpServletRequest request = new MockHttpServletRequest();
    private MockHttpServletResponse response = new MockHttpServletResponse();
    private String featureToggleName = "some-name";

    @Rule
    public ToggleRule toggleRule = new ToggleRule();

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
            public String name() {
                return featureToggleName;
            }

            @Override
            public boolean expectedToBeOn() {
                return true;
            }
        });

    }

    @Test
    @GivenToggle(name = "some-name", value = TRUE)
    public void shouldReturnTrueIfMethodIsAnnotatedAndToggleIsOn() throws Exception {

        ToggleInterceptor interceptor = new ToggleInterceptor(toggleRule.getToggleDecider());

        boolean result = interceptor.preHandle(request, response, handlerMethod);

        assertThat(result).isTrue();

    }

    @Test
    @GivenToggle(name = "some-name", value = FALSE)
    public void shouldReturnFalseIfMethodIsAnnotatedAndToggleIsOff() throws Exception {

        ToggleInterceptor interceptor = new ToggleInterceptor(toggleRule.getToggleDecider());

        boolean result = interceptor.preHandle(request, response, handlerMethod);

        assertThat(result).isFalse();

        assertThat(response.getStatus()).isEqualTo(SC_NOT_FOUND);
    }

    @Test
    @GivenToggle(name = "some-name", value = FALSE)
    public void shouldReturnTrueIfMethodIsNotAnnotated() throws Exception {
        when(handlerMethod.getMethodAnnotation(Toggle.class)).thenReturn(null);

        ToggleInterceptor interceptor = new ToggleInterceptor(toggleRule.getToggleDecider());

        boolean result = interceptor.preHandle(request, response, handlerMethod);

        assertThat(result).isTrue();
    }


    @Test
    @GivenToggle(name = "some-name", value = FALSE, defined = FALSE)
    public void shouldReturnFalseIfFeatureToggleIsNotDefined() throws Exception {

        ToggleInterceptor interceptor = new ToggleInterceptor(toggleRule.getToggleDecider());

        boolean result = interceptor.preHandle(request, response, handlerMethod);


        assertThat(result).isFalse();
    }
}