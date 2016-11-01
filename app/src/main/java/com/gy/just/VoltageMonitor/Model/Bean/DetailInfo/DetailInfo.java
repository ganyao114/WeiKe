package com.gy.just.VoltageMonitor.Model.Bean.DetailInfo;

import java.io.Serializable;

/**
 * Created by gy on 2016/4/28.
 */
public class DetailInfo implements Serializable{
    private Date date;
    private boolean isOnline;
    private int dataSum;
    private int minites_data;
    private int mindate_minlimite;
    private int mindate_maxlimite;
    private boolean[] exceptions = new boolean[9];
    private float Sum;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public int getDataSum() {
        return dataSum;
    }

    public void setDataSum(int dataSum) {
        this.dataSum = dataSum;
    }

    public int getMinites_data() {
        return minites_data;
    }

    public void setMinites_data(int minites_data) {
        this.minites_data = minites_data;
    }

    public int getMindate_minlimite() {
        return mindate_minlimite;
    }

    public void setMindate_minlimite(int mindate_minlimite) {
        this.mindate_minlimite = mindate_minlimite;
    }

    public int getMindate_maxlimite() {
        return mindate_maxlimite;
    }

    public void setMindate_maxlimite(int mindate_maxlimite) {
        this.mindate_maxlimite = mindate_maxlimite;
    }

    public boolean[] getExceptions() {
        return exceptions;
    }

    public void setExceptions(boolean[] exceptions) {
        this.exceptions = exceptions;
    }

    public float getSum() {
        return Sum;
    }

    public void setSum(float sum) {
        Sum = sum;
    }

}
