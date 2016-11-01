package com.gy.just.VoltageMonitor.Model.Bean;

import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.ListDataSrc;

import java.io.Serializable;

/**
 * Created by pc on 16/5/15.
 */
@ListDataSrc(R.layout.item6)
public class T06Bean implements Serializable{
    private String meterid;
    private String unit;
    private String citykind;
    private String kind;
    private String vollevel;
    private String sumruntime;
    private String overuptime;
    private String overupper;
    private String overdntime;
    private String overdnper;
    private String passper;
    private String max;
    private String maxtime;
    private String min;
    private String mintime;
    private String avr;
    private String install;
    private String passTime;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPassTime() {
        return passTime;
    }

    public void setPassTime(String passTime) {
        this.passTime = passTime;
    }

    public String getInstall() {
        return install;
    }

    public void setInstall(String install) {
        this.install = install;
    }

    public String getMeterid() {
        return meterid;
    }

    public void setMeterid(String meterid) {
        this.meterid = meterid;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getCitykind() {
        return citykind;
    }

    public void setCitykind(String citykind) {
        this.citykind = citykind;
    }

    public String getVollevel() {
        return vollevel;
    }

    public void setVollevel(String vollevel) {
        this.vollevel = vollevel;
    }

    public String getSumruntime() {
        return sumruntime;
    }

    public void setSumruntime(String sumruntime) {
        this.sumruntime = sumruntime;
    }

    public String getOveruptime() {
        return overuptime;
    }

    public void setOveruptime(String overuptime) {
        this.overuptime = overuptime;
    }

    public String getOverupper() {
        return overupper;
    }

    public void setOverupper(String overupper) {
        this.overupper = overupper;
    }

    public String getOverdntime() {
        return overdntime;
    }

    public void setOverdntime(String overdntime) {
        this.overdntime = overdntime;
    }

    public String getOverdnper() {
        return overdnper;
    }

    public void setOverdnper(String overdnper) {
        this.overdnper = overdnper;
    }

    public String getPassper() {
        return passper;
    }

    public void setPassper(String passper) {
        this.passper = passper;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMaxtime() {
        return maxtime;
    }

    public void setMaxtime(String maxtime) {
        this.maxtime = maxtime;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMintime() {
        return mintime;
    }

    public void setMintime(String mintime) {
        this.mintime = mintime;
    }

    public String getAvr() {
        return avr;
    }

    public void setAvr(String avr) {
        this.avr = avr;
    }
}
