package com.jinternals.toggle.interceptor;

import com.jinternals.toggle.annotation.Toggle;
import com.jinternals.toggle.api.decider.ToggleDecider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.String.format;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

public class ToggleInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(ToggleInterceptor.class);

    private final ToggleDecider toggleDecider;

    public ToggleInterceptor(ToggleDecider toggleDecider) {
        this.toggleDecider = toggleDecider;
    }

    public final boolean supports(Object handler) {
        return handler instanceof HandlerMethod;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!supports(handler)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Toggle annotation = handlerMethod.getMethodAnnotation(Toggle.class);

        if (annotation == null) {
            return true;
        }

        if (toggleDecider.isToggleDefined(annotation.name()) == false) {
            logger.info(format("ToggleDefinition %s is not defined.", annotation.name()));
            response.setStatus(SC_NOT_FOUND);
            return false;
        }

        logger.info(format("ToggleDefinition %s  expectedToBeOn : %s and is %s .", annotation.name(), annotation.expectedToBeOn(), toggleDecider.isToggleOn(annotation.name())));

        if (annotation.expectedToBeOn() == toggleDecider.isToggleOn(annotation.name())) {
            return true;
        }

        response.setStatus(SC_NOT_FOUND);
        return false;
    }


}