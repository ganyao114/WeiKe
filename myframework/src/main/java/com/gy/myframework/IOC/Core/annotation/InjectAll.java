package com.gy.myframework.IOC.Core.annotation;

import com.gy.myframework.IOC.Core.entity.InjectOptions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by gy on 2016/3/9.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectAll {
    InjectOptions[] value();
}
