package com.gy.just.VoltageMonitor.Model.Bean.DetailInfo;

/**
 * Created by gy on 2016/4/28.
 */
public class DateInfo {

    private int year;

    private int month;

    private Integer[] OLDays;

    public Integer[] getOLDays() {
        return OLDays;
    }

    public void setOLDays(Integer[] OLDays) {
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