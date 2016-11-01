package com.gy.just.VoltageMonitor.Model.Net;

import android.util.Log;

import com.gy.just.VoltageMonitor.Control.Utils.HttpDealProxy;
import com.gy.just.VoltageMonitor.Control.Utils.MathUtils;
import com.gy.just.VoltageMonitor.Control.Utils.TimeUtils;
import com.gy.just.VoltageMonitor.EventFlags.ShowMainList2Event;
import com.gy.just.VoltageMonitor.Model.Bean.CardPojo.MainTabCard2Pojo;
import com.gy.just.VoltageMonitor.Model.Bean.DataMaintan;
import com.gy.just.VoltageMonitor.Model.Bean.ShowMaintanData;
import com.gy.just.VoltageMonitor.Model.Global.Config;
import com.gy.just.VoltageMonitor.Model.Global.Global;
import com.gy.just.VoltageMonitor.View.Interface.IShowList;
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
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gy on 2016/4/24.
 */
public class GetMaintanDataModel extends BaseHttpModelBeta<List<DataMaintan>> implements IHttpDealCallBack{

    private IShowList view;

    public GetMaintanDataModel(String type, String date, IShowList view) {
        super();
        this.view = view;
        Map<String,String> par = new HashMap<String,String>();
        par.put(Config.Par.TOKEN_ID,Global.user.getSid());
        par.put("type", type);
        par.put("date",date);
        httpService.setParams(par);
    }

    @Override
    protected String setUrl() {
        return Config.Url.MAINTAN_DATA();
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
        Log.e("maintan",result);
        List<DataMaintan> res = new ArrayList<DataMaintan>();
        try {
            JSONObject object = new JSONObject(result);
            JSONArray array = object.getJSONArray(Config.JSONConfig.mainList.LIST_KEY);
            if (array == null||array.length()==0)
                throw new HttpServiceException("空列表");
            for (int i = 0;i <array.length();i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                DataMaintan dataMaintan = new DataMaintan();
                dataMaintan.setName(jsonObject.getString("yb_address"));
                dataMaintan.setStatue(jsonObject.getBoolean("status"));
                res.add(dataMaintan);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            throw new HttpServiceException(e.toString());
        }

        return (Serializable) res;
    }

    @Override
    public <T> void onResult(T t) {
        ShowMaintanData showMaintanData = new ShowMaintanData();
        showMaintanData.setList((List<DataMaintan>) t);
        view.refresh(showMaintanData);
    }

    @Override
    public void onError(Object object) {

    }
}
