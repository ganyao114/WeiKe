package com.gy.just.VoltageMonitor.Model.Bean;

import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.BindText;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.ListDataSrc;

import java.io.Serializable;

/**
 * Created by gy on 2016/4/23.
 */
@ListDataSrc(R.layout.layout_item_dialsinfo)
public class DailinfoPojo implements Serializable{

    @BindText(R.id.dialinf_txt1)
    private String time;
    @BindText(R.id.dialinf_txt2)
    private String vol;
    @BindText(R.id.dialinf_txt3)
    private String isoverup = "";
    @BindText(R.id.dialinf_txt4)
    private String isoverdn = "";
    @BindText(R.id.dialinf_txt5)
    private String cut = "";

    public DailinfoPojo() {
    }

    public String getCut() {
        return cut;
    }

    public void setCut(String cut) {
        this.cut = cut;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public String getIsoverdn() {
        return isoverdn;
    }

    public void setIsoverdn(String isoverdn) {
        this.isoverdn = isoverdn;
    }

    public String getIsoverup() {
        return isoverup;
    }

    public void setIsoverup(String isoverup) {
        this.isoverup = isoverup;
    }
}
