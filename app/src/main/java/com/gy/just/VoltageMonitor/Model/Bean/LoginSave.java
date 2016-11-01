package com.gy.just.VoltageMonitor.Model.Bean;

import com.gy.myframework.IOC.Model.local.shareprefrence.annotation.SPColumeName;
import com.gy.myframework.IOC.Model.local.shareprefrence.annotation.SPOptions;

import java.io.Serializable;

/**
 * Created by gy on 2016/5/9.
 */
@SPOptions(name = "login")
public class LoginSave implements Serializable{
    @SPColumeName("name")
    private String username;
    @SPColumeName("passwd")
    private String passwd;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
