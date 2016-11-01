package com.gy.just.VoltageMonitor.Model.Bean.DetailInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gy on 2016/4/28.
 */
public class DateOlInfo implements Serializable{

    private int year;

    private int month;

    private List<Integer> OLDays;

    public List<Integer> getOLDays() {
        return OLDays;
    }

    public void setOLDays(List<Integer> OLDays) {
        this.OLDays = OLDays;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}