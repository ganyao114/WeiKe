package com.gy.just.VoltageMonitor.Model.Net;

import com.gy.just.VoltageMonitor.Control.Utils.HttpDealProxy;
import com.gy.just.VoltageMonitor.Control.Utils.TimeUtils;
import com.gy.just.VoltageMonitor.EventFlags.ShowYwResEvent;
import com.gy.just.VoltageMonitor.Model.Global.Config;
import com.gy.just.VoltageMonitor.Model.Global.Global;
import com.gy.myframework.Exception.model.net.http.HttpServiceException;
import com.gy.myframework.IOC.Model.net.http.entity.HttpConnectMode;
import com.gy.myframework.MVP.Model.BaseHttpModelBeta;
import com.gy.myframework.Model.net.http.IHttpDealCallBack;
import com.gy.myframework.Service.thread.templet.configs.HttpTheadConfigBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pc on 16/5/22.
 */
public class SendYwResModel extends BaseHttpModelBeta<String> implements IHttpDealCallBack{

    public SendYwResModel(String meterid,String type,String date,Date start,Date end,String res) {
        super();
        Map<String,String> par = new HashMap<String,String>();
        par.put(Config.Par.TOKEN_ID, Global.user.getSid());
        par.put(Config.Par.YNWEI.METER_ID,meterid);
        par.put(Config.Par.YNWEI.DATE,date);
        par.put(Config.Par.YNWEI.START, TimeUtils.getTimeStr(start));
        par.put(Config.Par.YNWEI.STOP, TimeUtils.getTimeStr(end));
        par.put(Config.Par.YNWEI.X,Global.gpsx+"");
        par.put(Config.Par.YNWEI.Y,Global.gpsy+"");
        par.put(Config.Par.YNWEI.Z,Global.gpsz+"");
        par.put(Config.Par.YNWEI.TYPE,type);
        par.put(Config.Par.YNWEI.RES,res);
        httpService.setParams(par);
    }

    @Override
    protected String setUrl() {
        return Config.Url.YW_RES();
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
        String res = "未知错误";
        try {
            JSONObject object = new JSONObject(result);
            boolean flag = object.getBoolean("success");
            if (flag){
                res = "运维信息成功发送";
            }else {
                res = "发送失败,点击重试";
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new HttpServiceException(e.toString());
        }
        return res;
    }

    @Override
    public <T> void onResult(T t) {
        ShowYwResEvent event = new ShowYwResEvent();
        event.setMsg((String) t);
        super.onResult(event);
    }
}
