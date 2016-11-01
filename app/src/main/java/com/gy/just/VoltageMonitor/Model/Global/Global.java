package com.gy.just.VoltageMonitor.Model.Global;

import com.gy.just.VoltageMonitor.Model.Bean.NotifyBean;
import com.gy.just.VoltageMonitor.Model.Bean.UserBean;

import java.util.Date;
import java.util.List;

/**
 * Created by gy on 2016/4/23.
 */
public class Global {
    public static UserBean user;
    public static List<NotifyBean> notifies;
    public static Date defaultData = new Date(new Date().getTime()-24*60*60*1000);
    public static float gpsx;
    public static float gpsy;
    public static float gpsz;
}
