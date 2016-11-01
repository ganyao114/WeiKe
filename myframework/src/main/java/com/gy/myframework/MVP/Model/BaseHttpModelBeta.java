package com.gy.myframework.MVP.Model;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.gy.myframework.IOC.Model.net.http.entity.HttpConnectMode;
import com.gy.myframework.IOC.Service.event.impl.EventPoster;
import com.gy.myframework.Model.net.http.IHttpDealCallBack;
import com.gy.myframework.Model.net.http.impl.MyHttpService;
import com.gy.myframework.Service.thread.pool.impl.MyWorkThreadQueue;
import com.gy.myframework.Service.thread.templet.HttpThreadTemplet;
import com.gy.myframework.Service.thread.templet.configs.HttpTheadConfigBean;

import java.io.Serializable;

/**
 * Created by gy on 2016/4/23.
 */
public abstract class BaseHttpModelBeta<T> implements IBaseModel<T>{

    protected MyHttpService httpService;
    protected HttpThreadTemplet thread;
    protected IHttpDealCallBack dealCallBack;
    protected ModelHandler<T> handler;
    protected HttpConnectMode mode;
    protected String url;

    public BaseHttpModelBeta() {
        initModel();
    }

    @Override
    public void initModel() {
        httpService = new MyHttpService();
        handler = new ModelHandler<T>();
        dealCallBack = setCallBack();
        url = setUrl();
        mode = setconMode();
        httpService.setUrl(url);
        httpService.setDealCallBack(dealCallBack);
        thread = new HttpThreadTemplet(handler,httpService) {
            @Override
            protected void OnRun() throws Exception {
                T res = null;
                switch (mode){
                    case Post:
                        res = (T) httpService.getDataPost();
                        break;
                    case Get:
                        res = (T) httpService.getDataGet();
                        break;
                    case Download:
                        httpService.download();
                        break;
                    case UpLoad:
                        res = (T) httpService.upload();
                        break;
                }
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putSerializable("result", (Serializable) res);
                msg.what = 1;
                msg.setData(data);
                handler.sendMessage(msg);
            }

            @Override
            protected HttpTheadConfigBean SetConfig() {
                return setConfig();
            }

            @Override
            public Serializable dealReturn(String result) {
                return null;
            }
        };
    }

    public void doHttp(){
        MyWorkThreadQueue.AddTask(thread);
    }

    @Override
    public void destoryModel() {

    }

    @Override
    public <T> void onResult(T t) {
        EventPoster.Broadcast(t);
    }

    @Override
    public void onError(Object object) {
        EventPoster.Broadcast(object);
    }

    private class ModelHandler<T> extends Handler {

        @Override
        public void handleMessage(Message msg) {

            int Flag = msg.what;
            switch (Flag) {
                case 0:
                    String errmsg = (String) msg.getData().getSerializable(
                            "ErroMsg");
                    onError(errmsg);
                    break;
                case 1:
                    T result = (T) msg.getData().getSerializable("result");
                    onResult(result);
                    break;
                default:
                    break;
            }

        }

    }


    public void setDealCallBack(IHttpDealCallBack dealCallBack) {
        this.dealCallBack = dealCallBack;
    }


    public MyHttpService getHttpService() {
        return httpService;
    }

    protected abstract String setUrl();
    protected abstract IHttpDealCallBack setCallBack();
    protected abstract HttpConnectMode setconMode();
    protected abstract HttpTheadConfigBean setConfig();
}
