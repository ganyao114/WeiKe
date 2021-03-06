package com.gy.myframework.IOC.UI.view.viewbinder.annotation;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by gy on 2016/3/15.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@ListBinderBase(viewType = ImageView.class,methodName = "setImageBitmap",dataType = Bitmap.class)
public @interface BindImg {
    int[] value();
}
