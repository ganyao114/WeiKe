package com.gy.just.VoltageMonitor.Model.Net;

import com.gy.just.VoltageMonitor.Control.Utils.HttpDealProxy;
import com.gy.just.VoltageMonitor.Model.Bean.DetailInfo.VolTabBean;
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
 * Created by gy on 2016/4/29.
 */
public class GetVolTabModel extends BaseHttpModelBeta<VolTabBean> implements IHttpDealCallBack{

    private String callback;

    public GetVolTabModel(String meterid,String date,String callback) {
        super();
        this.callback = callback;
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
        VolTabBean res = new VolTabBean();
        try {
            JSONObject object = new JSONObject(result);
            JSONObject stateObj = object.getJSONObject(Config.JSONConfig.VolTab.STATE_KEY);
            if (!stateObj.getBoolean(Config.JSONConfig.VolTab.SUCCESS))
                throw new HttpServiceException("返回错误");
            JSONArray array = object.getJSONArray(Config.JSONConfig.VolTab.DATA_KEY);
            int len = array.length();
            if (len == 0)
                throw new HttpServiceException("返回错误");
            VolTabBean.Node[] nodes = new VolTabBean.Node[len];
            for (int i = 0;i < len;i ++){
                VolTabBean.Node node = res.new Node();
                JSONObject obj = array.getJSONObject(i);
                node.rn = obj.getInt(Config.JSONConfig.VolTab.NODE_ID);
                node.timeStr = obj.getString(Config.JSONConfig.VolTab.NODE_TIME);
                node.vol = (float) obj.getDouble(Config.JSONConfig.VolTab.NODE_VOL);
                nodes[i] = node;
            }
            res.setList(nodes);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new HttpServiceException(e.toString());
        }
        return res;
    }

    @Override
    public void onError(Object object) {
    }

    @Override
    public <T> void onResult(T t) {
        EventPoster.Post(callback,t);
    }
}
