package com.jinternals.toggle.analyzers;

import com.jinternals.toggle.exception.ToggleNotDefinedException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

public class ToggleNotDefinedExceptionAnalyzer extends AbstractFailureAnalyzer<ToggleNotDefinedException> {

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, ToggleNotDefinedException cause) {
        return new FailureAnalysis(getDescription(cause), getAction(cause), cause);
    }

    private String getDescription(ToggleNotDefinedException ex) {
        return String.format("The toggle \"%s\" can not be used, because it is not defined.", ex.getToggleName());
    }

    private String getAction(ToggleNotDefinedException ex) {
        return String.format("Consider defining the toggle \"%s\" in toggle repository.",
                ex.getToggleName());
    }
}
