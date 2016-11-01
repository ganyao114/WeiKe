package com.gy.just.VoltageMonitor.Model.Net;

import com.gy.just.VoltageMonitor.Control.Utils.HttpDealProxy;
import com.gy.just.VoltageMonitor.EventFlags.ShowT10Event;
import com.gy.just.VoltageMonitor.Model.Bean.T06Bean;
import com.gy.just.VoltageMonitor.Model.Bean.T10Bean;
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
 * Created by pc on 16/5/23.
 */
public class GetT10Model extends BaseHttpModelBeta<List<T10Bean>> implements IHttpDealCallBack {

    public GetT10Model(String from,String to,String pgstart,String pglmt) {
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
        return Config.Url.GET_T10();
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
        List<T10Bean> res = new ArrayList<T10Bean>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
            JSONArray array = jsonObject.getJSONArray(Config.JSONConfig.mainList.LIST_KEY);
            int len = array.length();
            if (array == null || len == 0)
                throw new HttpServiceException("空列表");
            for (int i = 0; i < len; i++) {
                T10Bean bean = new T10Bean();
                JSONObject object = array.getJSONObject(i);
                bean.setUnit(object.getString(Config.JSONConfig.T10.UNIT));
                bean.setCitykind(object.getString(Config.JSONConfig.T10.CITYKIND));
                bean.setInstall(object.getString(Config.JSONConfig.T10.INSTALL));
                bean.setKind(object.getString(Config.JSONConfig.T10.KIND));
                bean.setMeterid(object.getString(Config.JSONConfig.T10.METER_ID));
                bean.setVollevel(object.getString(Config.JSONConfig.T10.VOLLEVEL));
                bean.setOverup1_cut(object.getString("cs1Score"));
                bean.setOverup2_cut(object.getString("cs2Score"));
                bean.setOverup3_cut(object.getString("cs3Score"));
                bean.setOverup4_cut(object.getString("cs4Score"));
                bean.setOverupsum_cut(object.getString("csScore"));
                bean.setOverdn1_cut(object.getString("cx1Score"));
                bean.setOverdn2_cut(object.getString("cx2Score"));
                bean.setOverdn3_cut(object.getString("cx3Score"));
                bean.setOverdn4_cut(object.getString("cx4Score"));
                bean.setOverdnsum_cut(object.getString("cxScore"));
                bean.setCut_sum(object.getString("zScore"));
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
        ShowT10Event event = new ShowT10Event();
        event.setList((List<T10Bean>) t);
        super.onResult(event);
    }
}
