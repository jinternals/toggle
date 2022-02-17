package com.jinternals.toggle.validation.constraints;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {RequiredConstraintValidator.class})
public @interface RequiredConstraint {
    String message() default "{com.jinternals.toggle.validation.constraints.Required.message}";

    String toggleName();

    boolean expectedToBeOn() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
