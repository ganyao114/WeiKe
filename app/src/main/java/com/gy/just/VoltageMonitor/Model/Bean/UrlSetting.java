package com.gy.just.VoltageMonitor.Model.Bean;

import com.gy.myframework.IOC.Model.local.shareprefrence.annotation.SPColumeName;
import com.gy.myframework.IOC.Model.local.shareprefrence.annotation.SPOptions;

/**
 * Created by gy939 on 2016/10/23.
 */
@SPOptions(name = "baseurl")
public class UrlSetting {

    @SPColumeName("url")
    private String baseurl = "http://122.194.21.93:18888/GDZZ";

    public String getBaseurl() {
        return baseurl;
    }

    public void setBaseurl(String baseurl) {
        this.baseurl = baseurl;
    }
}
