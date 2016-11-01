package com.gy.just.VoltageMonitor.Model.Net;

import com.gy.just.VoltageMonitor.Control.Utils.HttpDealProxy;
import com.gy.just.VoltageMonitor.EventFlags.ShowT06Event;
import com.gy.just.VoltageMonitor.Model.Bean.T06Bean;
import com.gy.just.VoltageMonitor.Model.Global.Config;
import com.gy.just.VoltageMonitor.Model.Global.Global;
import com.gy.myframework.Exception.model.net.http.HttpServiceException;
import com.gy.myframework.IOC.Model.net.http.entity.HttpConnectMode;
import com.gy.myframework.IOC.Service.event.impl.EventPoster;
import com.gy.myframework.MVP.Model.BaseHttpModelBeta;
import com.gy.myframework.Model.net.http.IHttpDealCallBack;
import com.gy.myframework.Service.thread.templet.configs.HttpTheadConfigBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by pc on 16/5/15.
 */
public class GetT06Model extends BaseHttpModelBeta<List<T06Bean>> implements IHttpDealCallBack {
    private String callback;
    public GetT06Model(String meterid,String from,String to) {
        super();
        Map<String,String> par = new HashMap<String,String>();
        par.put(Config.Par.TOKEN_ID,Global.user.getSid());
        par.put(Config.Par.T06.LIST,"rsjrate");
        par.put(Config.Par.T06.TIMEFROM, from);
        par.put(Config.Par.T06.TIMETO, to);
        par.put(Config.Par.T06.START,"0");
        par.put(Config.Par.T06.LIMIT,"9999");
        par.put(Config.Par.T06.YBID,meterid);
        httpService.setParams(par);
    }

    public GetT06Model(String meterid,String from,String to,String pgstart,String pglmt) {
        super();
        Map<String,String> par = new HashMap<String,String>();
        par.put(Config.Par.TOKEN_ID, Global.user.getLoginname());
        par.put(Config.Par.T06.LIST,"rangerate");
        par.put(Config.Par.T06.TIMEFROM, from);
        par.put(Config.Par.T06.TIMETO, to);
        par.put(Config.Par.T06.START,pgstart);
        par.put(Config.Par.T06.LIMIT,pglmt);
        httpService.setParams(par);
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    @Override
    protected String setUrl() {
        return Config.Url.GET_T06();
    }

    @Override
    protected IHttpDealCallBack setCallBack() {
        return new HttpDealProxy(this);
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
        List<T06Bean> res = new ArrayList<T06Bean>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
            JSONArray array = jsonObject.getJSONArray(Config.JSONConfig.mainList.LIST_KEY);
            int len = array.length();
            if (array == null||len==0)
                throw new HttpServiceException("空列表");
            for (int i = 0;i <len;i++) {
                T06Bean bean = new T06Bean();
                JSONObject object = array.getJSONObject(i);
                bean.setMeterid(object.getString(Config.JSONConfig.T06.METERID));
                bean.setUnit(object.getString(Config.JSONConfig.T06.UNIT));
                bean.setCitykind(object.getString(Config.JSONConfig.T06.CITYKIND));
                bean.setKind(object.getString(Config.JSONConfig.T06.KIND));
                bean.setVollevel(object.getString(Config.JSONConfig.T06.VOLLEVEL));
                bean.setSumruntime(object.getString(Config.JSONConfig.T06.SUNRUNTIME));
                bean.setOverupper(object.getString(Config.JSONConfig.T06.OVER_UPPER_PER));
                bean.setOveruptime(object.getString(Config.JSONConfig.T06.OVER_UPPER));
                bean.setOverdntime(object.getString(Config.JSONConfig.T06.OVER_DN));
                bean.setOverdnper(object.getString(Config.JSONConfig.T06.OVER_DN_PER));
                bean.setMax(object.getString(Config.JSONConfig.T06.MAX));
                bean.setMaxtime(object.getString(Config.JSONConfig.T06.MAX_TIME));
                bean.setMin(object.getString(Config.JSONConfig.T06.MIN));
                bean.setMintime(object.getString(Config.JSONConfig.T06.MIN_TIME));
                bean.setPassper(object.getString(Config.JSONConfig.T06.PASS_PER));
                bean.setAvr(object.getString(Config.JSONConfig.T06.AVR));
                bean.setInstall(object.getString(Config.JSONConfig.T06.INSTALL));
                bean.setPassTime(object.getString(Config.JSONConfig.T06.PASS_TIME));
                if (object.has(Config.JSONConfig.T06.DATE))
                    bean.setDate(object.getString(Config.JSONConfig.T06.DATE));
                res.add(bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return (Serializable) res;
    }

    @Override
    public void onError(Object object) {
    }

    @Override
    public <T> void onResult(T t) {
        ShowT06Event event = new ShowT06Event();
        event.setList((List<T06Bean>) t);
        if (callback == null)
            super.onResult(event);
        else
            EventPoster.Post(callback, event);
    }
}
