package com.gy.just.VoltageMonitor.Model.Net;

import android.util.Log;

import com.gy.just.VoltageMonitor.Control.Utils.HttpDealProxy;
import com.gy.just.VoltageMonitor.EventFlags.LoginErrorEvent;
import com.gy.just.VoltageMonitor.EventFlags.LoginedEvent;
import com.gy.just.VoltageMonitor.Model.Bean.UserBean;
import com.gy.just.VoltageMonitor.Model.Global.Config;
import com.gy.just.VoltageMonitor.Model.Global.Global;
import com.gy.myframework.Exception.model.net.http.HttpServiceException;
import com.gy.myframework.IOC.Model.net.http.entity.HttpConnectMode;
import com.gy.myframework.IOC.Service.event.impl.EventPoster;
import com.gy.myframework.MVP.Model.BaseHttpModelBeta;
import com.gy.myframework.Model.net.http.IHttpDealCallBack;
import com.gy.myframework.Service.thread.templet.configs.HttpTheadConfigBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by gy on 2016/4/23.
 */
public class LoginModel extends BaseHttpModelBeta<UserBean> implements ILoginModel,IHttpDealCallBack{
    public LoginModel() {
        super();
    }

    @Override
    public Serializable dealReturn(String result) throws HttpServiceException {
        UserBean res = null;
        try {
            JSONObject object = new JSONObject(result);
            boolean state = object.getBoolean("success");
            if (state){
                res = new UserBean();
                res.setLoginname(object.getString(Config.JSONConfig.loginRes.LOGIN_NAME));
                res.setEmail(object.getString(Config.JSONConfig.loginRes.EMAIL));
                res.setName(object.getString(Config.JSONConfig.loginRes.NAME));
                res.setTel(object.getString(Config.JSONConfig.loginRes.TEL_NO));
                res.setOname(object.getString(Config.JSONConfig.loginRes.O_NAME));
                res.setSid(object.getString(Config.JSONConfig.loginRes.SID));
            }else {
                String msg = object.getString("msg");
                throw new HttpServiceException(msg);
            }
        } catch (JSONException e) {
            throw new HttpServiceException("JSON格式错误");
        }
        return res;
    }

    @Override
    public <UserBean> void onResult(UserBean userBean) {
        Global.user = (com.gy.just.VoltageMonitor.Model.Bean.UserBean) userBean;
        EventPoster.Broadcast(new LoginedEvent());
    }

    @Override
    public void onError(Object object) {
        EventPoster.Broadcast(new LoginErrorEvent((String) object));
    }

    @Override
    protected String setUrl() {
        return Config.Url.LOGIN_URL();
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
        return new HttpTheadConfigBean(false,0,"链接超时","登陆错误","未知错误");
    }

    @Override
    public void login(UserBean user) {
        Map<String,String> par = new HashMap<String, String>();
        par.put(Config.JSONConfig.loginRes.LOGIN_PAR_NAME,user.getLoginname());
        par.put(Config.JSONConfig.loginRes.LOGIN_PAR_PASS,user.getPass());
        httpService.setParams(par);
        doHttp();
    }
}
