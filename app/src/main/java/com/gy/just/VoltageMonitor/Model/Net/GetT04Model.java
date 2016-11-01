package com.gy.just.VoltageMonitor.Model.Net;

import android.util.Log;

import com.gy.just.VoltageMonitor.Control.Utils.HttpDealProxy;
import com.gy.just.VoltageMonitor.EventFlags.ShowT04Event;
import com.gy.just.VoltageMonitor.Model.Bean.LarTabBean.T04Bean;
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
 * Created by gy on 2016/4/30.
 */
public class GetT04Model extends BaseHttpModelBeta<List<T04Bean>> implements IHttpDealCallBack{

    public GetT04Model(int year, int month, String pt_id,String pgstart,String pglimt) {
        super();
        Map<String,String> par = new HashMap<String,String>();
        par.put(Config.Par.TOKEN_ID,Global.user.getSid());
        par.put(Config.Par.T04.START,pgstart);
        par.put(Config.Par.T04.LIMIT, pglimt);
        par.put(Config.Par.T04.YEAR,year+"");
        String monthstr;
        if (month < 10)
            monthstr = "0" + month;
        else
            monthstr = month+"";
        par.put(Config.Par.T04.MONTH,monthstr);
        if (pt_id!="0"){
            par.put("pointType",pt_id+"");
        }
        httpService.setParams(par);
    }

    @Override
    protected String setUrl() {
        return Config.Url.GET_T04();
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
        List<T04Bean> list = new ArrayList<T04Bean>();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray array = jsonObject.getJSONArray(Config.JSONConfig.T04.DATA_KEY);
            if (array == null||array.length() == 0)
                throw new HttpServiceException("数据异常");
            for (int i = 0;i < array.length(); i++){
                T04Bean bean = new T04Bean();
                JSONObject object = array.getJSONObject(i);
                bean.setMeterid(object.getString(Config.JSONConfig.T04.METER_ID));
                bean.setKind(object.getString(Config.JSONConfig.T04.KIND));
                bean.setInstall(object.getString(Config.JSONConfig.T04.INSTALL));
                bean.setOwner(object.getString(Config.JSONConfig.T04.OWNER));
                String runinfoBase = Config.JSONConfig.T04.RUNINFOBASE;
                boolean[] isols = new boolean[31];
                for (int j = 1;j <= 9;j++){
                    if (object.getString(runinfoBase+ "0" + j) != "null"){
                        isols[j-1] = true;
                    }else {
                        isols[j-1] = false;
                    }
                }
                for (int j = 10;j <= 31;j++){
                    if (object.getString(runinfoBase + j) != "null"){
                        isols[j-1] = true;
                    }else {
                        isols[j-1] = false;
                    }
                }
                bean.setIsol(isols);
                list.add(bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            onError(e.toString());
        }
        return (Serializable) list;
    }

    @Override
    public <T> void onResult(T t) {
        ShowT04Event event = new ShowT04Event();
        event.setList((List<T04Bean>) t);
        EventPoster.Broadcast(event);
    }

    @Override
    public void onError(Object object) {
        Log.e("gy", (String) object);
    }
}
