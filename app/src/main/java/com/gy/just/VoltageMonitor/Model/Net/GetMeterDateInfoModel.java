package com.gy.just.VoltageMonitor.Model.Net;

import android.util.Log;

import com.gy.just.VoltageMonitor.Control.Utils.HttpDealProxy;
import com.gy.just.VoltageMonitor.Model.Bean.DetailInfo.DateOlInfo;
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
 * Created by gy on 2016/4/28.
 */
public class GetMeterDateInfoModel extends BaseHttpModelBeta<DateOlInfo> implements IHttpDealCallBack{

    public GetMeterDateInfoModel(String meter_id, String time) {
        super();
        Map<String,String> par = new HashMap<String,String>();
        par.put(Config.Par.DETAIL.METER_ID,meter_id);
        par.put(Config.Par.DETAIL.TIME,time);
        par.put(Config.Par.TOKEN_ID,Global.user.getSid());
        httpService.setParams(par);
    }

    @Override
    protected String setUrl() {
        return Config.Url.GET_OLINFO();
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
        DateOlInfo dateInfo = new DateOlInfo();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
            JSONArray array = jsonObject.getJSONArray(Config.JSONConfig.T04.DATA_KEY);
            if (array == null||array.length() == 0)
                throw new HttpServiceException("数据异常");
            JSONObject object = array.getJSONObject(0);
            String runinfoBase = Config.JSONConfig.T04.RUNINFOBASE;
            List<Integer> oldays = new ArrayList<Integer>();
            for (int j = 1;j <= 9;j++){
                Log.e("gy", object.getString(runinfoBase + "0" + j));
                if (object.getString(runinfoBase + "0" + j) != "null"){
                    Log.e("gy", object.getString(runinfoBase + "0" + j));
                    oldays.add(j);
                }
            }
            for (int j = 10;j <= 31;j++){
                if (object.getString(runinfoBase + j) != "null"){
                    oldays.add(j);
                }
            }
            dateInfo.setOLDays(oldays);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new HttpServiceException(e.toString());
        }
        return (Serializable) dateInfo;
    }

    @Override
    public <T> void onResult(T t) {
        super.onResult(t);
    }

    @Override
    public void onError(Object object) {
        Log.e("gy", (String) object);
    }
}
