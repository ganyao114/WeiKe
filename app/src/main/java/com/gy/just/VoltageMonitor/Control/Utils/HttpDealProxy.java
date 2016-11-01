package com.gy.just.VoltageMonitor.Control.Utils;

import android.util.Log;

import com.gy.myframework.Exception.model.net.http.HttpServiceException;
import com.gy.myframework.Model.net.http.IHttpDealCallBack;

import java.io.Serializable;

/**
 * Created by gy939 on 2016/9/28.
 */
public class HttpDealProxy implements IHttpDealCallBack{

    private IHttpDealCallBack proxy;

    public HttpDealProxy(IHttpDealCallBack proxy) {
        this.proxy = proxy;
    }

    @Override
    public Serializable dealReturn(String result) throws HttpServiceException {
        Log.e("gy",result);
        Serializable res =  proxy.dealReturn(EncryptUtil.decrypt(result));
        return res;
    }
}
