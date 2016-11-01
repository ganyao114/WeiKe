package com.gy.just.VoltageMonitor.Model.Net;

import com.gy.just.VoltageMonitor.Control.Utils.HttpDealProxy;
import com.gy.just.VoltageMonitor.EventFlags.ShowAvlsEvent;
import com.gy.just.VoltageMonitor.Model.Bean.AvlMeterBean;
import com.gy.just.VoltageMonitor.Model.Bean.DetailInfo.BaseMessage;
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
 * Created by pc on 16/5/19.
 */
public class GetAvlMeterModel extends BaseHttpModelBeta<List<AvlMeterBean>> implements IHttpDealCallBack{
    public GetAvlMeterModel() {
        super();
        Map<String,String> par = new HashMap<String,String>();
        par.put("start","0");
        par.put("limit","9999");
        par.put(Config.Par.TOKEN_ID,Global.user.getSid());
        httpService.setParams(par);
    }

    @Override
    protected String setUrl() {
        return Config.Url.BASE + "/servlet/mobile/basic/MYbLoad";
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
        List<AvlMeterBean> res = new ArrayList<AvlMeterBean>();
        try {
            JSONObject object = new JSONObject(result);
            if (object == null)
                throw new JSONException("返回错误");
            JSONArray dataArrary = object.getJSONArray(Config.JSONConfig.detailBase.DATA_KEY);
            if (dataArrary == null || dataArrary.length() == 0)
                throw new JSONException("返回错误");
            for (int i=0;i < dataArrary.length();i++){
                JSONObject obj = dataArrary.getJSONObject(i);
                AvlMeterBean bean = new AvlMeterBean();
                bean.setMeterid(obj.getString("yb_id"));
                bean.setName(obj.getString("yb_name"));
                res.add(bean);
            }
        }catch (JSONException e) {
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
        ShowAvlsEvent event = new ShowAvlsEvent();
        event.setList((List<AvlMeterBean>) t);
        super.onResult(event);
    }
}
