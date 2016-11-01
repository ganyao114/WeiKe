package com.gy.just.VoltageMonitor.Model.Net;

import android.util.Log;

import com.gy.just.VoltageMonitor.Control.Utils.HttpDealProxy;
import com.gy.just.VoltageMonitor.EventFlags.ShowT02Event;
import com.gy.just.VoltageMonitor.Model.Bean.LarTabBean.T02Bean;
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
public class GetT02Model extends BaseHttpModelBeta<List<T02Bean>> implements IHttpDealCallBack{


    public GetT02Model(String datestr,String pt_id,String pgstart,String lmt) {
        super();
        Map<String,String> par = new HashMap<String,String>();
        par.put(Config.Par.TOKEN_ID,Global.user.getSid());
        par.put(Config.Par.T02.DATE, datestr);
        par.put(Config.Par.T02.START,pgstart);
        par.put(Config.Par.T02.LIMIT, lmt);
        if (pt_id!="0"){
            par.put("pointType",pt_id);
        }
        httpService.setParams(par);
    }

    @Override
    protected String setUrl() {
        return Config.Url.GET_T02();
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
        List<T02Bean> list = new ArrayList<T02Bean>();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray array = jsonObject.getJSONArray(Config.JSONConfig.T02.DATA);
            if (array == null||array.length() == 0)
                throw new HttpServiceException("数据异常");
            for (int i = 0;i < array.length();i ++){
                T02Bean bean = new T02Bean();
                Log.e("gy", "1");
                JSONObject object = array.getJSONObject(i);
                bean.setMeterid(object.getString(Config.JSONConfig.T02.METER_ID));
                bean.setMeterkind(object.getString(Config.JSONConfig.T02.KIND));
                bean.setInstalllocal(object.getString(Config.JSONConfig.T02.INSTALL));
                bean.setOwner(object.getString(Config.JSONConfig.T02.OWNER));
                int ol = object.getInt(Config.JSONConfig.T02.ISOL);
                if (ol == 1)
                    bean.setOnline(true);
                else
                    bean.setOnline(false);
                bean.setMin_datas(object.getInt(Config.JSONConfig.T02.MIN_DATAS));
                bean.setSum((float) object.getDouble(Config.JSONConfig.T02.SUM));
                bean.setOver_upper(object.getInt(Config.JSONConfig.T02.CSX));
                bean.setOver_doenner(object.getInt(Config.JSONConfig.T02.CXX));
                boolean[] isExceptions = new boolean[9];
                for (int j = 1;j <= 9;j++){
                    if (object.getInt(Config.JSONConfig.T02.EXC_BASE + j)==1){
                        isExceptions[j-1] = true;
                    }else {
                        isExceptions[j-1] = false;
                    }
                }
                Log.e("gy", "2");
                bean.setExceptions(isExceptions);
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
        ShowT02Event event = new ShowT02Event();
        event.setList((List<T02Bean>) t);
//        activity.setTab02(event);
        EventPoster.Broadcast(event);
    }

    @Override
    public void onError(Object object) {
        Log.e("gy", (String) object);
    }
}
