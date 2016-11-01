package com.gy.just.VoltageMonitor.Control.Application;

import android.app.Application;
import android.text.TextUtils;

import com.gy.just.VoltageMonitor.Model.Bean.UrlSetting;
import com.gy.just.VoltageMonitor.Model.Global.Config;
import com.gy.myframework.IOC.Core.impl.IOC;
import com.gy.myframework.IOC.Model.local.shareprefrence.impl.SharePrefrenceUtils;

import org.xutils.x;

/**
 * Created by gy on 2016/4/11.
 */
public class MyAppliction extends Application{
    public static String username;
    @Override
    public void onCreate() {
        super.onCreate();
        IOC.getInstance().init(this);
        x.Ext.init(this);
        UrlSetting setting = new UrlSetting();
        SharePrefrenceUtils.Get(setting);
        if (!TextUtils.isEmpty(setting.getBaseurl()))
            Config.Url.BASE = setting.getBaseurl();
    }
}
