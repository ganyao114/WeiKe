package com.gy.just.VoltageMonitor.Model.Net;

import com.gy.just.VoltageMonitor.Control.Utils.HttpDealProxy;
import com.gy.just.VoltageMonitor.EventFlags.ShowYwKindEvent;
import com.gy.just.VoltageMonitor.Model.Bean.YunWeiKindBean;
import com.gy.just.VoltageMonitor.Model.Global.Config;
import com.gy.just.VoltageMonitor.Model.Global.Global;
import com.gy.myframework.Exception.model.net.http.HttpServiceException;
import com.gy.myframework.IOC.Model.net.http.entity.HttpConnectMode;
import com.gy.myframework.MVP.Model.BaseHttpModel;
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
 * Created by pc on 16/5/20.
 */
public class GetYwKindModel extends BaseHttpModelBeta<List<YunWeiKindBean>> implements IHttpDealCallBack {
    public GetYwKindModel() {
        super();
        Map<String,String> par = new HashMap<String,String>();
        par.put(Config.Par.TOKEN_ID,Global.user.getSid());
        par.put("code","300");
        httpService.setParams(par);
    }

    @Override
    protected String setUrl() {
        return Config.Url.BASE + "/servlet/mobile/common/MComboItemLoad";
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
        List<YunWeiKindBean> res = new ArrayList<YunWeiKindBean>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
            JSONArray array = jsonObject.getJSONArray(Config.JSONConfig.mainList.LIST_KEY);
            int len = array.length();
            if (array == null||len==0)
                throw new HttpServiceException("空列表");
            for (int i = 0;i <len;i++) {
                YunWeiKindBean bean = new YunWeiKindBean();
                JSONObject object = array.getJSONObject(i);
                bean.setName(object.getString("name"));
                bean.setCode(object.getString("code"));
                res.add(bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new HttpServiceException(e.toString());
        }
        return (Serializable) res;
    }

    @Override
    public <T> void onResult(T t) {
        ShowYwKindEvent event = new ShowYwKindEvent();
        event.setList((List<YunWeiKindBean>) t);
        super.onResult(event);
    }

    @Override
    public void onError(Object object) {
    }
}
