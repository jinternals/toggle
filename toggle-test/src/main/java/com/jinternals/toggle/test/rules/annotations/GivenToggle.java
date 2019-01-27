package com.jinternals.toggle.test.rules.annotations;

import com.jinternals.toggle.test.MockToggleValue;

import java.lang.annotation.*;

import static com.jinternals.toggle.test.MockToggleValue.TRUE;

@Repeatable(GivenToggles.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface GivenToggle {
    String name() default "";
    MockToggleValue value() default TRUE;
}