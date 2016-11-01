package com.gy.just.VoltageMonitor.Model.Bean;

import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.ListDataSrc;

/**
 * Created by pc on 16/5/23.
 */
@ListDataSrc(R.layout.item10)
public class T10Bean extends T11Bean{
    private String unit;
    private String citykind;
    private String install;
    private String kind;
    private String vollevel;
    private String meterid;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCitykind() {
        return citykind;
    }

    public void setCitykind(String citykind) {
        this.citykind = citykind;
    }

    public String getInstall() {
        return install;
    }

    public void setInstall(String install) {
        this.install = install;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getVollevel() {
        return vollevel;
    }

    public void setVollevel(String vollevel) {
        this.vollevel = vollevel;
    }

    public String getMeterid() {
        return meterid;
    }

    public void setMeterid(String meterid) {
        this.meterid = meterid;
    }
}
