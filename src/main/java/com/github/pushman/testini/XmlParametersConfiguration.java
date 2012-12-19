package com.github.pushman.testini;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface XmlParametersConfiguration {
    java.lang.String source() default "";
}