package com.gy.just.VoltageMonitor.Model.Net;

import com.gy.just.VoltageMonitor.Control.Utils.HttpDealProxy;
import com.gy.just.VoltageMonitor.Model.Bean.DetailInfo.DetailInfo;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gy on 2016/4/28.
 */
public class GetDetailInfoModel extends BaseHttpModelBeta<DetailInfo> implements IHttpDealCallBack{

    public GetDetailInfoModel(String meter_id,String time) {
        super();
        Map<String,String> par = new HashMap<String,String>();
        par.put(Config.Par.DETAIL.METER_ID,meter_id);
        par.put(Config.Par.DETAIL.TIME,time);
        par.put(Config.Par.TOKEN_ID,Global.user.getSid());
        httpService.setParams(par);
    }

    @Override
    public <T> void onResult(T t) {
        super.onResult(t);
    }

    @Override
    public void onError(Object object) {
    }

    @Override
    protected String setUrl() {
        return Config.Url.DETAIL_LIST();
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
        DetailInfo res = new DetailInfo();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray array = jsonObject.getJSONArray(Config.JSONConfig.DetailT3.DATA);
            if (array==null||array.length()==0)
                throw new JSONException("返回错误");
            JSONObject object = array.getJSONObject(0);
            int ol = object.getInt(Config.JSONConfig.DetailT3.ISOL);
            if (ol == 1)
                res.setOnline(true);
            else
                res.setOnline(false);
            res.setDataSum(288);
            res.setSum((float) object.getDouble(Config.JSONConfig.DetailT3.SUM));
            res.setMinites_data(object.getInt(Config.JSONConfig.DetailT3.MIN_DATAS));
            res.setMindate_minlimite(object.getInt(Config.JSONConfig.DetailT3.CXX));
            res.setMindate_maxlimite(object.getInt(Config.JSONConfig.DetailT3.CSX));
            boolean[] isExceptions = new boolean[9];
            for (int j = 1;j <= 9;j++){
                if (object.getInt(Config.JSONConfig.DetailT3.EXC_BASE + j)==1){
                    isExceptions[j-1] = true;
                }else {
                    isExceptions[j-1] = false;
                }
            }
            res.setExceptions(isExceptions);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return (Serializable) res;
    }
}
