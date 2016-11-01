package com.gy.myframework.IOC.Core.impl;

import com.gy.myframework.IOC.Core.parase.AnnotationFactory;

/**
 * Created by gy on 2015/11/19.
 */
public class IntThread implements Runnable {
    private AnnotationFactory factory;

    @Override
    public void run() {
        factory = AnnotationFactory.getInstance();
        factory.getContextAnnotations();
    }
}
