package com.gy.just.VoltageMonitor.Model.Bean.TabViewBean;

import java.io.Serializable;

/**
 * Created by gy on 2016/4/25.
 */
public class TabValueBean implements Serializable{
    private String title;
    private String tipX;
    private String tipY;
    private int[] value;
    private String[] valueTips;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTipX() {
        return tipX;
    }

    public void setTipX(String tipX) {
        this.tipX = tipX;
    }

    public String getTipY() {
        return tipY;
    }

    public void setTipY(String tipY) {
        this.tipY = tipY;
    }

    public int[] getValue() {
        return value;
    }

    public void setValue(int[] value) {
        this.value = value;
    }

    public String[] getValueTips() {
        return valueTips;
    }

    public void setValueTips(String[] valueTips) {
        this.valueTips = valueTips;
    }
}
