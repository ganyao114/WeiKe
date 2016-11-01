package com.gy.just.VoltageMonitor.Model.Net;

import com.gy.just.VoltageMonitor.Model.Bean.CardPojo.MainTabCardPojo;
import com.gy.myframework.Exception.model.net.http.HttpServiceException;
import com.gy.myframework.IOC.Model.net.http.entity.HttpConnectMode;
import com.gy.myframework.MVP.Model.BaseHttpModelBeta;
import com.gy.myframework.Model.net.http.IHttpDealCallBack;
import com.gy.myframework.Service.thread.templet.configs.HttpTheadConfigBean;

import java.io.Serializable;

/**
 * Created by gy on 2016/4/27.
 */
public class GetMainTabModel extends BaseHttpModelBeta<MainTabCardPojo> implements IHttpDealCallBack{
    @Override
    protected String setUrl() {
        return null;
    }

    @Override
    protected IHttpDealCallBack setCallBack() {
        return null;
    }

    @Override
    protected HttpConnectMode setconMode() {
        return HttpConnectMode.Post;
    }

    @Override
    protected HttpTheadConfigBean setConfig() {
        return new HttpTheadConfigBean(false,0,"","","");
    }

    @Override
    public Serializable dealReturn(String result) throws HttpServiceException {

        return null;
    }
}
