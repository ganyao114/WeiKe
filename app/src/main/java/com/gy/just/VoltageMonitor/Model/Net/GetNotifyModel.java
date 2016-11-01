package com.gy.just.VoltageMonitor.Model.Net;

import android.util.Log;

import com.gy.just.VoltageMonitor.Control.Utils.HttpDealProxy;
import com.gy.just.VoltageMonitor.EventFlags.HasNotifyEvent;
import com.gy.just.VoltageMonitor.Model.Bean.DB.NotifyDB;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gy on 2016/4/25.
 */
public class GetNotifyModel extends BaseHttpModelBeta<ArrayList<NotifyDB>> implements IHttpDealCallBack{

    private String Loginname;

    public GetNotifyModel(String username) {
        super();
        Loginname = new String(username);
        Map<String,String> par = new HashMap<String,String>();
        par.put(Config.Par.TOKEN_ID, Loginname);
        httpService.setParams(par);
    }

    @Override
    protected String setUrl() {
        return Config.Url.GET_NOTIFY();
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
        return new HttpTheadConfigBean(true,60*1000,"","","");
    }

    @Override
    public Serializable dealReturn(String result) throws HttpServiceException {
        String restmp = "{data:[{\"m_ybID\":\"0013647245 \",\"m_time\":\"201605252335\",\"m_voltage\":260.46,\"m_type\":1,\"m_Score\":0.4,\"shift\":4,\"RN\":2},{\"m_ybID\":\"0013647245 \",\"m_time\":\"201605252340\",\"m_voltage\":259.78,\"m_type\":1,\"m_Score\":0.3,\"shift\":3,\"RN\":3},{\"m_ybID\":\"0013647245 \",\"m_time\":\"201605252345\",\"m_voltage\":259.88,\"m_type\":1,\"m_Score\":0.3,\"shift\":3,\"RN\":4},{\"m_ybID\":\"0013647245 \",\"m_time\":\"201605252350\",\"m_voltage\":260.27,\"m_type\":1,\"m_Score\":0.4,\"shift\":4,\"RN\":5},{\"m_ybID\":\"0013647245 \",\"m_time\":\"201605252355\",\"m_voltage\":259.44,\"m_type\":1,\"m_Score\":0.3,\"shift\":3,\"RN\":6},{\"m_ybID\":\"0013647245 \",\"m_time\":\"201605260000\",\"m_voltage\":259.01,\"m_type\":1,\"m_Score\":0.3,\"shift\":3,\"RN\":7},{\"m_ybID\":\"0013647245 \",\"m_time\":\"201605260005\",\"m_voltage\":259.28,\"m_type\":1,\"m_Score\":0.3,\"shift\":3,\"RN\":8},{\"m_ybID\":\"0013647245 \",\"m_time\":\"201605260010\",\"m_voltage\":258.64,\"m_type\":1,\"m_Score\":0.3,\"shift\":3,\"RN\":9},{\"m_ybID\":\"0013647245 \",\"m_time\":\"201605260015\",\"m_voltage\":259.46,\"m_type\":1,\"m_Score\":0.3,\"shift\":3,\"RN\":10},{\"m_ybID\":\"0013647245 \",\"m_time\":\"201605260020\",\"m_voltage\":258.25,\"m_type\":1,\"m_Score\":0.3,\"shift\":3,\"RN\":11},{\"m_ybID\":\"0013647245 \",\"m_time\":\"201605260025\",\"m_voltage\":258.92,\"m_type\":1,\"m_Score\":0.3,\"shift\":3,\"RN\":12},{\"m_ybID\":\"0013647245 \",\"m_time\":\"201605260030\",\"m_voltage\":258.59,\"m_type\":1,\"m_Score\":0.3,\"shift\":3,\"RN\":13},{\"m_ybID\":\"0013647245 \",\"m_time\":\"201605260035\",\"m_voltage\":258.49,\"m_type\":1,\"m_Score\":0.3,\"shift\":3,\"RN\":14},{\"m_ybID\":\"0013647245 \",\"m_time\":\"201605260040\",\"m_voltage\":259.04,\"m_type\":1,\"m_Score\":0.3,\"shift\":3,\"RN\":15},{\"m_ybID\":\"0013647245 \",\"m_time\":\"201605260045\",\"m_voltage\":258.49,\"m_type\":1,\"m_Score\":0.3,\"shift\":3,\"RN\":16},{\"m_ybID\":\"0013647245 \",\"m_time\":\"201605260050\",\"m_voltage\":258.51,\"m_type\":1,\"m_Score\":0.3,\"shift\":3,\"RN\":17},{\"m_ybID\":\"0013647245 \",\"m_time\":\"201605260055\",\"m_voltage\":258.47,\"m_type\":1,\"m_Score\":0.3,\"shift\":3,\"RN\":18}],meta:{success:true,msg:'',total:17}}";
        List<NotifyDB> list = new ArrayList<NotifyDB>();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray array = jsonObject.getJSONArray(Config.JSONConfig.mainList.LIST_KEY);
            int len = array.length();
            if (array == null||len==0)
                throw new HttpServiceException("空列表");
            for (int i = 0;i < len;i ++) {
                JSONObject object = array.getJSONObject(i);
                NotifyDB bean = new NotifyDB();
                String meterid = object.getString(Config.JSONConfig.NOTIFY.METER_ID);
                bean.setMeterid(meterid);
                int type = object.getInt(Config.JSONConfig.NOTIFY.EXC_TYPE);
                String typeStr;
                float vol = (float) object.getDouble(Config.JSONConfig.NOTIFY.VOL);
                String timeStr = object.getString(Config.JSONConfig.NOTIFY.TIME);
                bean.setTime(timeStr);
                if (type == -1){
                    typeStr = "超下限";
                }else {
                    typeStr = "超上限";
                }
                bean.setTitle("表"+meterid+typeStr);
                bean.setContent("时间:"+bean.getTime()+"\n"+"表:"+meterid+"电压为:"+vol+","+typeStr+",请及时检查!");
                list.add(bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new HttpServiceException(e.toString());
        }
        return (Serializable) list;
    }

    @Override
    public <T> void onResult(T t) {
        HasNotifyEvent event = new HasNotifyEvent();
        event.setList((List<NotifyDB>) t);
        EventPoster.Broadcast(event);
    }

    @Override
    public void onError(Object object) {
        Log.e("gy", (String) object);
    }
}
