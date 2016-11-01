package com.gy.just.VoltageMonitor.Model.Net;

import android.util.Log;

import com.gy.just.VoltageMonitor.Control.Utils.HttpDealProxy;
import com.gy.just.VoltageMonitor.Control.Utils.MathUtils;
import com.gy.just.VoltageMonitor.Control.Utils.TimeUtils;
import com.gy.just.VoltageMonitor.EventFlags.ShowMainList2Event;
import com.gy.just.VoltageMonitor.EventFlags.ShowMainListEvent;
import com.gy.just.VoltageMonitor.Model.Bean.CardPojo.MainTabCard2Pojo;
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
public class GetExcListModel extends BaseHttpModelBeta<List<MainTabCard2Pojo>> implements IHttpDealCallBack{

    public GetExcListModel() {
        super();
        Map<String,String> par = new HashMap<String,String>();
        par.put(Config.Par.mainList.DATE_KEY, TimeUtils.getDateStr(Global.defaultData));
        par.put(Config.Par.TOKEN_ID,Global.user.getSid());
        httpService.setParams(par);
    }

    @Override
    protected String setUrl() {
        return Config.Url.GETEXCLIST_URL();
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
        List<MainTabCard2Pojo> res = new ArrayList<MainTabCard2Pojo>();
        try {
            JSONObject object = new JSONObject(result);
            JSONArray array = object.getJSONArray(Config.JSONConfig.mainList.LIST_KEY);
            if (array == null||array.length()==0)
                throw new HttpServiceException("空列表");
            for (int i = 0;i <array.length();i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                MainTabCard2Pojo tab = new MainTabCard2Pojo();
                tab.setMeterkind(jsonObject.getString(Config.JSONConfig.Exc_List.METER_KIND));
                tab.setPtid(jsonObject.getString(Config.JSONConfig.Exc_List.PT_ID));
                tab.setMetersum(jsonObject.getInt(Config.JSONConfig.Exc_List.METER_SUM));
                tab.setDaycount(jsonObject.getInt(Config.JSONConfig.Exc_List.OFFLINES));
                tab.setMincount(jsonObject.getInt(Config.JSONConfig.Exc_List.EXCS));
                tab.setExceptions(jsonObject.getInt(Config.JSONConfig.Exc_List.OVER_LIMITS));
                tab.setDay_progress(MathUtils.per2int(jsonObject.getString(Config.JSONConfig.Exc_List.OFFLINE_PER)));
                tab.setMin_progress(MathUtils.per2int(jsonObject.getString(Config.JSONConfig.Exc_List.EXC_PER)));
                tab.setException_progress(MathUtils.per2int(jsonObject.getString(Config.JSONConfig.Exc_List.OVWE_PER)));
                tab.setMin_per(jsonObject.getString(Config.JSONConfig.Exc_List.EXC_PER));
                tab.setDay_per(jsonObject.getString(Config.JSONConfig.Exc_List.OFFLINE_PER));
                tab.setException_per(jsonObject.getString(Config.JSONConfig.Exc_List.OVWE_PER));
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
        ShowMainList2Event event = new ShowMainList2Event();
        event.setList((List<MainTabCard2Pojo>) t);
        EventPoster.Broadcast(event);
    }

    @Override
    public void onError(Object object) {
        Log.e("gy",(String) object);
    }
}
