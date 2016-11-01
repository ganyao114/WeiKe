package com.gy.just.VoltageMonitor.Model.Net;

import android.util.Log;

import com.gy.just.VoltageMonitor.Control.Utils.HttpDealProxy;
import com.gy.just.VoltageMonitor.Model.Bean.DetailInfo.BaseMessage;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gy on 2016/4/28.
 */
public class GetBaseMessageModel extends BaseHttpModelBeta<BaseMessage> implements IHttpDealCallBack{


    public GetBaseMessageModel(String meter_id){
        super();
        Map<String,String> par = new HashMap<String,String>();
        par.put(Config.Par.baseMsg.METER_ID,meter_id);
        par.put(Config.Par.TOKEN_ID,Global.user.getSid());
        httpService.setParams(par);
    }

    @Override
    protected String setUrl() {
        return Config.Url.BASEMSG_URL();
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
        BaseMessage res = new BaseMessage();
        try {
            JSONObject object = new JSONObject(result);
            if (object == null)
                throw new JSONException("返回错误");
            JSONArray dataArrary = object.getJSONArray(Config.JSONConfig.detailBase.DATA_KEY);
            if (dataArrary==null||dataArrary.length()==0)
                throw new JSONException("返回错误");
            JSONObject dataObj = dataArrary.getJSONObject(0);
            res.setMeter_No(dataObj.getString(Config.JSONConfig.detailBase.ID));
            res.setMeter_Name(dataObj.getString(Config.JSONConfig.detailBase.NAME));
            res.setMeter_kind(dataObj.getString(Config.JSONConfig.detailBase.KIND));
            res.setInstall_Loacl(dataObj.getString(Config.JSONConfig.detailBase.INSTALL_LOCAL));
            res.setMeter_City_kind(dataObj.getString(Config.JSONConfig.detailBase.CITY_KIND));
//            res.setMng_Person(object.getString(Config.JSONConfig.detailBase.MNG_PERSON));
            res.setVol_Level(dataObj.getString(Config.JSONConfig.detailBase.VOL_LEVEL));
            res.setUser_Unit(dataObj.getString(Config.JSONConfig.detailBase.USER_UNIT));
            res.setPx(dataObj.getString("yb_longitude"));
            res.setPy(dataObj.getString("yb_latitude"));
        } catch (JSONException e) {
            e.printStackTrace();
            throw new HttpServiceException(e.toString());
        }
        return res;
    }


    @Override
    public <T> void onResult(T t) {
        EventPoster.Broadcast(t);
    }

    @Override
    public void onError(Object object) {
        Log.e("gy",(String) object);
    }
}
