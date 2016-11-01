package com.gy.just.VoltageMonitor.Model.Bean;

import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.BindText;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.ListDataSrc;

import java.io.Serializable;

/**
 * Created by pc on 16/5/16.
 */
@ListDataSrc(R.layout.item_olstatue_list)
public class OnlineStatueBean implements Serializable{
    @BindText(R.id.olstate_username)
    private String username;
    @BindText(R.id.olstate_unit)
    private String unit;
    @BindText(R.id.olstate_sum)
    private String sum;
    @BindText(R.id.olstate_app)
    private String app;
    @BindText(R.id.olstate_web)
    private String web;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
