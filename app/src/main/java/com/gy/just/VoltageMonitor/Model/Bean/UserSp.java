package com.gy.just.VoltageMonitor.Model.Bean;

import com.gy.myframework.IOC.Model.local.shareprefrence.annotation.SPColumeName;
import com.gy.myframework.IOC.Model.local.shareprefrence.annotation.SPOptions;

/**
 * Created by gy on 2016/5/4.
 */
@SPOptions(name = "user")
public class UserSp {

    @SPColumeName("name")
    private String username;
    @SPColumeName("sid")
    private String sid;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
