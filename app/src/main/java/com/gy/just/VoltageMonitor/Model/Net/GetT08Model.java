package com.gy.just.VoltageMonitor.Model.Net;

import com.gy.just.VoltageMonitor.Control.Utils.HttpDealProxy;
import com.gy.just.VoltageMonitor.EventFlags.ShowT06Event;
import com.gy.just.VoltageMonitor.EventFlags.ShowT08Event;
import com.gy.just.VoltageMonitor.Model.Bean.T06Bean;
import com.gy.just.VoltageMonitor.Model.Bean.T08Bean;
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
public class GetT08Model extends BaseHttpModelBeta<List<T08Bean>> implements IHttpDealCallBack {
    public GetT08Model(String from, String to,String pgstart,String pglmt) {
        super();
        Map<String,String> par = new HashMap<String,String>();
        par.put(Config.Par.TOKEN_ID,Global.user.getSid());
        par.put(Config.Par.T06.TIMEFROM, from);
        par.put(Config.Par.T06.TIMETO, to);
        par.put(Config.Par.T06.START,pgstart);
        par.put(Config.Par.T06.LIMIT,pglmt);
        httpService.setParams(par);
    }


    @Override
    protected String setUrl() {
        return Config.Url.GET_T08();
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
        List<T08Bean> res = new ArrayList<T08Bean>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
            JSONArray array = jsonObject.getJSONArray(Config.JSONConfig.mainList.LIST_KEY);
            int len = array.length();
            if (array == null||len==0)
                throw new HttpServiceException("空列表");
            for (int i = 0;i <len;i++) {
                T08Bean bean = new T08Bean();
                JSONObject object = array.getJSONObject(i);
                bean.setMeterid(object.getString(Config.JSONConfig.T06.METERID));
                bean.setUnit(object.getString(Config.JSONConfig.T06.UNIT));
                bean.setCitykind(object.getString(Config.JSONConfig.T06.CITYKIND));
                bean.setKind(object.getString(Config.JSONConfig.T06.KIND));
                bean.setVollevel(object.getString("vName"));
                bean.setInstall(object.getString(Config.JSONConfig.T06.INSTALL));
                bean.setDaycut(object.getString("rScore"));
                bean.setMincut(object.getString("fScore"));
                bean.setExccut(object.getString("eScore"));
                bean.setOverupcut(object.getString("csScore"));
                bean.setOverdncut(object.getString("cxScore"));
                bean.setSumcut(object.getString("lostScore"));
                bean.setCountper(object.getString("score"));
                res.add(bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new HttpServiceException(e.toString());
        }

        return (Serializable) res;
    }

    @Override
    public void onError(Object object) {
    }

    @Override
    public <T> void onResult(T t) {
        ShowT08Event event = new ShowT08Event();
        event.setList((List<T08Bean>) t);
        super.onResult(event);
    }
}
