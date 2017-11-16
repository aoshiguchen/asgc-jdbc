package com.github.aoshiguchen.framework.jdbc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.aoshiguchen.framework.jdbc.entity.IPo;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Dao {
	
	public Class<?> value();
	
}
