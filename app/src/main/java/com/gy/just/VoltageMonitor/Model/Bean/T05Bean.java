package com.gy.just.VoltageMonitor.Model.Bean;

import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.ListDataSrc;

import java.io.Serializable;

/**
 * Created by gy on 2016/5/3.
 */
@ListDataSrc(R.layout.item3)
public class T05Bean implements Serializable{

    private String unit;
    private String p_kind;
    private float per_pass;
    private String per_pass_str;
    private int meter_counts;
    private float day_cut;
    private float min_cut;
    private float exc_cut;
    private float over_upper_cut;
    private float over_down_cut;
    private String count_rate;
    private float sum;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getP_kind() {
        return p_kind;
    }

    public void setP_kind(String p_kind) {
        this.p_kind = p_kind;
    }

    public float getPer_pass() {
        return per_pass;
    }

    public void setPer_pass(float per_pass) {
        this.per_pass = per_pass;
        this.per_pass_str = per_pass+"%";
    }

    public String getPer_pass_str() {
        return per_pass_str;
    }

    public void setPer_pass_str(String per_pass_str) {
        this.per_pass_str = per_pass_str;
    }

    public int getMeter_counts() {
        return meter_counts;
    }

    public void setMeter_counts(int meter_counts) {
        this.meter_counts = meter_counts;
    }

    public float getDay_cut() {
        return day_cut;
    }

    public void setDay_cut(float day_cut) {
        this.day_cut = day_cut;
    }

    public float getMin_cut() {
        return min_cut;
    }

    public void setMin_cut(float min_cut) {
        this.min_cut = min_cut;
    }

    public float getExc_cut() {
        return exc_cut;
    }

    public void setExc_cut(float exc_cut) {
        this.exc_cut = exc_cut;
    }

    public float getOver_upper_cut() {
        return over_upper_cut;
    }

    public void setOver_upper_cut(float over_upper_cut) {
        this.over_upper_cut = over_upper_cut;
    }

    public float getOver_down_cut() {
        return over_down_cut;
    }

    public void setOver_down_cut(float over_down_cut) {
        this.over_down_cut = over_down_cut;
    }

    public String getCount_rate() {
        return count_rate;
    }

    public void setCount_rate(String count_rate) {
        this.count_rate = count_rate;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }
}
