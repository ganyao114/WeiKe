package com.gy.just.VoltageMonitor.Model.SharePrefrence;

import com.gy.myframework.IOC.Model.local.shareprefrence.annotation.SPColumeName;
import com.gy.myframework.IOC.Model.local.shareprefrence.annotation.SPOptions;

/**
 * Created by gy on 2016/4/13.
 */
@SPOptions(name = "AppSetting")
public class AppSetting {
    @SPColumeName("serviceUrl")
    private String serviceUrl;
    @SPColumeName("isPush")
    private boolean isPush;
    @SPColumeName("isWifi")
    private boolean isWifi;
}
