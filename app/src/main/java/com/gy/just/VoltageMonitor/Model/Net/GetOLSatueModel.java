package com.gy.just.VoltageMonitor.Model.Net;

import com.gy.just.VoltageMonitor.Control.Utils.HttpDealProxy;
import com.gy.just.VoltageMonitor.EventFlags.ShowOlListEvent;
import com.gy.just.VoltageMonitor.Model.Bean.OnlineStatueBean;
import com.gy.just.VoltageMonitor.Model.Bean.T11Bean;
import com.gy.just.VoltageMonitor.Model.Global.Config;
import com.gy.just.VoltageMonitor.Model.Global.Global;
import com.gy.just.VoltageMonitor.View.Activity.OnlineSatueActivity;
import com.gy.myframework.Exception.model.net.http.HttpServiceException;
import com.gy.myframework.IOC.Model.net.http.entity.HttpConnectMode;
import com.gy.myframework.MVP.Model.BaseHttpModelBeta;
import com.gy.myframework.Model.net.http.IHttpDealCallBack;
import com.gy.myframework.Service.thread.templet.configs.HttpTheadConfigBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.PipedOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by pc on 16/5/24.
 */
public class GetOLSatueModel extends BaseHttpModelBeta<List<OnlineStatueBean>> implements IHttpDealCallBack {
    private OnlineSatueActivity call;
    public GetOLSatueModel(String year, String month, OnlineSatueActivity onlineSatueActivity) {
        super();
        call = onlineSatueActivity;
        Map<String,String> par = new HashMap<String,String>();
        par.put(Config.Par.TOKEN_ID,Global.user.getSid());
        par.put("year", year);
        par.put("month", month);
        par.put(Config.Par.T06.START,"0");
        par.put(Config.Par.T06.LIMIT,"9999");
        httpService.setParams(par);
    }

    @Override
    protected String setUrl() {
        return Config.Url.OLSTATUE();
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
        List<OnlineStatueBean> res = new ArrayList<OnlineStatueBean>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
            JSONArray array = jsonObject.getJSONArray(Config.JSONConfig.mainList.LIST_KEY);
            int len = array.length();
            if (array == null || len == 0)
                throw new HttpServiceException("空列表");
            for (int i = 0; i < len; i++) {
                OnlineStatueBean bean = new OnlineStatueBean();
                JSONObject object = array.getJSONObject(i);
                bean.setUsername(object.getString("userName"));
                bean.setUnit(object.getString("orgName"));
                int webs = object.getInt("webTimes");
                int apps = object.getInt("appTimes");
                bean.setApp(apps+"");
                bean.setWeb(webs + "");
                bean.setSum((apps + webs) + "");
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
        ShowOlListEvent event = new ShowOlListEvent();
        event.setList((List<OnlineStatueBean>) t);
        call.showOlStatueList(event);
    }
}
