package com.gy.myframework.Service.verification.impl;

import com.gy.myframework.Service.verification.IValiResult;
import com.gy.myframework.Service.verification.IValidater;

/**
 * Created by gy on 2016/4/21.
 */
public class EditorTextVali implements IValidater{
    private IValiResult result;
    @Override
    public Object beforeValidate(Object object) {
        return null;
    }

    @Override
    public Object onValidate(Object object) {
        return null;
    }

    @Override
    public Object afterValidate(Object object) {
        return null;
    }

    @Override
    public void setResult(IValiResult result) {
        this.result = result;
    }
}
