package com.gy.just.VoltageMonitor.Model.Net;

import com.gy.just.VoltageMonitor.Control.Utils.HttpDealProxy;
import com.gy.just.VoltageMonitor.EventFlags.ShowT12Event;
import com.gy.just.VoltageMonitor.Model.Bean.T11Bean;
import com.gy.just.VoltageMonitor.Model.Bean.T12Bean;
import com.gy.just.VoltageMonitor.Model.Global.Config;
import com.gy.just.VoltageMonitor.Model.Global.Global;
import com.gy.myframework.Exception.model.net.http.HttpServiceException;
import com.gy.myframework.IOC.Model.net.http.entity.HttpConnectMode;
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
 * Created by pc on 16/5/24.
 */
public class GetT12Model extends BaseHttpModelBeta<List<T12Bean>> implements IHttpDealCallBack {
    public GetT12Model(String from,String to,String pgstart,String pglmt) {
        super();
        Map<String,String> par = new HashMap<String,String>();
        par.put(Config.Par.TOKEN_ID,Global.user.getSid());
        par.put(Config.Par.T06.TIMEFROM, from);
        par.put(Config.Par.T06.TIMETO, to);
        par.put(Config.Par.T06.START, pgstart);
        par.put(Config.Par.T06.LIMIT,pglmt);
        httpService.setParams(par);
    }

    @Override
    protected String setUrl() {
        return Config.Url.GET_T12();
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
        List<T12Bean> res = new ArrayList<T12Bean>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
            JSONArray array = jsonObject.getJSONArray(Config.JSONConfig.mainList.LIST_KEY);
            int len = array.length();
            if (array == null || len == 0)
                throw new HttpServiceException("空列表");
            for (int i = 0; i < len; i++) {
                T12Bean bean = new T12Bean();
                JSONObject object = array.getJSONObject(i);
                bean.setUnit(object.getString("orgName"));
                bean.setDate(object.getString("date"));
                bean.setMeterid(object.getString("ybId"));
                bean.setInstall(object.getString("address"));
                bean.setFuzeren(Global.user.getLoginname());
                bean.setXunshiren(object.getString("uName"));
                bean.setStart(object.getString("startTime"));
                bean.setEnd(object.getString("endTime"));
                bean.setGpsx(object.getString("longitude"));
                bean.setGpsy(object.getString("latitude"));
                bean.setGpsz(object.getString("height"));
                bean.setKind(object.getString("xsName"));
                res.add(bean);
            }
        }catch (JSONException e) {
            e.printStackTrace();
            throw new HttpServiceException(e.toString());
        }
        return (Serializable) res;
    }

    @Override
    public <T> void onResult(T t) {
        ShowT12Event event = new ShowT12Event();
        event.setList((List<T12Bean>) t);
        super.onResult(event);
    }
}
