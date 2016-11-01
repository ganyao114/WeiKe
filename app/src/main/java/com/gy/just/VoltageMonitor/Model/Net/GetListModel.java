package com.gy.just.VoltageMonitor.Model.Net;

import android.util.Log;

import com.gy.just.VoltageMonitor.Control.Utils.HttpDealProxy;
import com.gy.just.VoltageMonitor.Control.Utils.TimeUtils;
import com.gy.just.VoltageMonitor.EventFlags.ShowMainListEvent;
import com.gy.just.VoltageMonitor.Model.Bean.CardPojo.MainTabCardPojo;
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
 * Created by gy on 2016/4/24.
 */
public class GetListModel extends BaseHttpModelBeta<List<MainTabCardPojo>> implements IHttpDealCallBack{

    public GetListModel() {
        super();
        Map<String,String> par = new HashMap<String,String>();
        par.put(Config.Par.mainList.DATE_KEY, TimeUtils.getDateStr(Global.defaultData));
        par.put(Config.Par.TOKEN_ID,Global.user.getSid());
        httpService.setParams(par);
    }

    @Override
    protected String setUrl() {
        return Config.Url.GETLIST_URL();
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
        List<MainTabCardPojo> res = new ArrayList<MainTabCardPojo>();
        try {
            JSONObject object = new JSONObject(result);
            JSONArray array = object.getJSONArray(Config.JSONConfig.mainList.LIST_KEY);
            if (array == null||array.length()==0)
                throw new HttpServiceException("空列表");
            for (int i = 0;i <array.length();i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                MainTabCardPojo tab = new MainTabCardPojo();
                tab.setMeterkind(jsonObject.getString(Config.JSONConfig.mainList.PT_NAME));
                tab.setPtid(jsonObject.getString(Config.JSONConfig.mainList.PT_ID));
                tab.setMetersum(jsonObject.getInt(Config.JSONConfig.mainList.METER_SUM));
                tab.setDaycount(jsonObject.getInt(Config.JSONConfig.mainList.DAY_DATAS));
                tab.setMincount(jsonObject.getInt(Config.JSONConfig.mainList.MIN_COUNTS));
                tab.setExceptions(jsonObject.getInt(Config.JSONConfig.mainList.EXC_COUNT));
                tab.setDay_progress((int) jsonObject.getDouble(Config.JSONConfig.mainList.DAY_OL_RATE));
                tab.setMin_progress((int) jsonObject.getDouble(Config.JSONConfig.mainList.MIN_OL_RATE));
                tab.setException_progress((int) jsonObject.getDouble(Config.JSONConfig.mainList.EXC_RATE));
                tab.setMin_per(jsonObject.getDouble(Config.JSONConfig.mainList.MIN_OL_RATE)+"%");
                tab.setDay_per(jsonObject.getDouble(Config.JSONConfig.mainList.DAY_OL_RATE)+"%");
                tab.setException_per(jsonObject.getDouble(Config.JSONConfig.mainList.EXC_RATE)+"%");
                res.add(tab);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            throw new HttpServiceException(e.toString());
        }

        return (Serializable) res;
    }

    @Override
    public <T> void onResult(T t) {
        ShowMainListEvent event = new ShowMainListEvent();
        event.setList((List<MainTabCardPojo>) t);
        EventPoster.Broadcast(event);
    }

    @Override
    public void onError(Object object) {
        Log.e("gy",(String) object);
    }
}
