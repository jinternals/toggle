package com.jinternals.toggle.generator.plugin.exception;

import org.apache.maven.plugin.MojoExecutionException;

public class ToggleGeneratorException extends MojoExecutionException {

    public ToggleGeneratorException(String s) {
        super(s);
    }

    public ToggleGeneratorException(String s, Throwable throwable) {
        super(s, throwable);
    }

}
