package com.gy.just.VoltageMonitor.Model.Net;

import com.gy.just.VoltageMonitor.Control.Utils.HttpDealProxy;
import com.gy.just.VoltageMonitor.EventFlags.ShowVolListEvent;
import com.gy.just.VoltageMonitor.Model.Bean.DailinfoPojo;
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
 * Created by gy on 2016/4/29.
 */
public class GetVolListTabModel extends BaseHttpModelBeta<List<DailinfoPojo>> implements IHttpDealCallBack{


    public GetVolListTabModel(String meterid, String date) {
        super();
        Map<String,String> par = new HashMap<String,String>();
        par.put(Config.Par.volTab.METER_ID,meterid);
        par.put(Config.Par.volTab.DATE,date);
        par.put(Config.Par.TOKEN_ID,Global.user.getSid());
        httpService.setParams(par);
    }

    @Override
    protected String setUrl() {
        return Config.Url.VOL_TAB();
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
        List<DailinfoPojo> list = new ArrayList<DailinfoPojo>();
        try {
            JSONObject object = new JSONObject(result);
            JSONObject stateObj = object.getJSONObject(Config.JSONConfig.VolTab.STATE_KEY);
            if (!stateObj.getBoolean(Config.JSONConfig.VolTab.SUCCESS))
                throw new HttpServiceException("返回错误");
            JSONArray array = object.getJSONArray(Config.JSONConfig.VolTab.DATA_KEY);
            int len = array.length();
            if (len == 0)
                throw new HttpServiceException("返回错误");
            for (int i = 0;i < len;i ++){
                JSONObject obj = array.getJSONObject(i);
                DailinfoPojo pojo = new DailinfoPojo();
                pojo.setTime(obj.getString(Config.JSONConfig.VolTab.NODE_TIME));
                pojo.setVol(obj.getString(Config.JSONConfig.VolTab.NODE_VOL));
                int type = obj.getInt(Config.JSONConfig.VolTab.FLAG);
                if (type == 1){
                    pojo.setIsoverup("是");
                    pojo.setCut(obj.getString(Config.JSONConfig.VolTab.CUT));
                }else if (type == -1){
                    pojo.setIsoverdn("是");
                    pojo.setCut(obj.getString(Config.JSONConfig.VolTab.CUT));
                }
                list.add(pojo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new HttpServiceException(e.toString());
        }
        return (Serializable) list;
    }

    @Override
    public void onError(Object object) {
    }

    @Override
    public <T> void onResult(T t) {
        ShowVolListEvent event = new ShowVolListEvent();
        event.setList((List<DailinfoPojo>) t);
        EventPoster.Broadcast(event);
    }
}
