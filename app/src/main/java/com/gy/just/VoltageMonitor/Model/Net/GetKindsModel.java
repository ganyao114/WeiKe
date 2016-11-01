package com.gy.just.VoltageMonitor.Model.Net;

import com.gy.just.VoltageMonitor.Control.Utils.HttpDealProxy;
import com.gy.just.VoltageMonitor.EventFlags.ShowKindsEvent;
import com.gy.just.VoltageMonitor.Model.Bean.KindsCardPojo;
import com.gy.just.VoltageMonitor.Model.Global.Config;
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
import java.util.List;

/**
 * Created by gy on 2016/5/2.
 */
public class GetKindsModel extends BaseHttpModelBeta<List<KindsCardPojo>> implements IHttpDealCallBack{

    public GetKindsModel() {
        super();
    }

    @Override
    protected String setUrl() {
        return Config.Url.GET_TYPELIST();
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
        List<KindsCardPojo> res = new ArrayList<KindsCardPojo>();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray array = jsonObject.getJSONArray("data");
            if (array==null||array.length()==0)
                throw new JSONException("返回错误");
            KindsCardPojo all = new KindsCardPojo();
            all.setPt_name("所有类型");
            all.setPt_id("0");
            res.add(all);
            for (int i = 0;i < array.length();i++){
                KindsCardPojo pojo = new KindsCardPojo();
                JSONObject object = array.getJSONObject(i);
                pojo.setPt_id(object.getString("pt_id"));
                pojo.setPt_name(object.getString("pt_name"));
                res.add(pojo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new HttpServiceException(e.toString());
        }
        return (Serializable) res;
    }

    @Override
    public <T> void onResult(T t) {
        ShowKindsEvent event = new ShowKindsEvent();
        event.setList((List<KindsCardPojo>) t);
        EventPoster.Broadcast(event);
    }
}
