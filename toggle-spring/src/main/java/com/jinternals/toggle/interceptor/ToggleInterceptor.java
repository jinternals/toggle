package com.jinternals.toggle.interceptor;

import com.jinternals.toggle.annotation.Toggle;
import com.jinternals.toggle.api.decider.ToggleDecider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.String.format;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

public class ToggleInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(ToggleInterceptor.class);

    private final ToggleDecider toggleDecider;

    public ToggleInterceptor(ToggleDecider toggleDecider) {
        this.toggleDecider = toggleDecider;
    }

    public final boolean supports(Object handler) {
        return handler instanceof HandlerMethod;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!supports(handler)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Toggle annotation = handlerMethod.getMethodAnnotation(Toggle.class);

        if (annotation == null) {
            return true;
        }

        if (toggleDecider.isToggleDefined(annotation.feature()) == false) {
            logger.info(format("Feature toggle %s is not defined.", annotation.feature()));
            response.setStatus(SC_NOT_FOUND);
            return false;
        }

        logger.info(format("Feature toggle %s  expectedToBeOn : %s and is %s .", annotation.feature(), annotation.expectedToBeOn(), toggleDecider.isToggleOn(annotation.feature())));

        if (annotation.expectedToBeOn() == toggleDecider.isToggleOn(annotation.feature())) {
            return true;
        }

        response.setStatus(SC_NOT_FOUND);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }


}