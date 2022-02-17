package com.jinternals.toggle.validation.constraints;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {PatternConstraintValidator.class})
public @interface PatternConstraint {
    String message() default "{com.jinternals.toggle.validation.constraints.PatternConstraint.message}";

    String toggleName();

    boolean expectedToBeOn() default true;

    String pattern();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
