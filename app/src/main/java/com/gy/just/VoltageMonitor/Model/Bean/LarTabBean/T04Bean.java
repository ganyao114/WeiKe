package com.gy.just.VoltageMonitor.Model.Bean.LarTabBean;

/**
 * Created by gy on 2016/4/30.
 */

import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.ListDataSrc;

import java.io.Serializable;

@ListDataSrc(R.layout.item_month_tab_layout)
public class T04Bean implements Serializable{
    private String meterid;
    private String install;
    private String kind;
    private String owner;
    private boolean[] isol;

    public String getMeterid() {
        return meterid;
    }

    public void setMeterid(String meterid) {
        this.meterid = meterid;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean[] getIsol() {
        return isol;
    }

    public void setIsol(boolean[] isol) {
        this.isol = isol;
    }
}
