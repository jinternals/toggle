package com.jinternals.toggle.annotation;

import com.jinternals.toggle.annotation.condition.ToggleCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(ToggleCondition.class)
public @interface Toggle {
    String feature();

    boolean expectedToBeOn() default true;
}