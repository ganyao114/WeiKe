package com.gy.just.VoltageMonitor.Model.Net;

import com.gy.just.VoltageMonitor.Control.Utils.HttpDealProxy;
import com.gy.just.VoltageMonitor.EventFlags.ShowT08Event;
import com.gy.just.VoltageMonitor.EventFlags.ShowT09Event;
import com.gy.just.VoltageMonitor.Model.Bean.T08Bean;
import com.gy.just.VoltageMonitor.Model.Bean.T09Bean;
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
 * Created by pc on 16/5/15.
 */
public class GetT09Model extends BaseHttpModelBeta<List<T09Bean>> implements IHttpDealCallBack {
    public GetT09Model(String meterid,String from, String to) {
        super();
        Map<String,String> par = new HashMap<String,String>();
        par.put("ybId",meterid);
        par.put(Config.Par.TOKEN_ID,Global.user.getSid());
        par.put(Config.Par.T06.TIMEFROM, from);
        par.put(Config.Par.T06.TIMETO, to);
        httpService.setParams(par);
    }


    @Override
    protected String setUrl() {
        return Config.Url.GET_T09();
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
        List<T09Bean> res = new ArrayList<T09Bean>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
            JSONArray array = jsonObject.getJSONArray(Config.JSONConfig.mainList.LIST_KEY);
            int len = array.length();
            if (array == null||len==0)
                throw new HttpServiceException("空列表");
            for (int i = 0;i <len;i++) {
                T09Bean bean = new T09Bean();
                JSONObject object = array.getJSONObject(i);
                bean.setDate(object.getString("dateStr"));
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
        ShowT09Event event = new ShowT09Event();
        event.setList((List<T09Bean>) t);
        super.onResult(event);
    }
}
