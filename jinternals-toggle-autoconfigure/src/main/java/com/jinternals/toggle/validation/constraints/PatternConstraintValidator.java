package com.jinternals.toggle.validation.constraints;

import com.jinternals.toggle.core.decider.ToggleDecider;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

import static java.util.Objects.nonNull;
import static java.util.regex.Pattern.compile;

public class PatternConstraintValidator implements ConstraintValidator<PatternConstraint, String> {

    private String toggleName;
    private boolean expectedToBeOn;
    private Pattern pattern;

    @Autowired
    private ToggleDecider toggleDecider;

    @Override
    public void initialize(PatternConstraint required) {
        this.toggleName = required.toggleName();
        this.expectedToBeOn = required.expectedToBeOn();
        this.pattern = compile(required.pattern());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if(toggleDecider.isToggleOn(toggleName) == expectedToBeOn){
            return nonNull(value) && pattern.matcher(value).matches();
        }

        return true;
    }

}
