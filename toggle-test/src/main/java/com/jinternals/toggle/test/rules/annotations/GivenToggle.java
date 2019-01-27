package com.jinternals.toggle.test.rules.annotations;

import com.jinternals.toggle.test.ToggleValue;

import java.lang.annotation.*;

import static com.jinternals.toggle.test.ToggleValue.TRUE;

@Repeatable(GivenToggles.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface GivenToggle {
    String name() default "";
    ToggleValue value() default TRUE;
    ToggleValue defined() default TRUE;
}