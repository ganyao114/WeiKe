package com.gy.just.VoltageMonitor.Model.Net;

import com.gy.just.VoltageMonitor.Control.Utils.HttpDealProxy;
import com.gy.just.VoltageMonitor.EventFlags.ShowT05Event;
import com.gy.just.VoltageMonitor.Model.Bean.T05Bean;
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
 * Created by gy on 2016/5/3.
 */
public class GetT05Model extends BaseHttpModelBeta<List<T05Bean>> implements IHttpDealCallBack{

    public GetT05Model(String from,String to) {
        super();
        Map<String,String> par = new HashMap<String,String>();
        par.put(Config.Par.TOKEN_ID,Global.user.getSid());
        par.put(Config.Par.T05.FROM,from);
        par.put(Config.Par.T05.TO, to);
        httpService.setParams(par);
    }

    @Override
    protected String setUrl() {
        return Config.Url.GET_T05();
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
        List<T05Bean> res = null;
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray array = jsonObject.getJSONArray(Config.JSONConfig.mainList.LIST_KEY);
            int len = array.length();
            if (array == null||len==0)
                throw new HttpServiceException("空列表");
            Map<String,List<T05Bean>> map = new HashMap<String,List<T05Bean>>();
            for (int i = 0;i <len;i++) {
                JSONObject object = array.getJSONObject(i);
                T05Bean bean = new T05Bean();
                String unit = object.getString(Config.JSONConfig.T05.UNIT);
                bean.setUnit(unit);
                bean.setP_kind(object.getString(Config.JSONConfig.T05.KIND));
                bean.setPer_pass((float) object.getDouble(Config.JSONConfig.T05.PASS_PER));
                bean.setCount_rate(object.getString(Config.JSONConfig.T05.COUNT_RATE));
                bean.setMeter_counts(object.getInt(Config.JSONConfig.T05.METER_COUNT));
                bean.setSum((float) object.getDouble(Config.JSONConfig.T05.SUM));
                bean.setDay_cut((float) object.getDouble(Config.JSONConfig.T05.DAY_CUT));
                bean.setMin_cut((float) object.getDouble(Config.JSONConfig.T05.MIN_CUT));
                bean.setExc_cut((float) object.getDouble(Config.JSONConfig.T05.EXC_CUT));
                bean.setOver_upper_cut((float) object.getDouble(Config.JSONConfig.T05.OVER_UPPER));
                bean.setOver_down_cut((float) object.getDouble(Config.JSONConfig.T05.OVER_DOWN));
                if (map.containsKey(unit)){
                    map.get(unit).add(bean);
                }else {
                    map.put(unit,new ArrayList<T05Bean>());
                    map.get(unit).add(bean);
                }
                res = processMap(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new HttpServiceException(e.toString());
        }
        return (Serializable) res;
    }

    private List<T05Bean> processMap(Map<String,List<T05Bean>> map){
        List<T05Bean> res = new ArrayList<T05Bean>();
        for (Map.Entry<String,List<T05Bean>> entry:map.entrySet()){
            String unit = entry.getKey();
            List<T05Bean> value = entry.getValue();
            T05Bean bean = new T05Bean();
            bean.setUnit(unit);
            bean.setP_kind("综合");
            int sum = 0;
            for (T05Bean tmp:value){
                sum = sum + tmp.getMeter_counts();
            }
            bean.setMeter_counts(sum);
            float count = 0;
            float daycut = 0;
            float mincut = 0;
            float exccut = 0;
            float overdncut = 0;
            float overupcut = 0;
            for (T05Bean tmp:value){
                if (count == 0)
                    count = count + tmp.getPer_pass()/2;
                else
                    count = count + tmp.getPer_pass()/6;
                daycut = daycut + tmp.getDay_cut();
                mincut = mincut + tmp.getMin_cut();
                exccut = exccut + tmp.getExc_cut();
                overdncut = overdncut + tmp.getOver_down_cut();
                overupcut = overupcut + tmp.getOver_upper_cut();
            }
            bean.setPer_pass(count);
            bean.setDay_cut(daycut);
            bean.setMin_cut(mincut);
            bean.setExc_cut(exccut);
            bean.setOver_upper_cut(overupcut);
            bean.setOver_down_cut(overdncut);
            res.add(bean);
            res.addAll(value);
        }
        return res;
    }

    @Override
    public <T> void onResult(T t) {
        ShowT05Event event = new ShowT05Event();
        event.setList((List<T05Bean>) t);
        EventPoster.Broadcast(event);
    }

    @Override
    public void onError(Object object) {
    }
}
