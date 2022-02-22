package com.jinternals.toggle.interceptor;

import com.jinternals.toggle.annotation.Toggle;
import com.jinternals.toggle.core.services.ToggleService;
import com.jinternals.toggle.test.rules.ToggleExtension;
import com.jinternals.toggle.test.rules.annotations.GivenToggle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;

import java.lang.annotation.Annotation;

import static com.jinternals.toggle.test.ToggleValue.FALSE;
import static com.jinternals.toggle.test.ToggleValue.TRUE;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith({MockitoExtension.class, ToggleExtension.class})
public class ToggleDefinitionInterceptorTest {

    private MockHttpServletRequest request = new MockHttpServletRequest();
    private MockHttpServletResponse response = new MockHttpServletResponse();
    private String toggleName = "some-name";

    @Mock
    private HandlerMethod handlerMethod;

    @BeforeEach
    void setUp() throws Exception {
        when(handlerMethod.getMethodAnnotation(Toggle.class)).thenReturn(new Toggle() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }

            @Override
            public String name() {
                return toggleName;
            }

            @Override
            public boolean expectedToBeOn() {
                return true;
            }
        });

    }

    @Test
    @GivenToggle(name = "some-name", value = TRUE)
    void shouldReturnTrueIfMethodIsAnnotatedAndToggleIsOn(ToggleService toggleService) throws Exception {

        ToggleInterceptor interceptor = new ToggleInterceptor(toggleService);

        boolean result = interceptor.preHandle(request, response, handlerMethod);

        assertThat(result).isTrue();

    }

    @Test
    @GivenToggle(name = "some-name", value = FALSE)
    void shouldReturnFalseIfMethodIsAnnotatedAndToggleIsOff(ToggleService toggleService) throws Exception {

        ToggleInterceptor interceptor = new ToggleInterceptor(toggleService);

        boolean result = interceptor.preHandle(request, response, handlerMethod);

        assertThat(result).isFalse();

        assertThat(response.getStatus()).isEqualTo(SC_NOT_FOUND);
    }

    @Test
    @GivenToggle(name = "some-name", value = FALSE)
    void shouldReturnTrueIfMethodIsNotAnnotated(ToggleService toggleService) throws Exception {
        when(handlerMethod.getMethodAnnotation(Toggle.class)).thenReturn(null);

        ToggleInterceptor interceptor = new ToggleInterceptor(toggleService);

        boolean result = interceptor.preHandle(request, response, handlerMethod);

        assertThat(result).isTrue();
    }


    @Test
    @GivenToggle(name = "some-name", value = FALSE, defined = FALSE)
    void shouldReturnFalseIfToggleIsNotDefined(ToggleService toggleService) throws Exception {

        ToggleInterceptor interceptor = new ToggleInterceptor(toggleService);

        boolean result = interceptor.preHandle(request, response, handlerMethod);

        assertThat(result).isFalse();
    }
}
