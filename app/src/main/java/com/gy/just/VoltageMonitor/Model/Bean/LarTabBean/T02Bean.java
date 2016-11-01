package com.gy.just.VoltageMonitor.Model.Bean.LarTabBean;

import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.ListDataSrc;

import java.io.Serializable;

/**
 * Created by gy on 2016/4/30.
 */
@ListDataSrc(R.layout.item)
public class T02Bean implements Serializable{
    private String meterid;
    private String meterkind;
    private String installlocal;
    private String owner;
    private boolean isOnline;
    private int min_datas;
    private boolean[] exceptions;
    private int over_upper;
    private int over_doenner;
    private float sum;

    public String getMeterid() {
        return meterid;
    }

    public void setMeterid(String meterid) {
        this.meterid = meterid;
    }

    public String getMeterkind() {
        return meterkind;
    }

    public void setMeterkind(String meterkind) {
        this.meterkind = meterkind;
    }

    public String getInstalllocal() {
        return installlocal;
    }

    public void setInstalllocal(String installlocal) {
        this.installlocal = installlocal;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public int getMin_datas() {
        return min_datas;
    }

    public void setMin_datas(int min_datas) {
        this.min_datas = min_datas;
    }

    public boolean[] getExceptions() {
        return exceptions;
    }

    public void setExceptions(boolean[] exceptions) {
        this.exceptions = exceptions;
    }

    public int getOver_upper() {
        return over_upper;
    }

    public void setOver_upper(int over_upper) {
        this.over_upper = over_upper;
    }

    public int getOver_doenner() {
        return over_doenner;
    }

    public void setOver_doenner(int over_doenner) {
        this.over_doenner = over_doenner;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
