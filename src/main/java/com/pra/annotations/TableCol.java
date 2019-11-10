package com.pra.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author prakash dudhat
 *
 */

@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface TableCol{
	String displayName() default "-";
	boolean filterable() default false;
	int width() default -1;
}
