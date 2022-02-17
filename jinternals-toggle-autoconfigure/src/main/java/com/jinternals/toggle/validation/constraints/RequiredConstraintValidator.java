package com.jinternals.toggle.validation.constraints;

import com.jinternals.toggle.core.decider.ToggleDecider;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.nonNull;

public class RequiredConstraintValidator implements ConstraintValidator<RequiredConstraint, Object> {

    private String toggleName;
    private boolean expectedToBeOn;

    @Autowired
    private ToggleDecider toggleDecider;

    @Override
    public void initialize(RequiredConstraint requiredConstraint) {
        this.toggleName = requiredConstraint.toggleName();
        this.expectedToBeOn = requiredConstraint.expectedToBeOn();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {

        if(toggleDecider.isToggleOn(toggleName) == expectedToBeOn){
            return nonNull(obj);
        }

        return true;
    }

}
