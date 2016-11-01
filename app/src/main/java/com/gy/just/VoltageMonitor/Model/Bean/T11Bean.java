package com.gy.just.VoltageMonitor.Model.Bean;

import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.ListDataSrc;

import java.io.Serializable;

/**
 * Created by pc on 16/5/23.
 */
@ListDataSrc(R.layout.item11)
public class T11Bean implements Serializable{
    private String overup1_cut;
    private String overup2_cut;
    private String overup3_cut;
    private String overup4_cut;
    private String overupsum_cut;
    private String overdn1_cut;
    private String overdn2_cut;
    private String overdn3_cut;
    private String overdn4_cut;
    private String overdnsum_cut;
    private String cut_sum;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOverup1_cut() {
        return overup1_cut;
    }

    public void setOverup1_cut(String overup1_cut) {
        this.overup1_cut = overup1_cut;
    }

    public String getOverup2_cut() {
        return overup2_cut;
    }

    public void setOverup2_cut(String overup2_cut) {
        this.overup2_cut = overup2_cut;
    }

    public String getOverup3_cut() {
        return overup3_cut;
    }

    public void setOverup3_cut(String overup3_cut) {
        this.overup3_cut = overup3_cut;
    }

    public String getOverup4_cut() {
        return overup4_cut;
    }

    public void setOverup4_cut(String overup4_cut) {
        this.overup4_cut = overup4_cut;
    }

    public String getOverupsum_cut() {
        return overupsum_cut;
    }

    public void setOverupsum_cut(String overupsum_cut) {
        this.overupsum_cut = overupsum_cut;
    }

    public String getOverdn1_cut() {
        return overdn1_cut;
    }

    public void setOverdn1_cut(String overdn1_cut) {
        this.overdn1_cut = overdn1_cut;
    }

    public String getOverdn2_cut() {
        return overdn2_cut;
    }

    public void setOverdn2_cut(String overdn2_cut) {
        this.overdn2_cut = overdn2_cut;
    }

    public String getOverdn3_cut() {
        return overdn3_cut;
    }

    public void setOverdn3_cut(String overdn3_cut) {
        this.overdn3_cut = overdn3_cut;
    }

    public String getOverdn4_cut() {
        return overdn4_cut;
    }

    public void setOverdn4_cut(String overdn4_cut) {
        this.overdn4_cut = overdn4_cut;
    }

    public String getOverdnsum_cut() {
        return overdnsum_cut;
    }

    public void setOverdnsum_cut(String overdnsum_cut) {
        this.overdnsum_cut = overdnsum_cut;
    }

    public String getCut_sum() {
        return cut_sum;
    }

    public void setCut_sum(String cut_sum) {
        this.cut_sum = cut_sum;
    }
}
