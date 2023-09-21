package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE}) // Class, interface (including annotation interface), enum, or record declaration
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
}