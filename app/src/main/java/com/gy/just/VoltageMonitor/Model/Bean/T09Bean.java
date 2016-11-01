package com.gy.just.VoltageMonitor.Model.Bean;

import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.ListDataSrc;

import java.io.Serializable;

/**
 * Created by pc on 16/5/23.
 */
@ListDataSrc(R.layout.item9)
public class T09Bean implements Serializable{
    private String daycut;
    private String mincut;
    private String exccut;
    private String overupcut;
    private String overdncut;
    private String sumcut;
    private String countper;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDaycut() {
        return daycut;
    }

    public void setDaycut(String daycut) {
        this.daycut = daycut;
    }

    public String getMincut() {
        return mincut;
    }

    public void setMincut(String mincut) {
        this.mincut = mincut;
    }

    public String getOverupcut() {
        return overupcut;
    }

    public void setOverupcut(String overupcut) {
        this.overupcut = overupcut;
    }

    public String getExccut() {
        return exccut;
    }

    public void setExccut(String exccut) {
        this.exccut = exccut;
    }

    public String getOverdncut() {
        return overdncut;
    }

    public void setOverdncut(String overdncut) {
        this.overdncut = overdncut;
    }

    public String getSumcut() {
        return sumcut;
    }

    public void setSumcut(String sumcut) {
        this.sumcut = sumcut;
    }

    public String getCountper() {
        return countper;
    }

    public void setCountper(String countper) {
        this.countper = countper;
    }
}
