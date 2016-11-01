package com.gy.myframework.Model.net.http;

import com.gy.myframework.Exception.model.net.http.HttpServiceException;

import java.io.Serializable;

/**
 * Created by gy on 2015/11/14.
 */
public interface IHttpDealCallBack {
    public Serializable dealReturn(String result) throws HttpServiceException;
}
