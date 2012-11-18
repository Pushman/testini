package com.github.pushman.testini;

import javax.lang.model.type.NullType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Parameterised {

    Class<?> source() default NullType.class;

    java.lang.String method() default "";
}