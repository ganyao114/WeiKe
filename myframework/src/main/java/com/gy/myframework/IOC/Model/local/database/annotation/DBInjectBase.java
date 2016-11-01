package com.gy.myframework.IOC.Model.local.database.annotation;


import com.gy.myframework.IOC.Model.local.database.entity.DBValueMode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by gy on 2016/1/18.
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBInjectBase {
    DBValueMode value();
}
