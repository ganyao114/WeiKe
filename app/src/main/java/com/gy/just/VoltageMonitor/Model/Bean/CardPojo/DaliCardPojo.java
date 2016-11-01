package com.gy.just.VoltageMonitor.Model.Bean.CardPojo;

import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.BindProgress;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.BindProgressMax;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.BindText;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.ListDataSrc;

import java.io.Serializable;

/**
 * Created by gy on 2016/4/24.
 */
@ListDataSrc(R.layout.list_item_card_detail)
public class DaliCardPojo implements Serializable{
    @BindText(R.id.card_info_title)
    private String unit;
    private String coverurl;
    @BindProgressMax(R.id.card_progressbar1)
    private Integer max = 100;
    @BindText(R.id.card_progress_txt1)
    private String per1_txt;
    @BindProgress(R.id.card_progressbar1)
    private Integer per1;
    @BindText(R.id.card_progress_txt2)
    private String pre2_txt;
    @BindProgress(R.id.card_progressbar2)
    private Integer pre2;
    @BindText(R.id.card_progress_txt3)
    private String pre3_txt;
    @BindProgress(R.id.card_progressbar3)
    private Integer pre3;
    @BindText(R.id.card_pre_title1)
    private String per_title1;
    @BindText(R.id.card_pre_title2)
    private String per_title2;
    @BindText(R.id.card_pre_title3)
    private String per_title3;

    public String getCoverurl() {
        return coverurl;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
    }

    public String getPer1_txt() {
        return per1_txt;
    }

    public void setPer1_txt(String per1_txt) {
        this.per1_txt = "上线率:"+per1_txt;
    }

    public Integer getPer1() {
        return per1;
    }

    public void setPer1(Integer per1) {
        this.per1 = per1;
    }

    public String getPre2_txt() {
        return pre2_txt;
    }

    public void setPre2_txt(String pre2_txt) {
        this.pre2_txt = "上线率:"+pre2_txt;
    }

    public Integer getPre3() {
        return pre3;
    }

    public void setPre3(Integer pre3) {
        this.pre3 = pre3;
    }

    public Integer getPre2() {
        return pre2;
    }

    public void setPre2(Integer pre2) {
        this.pre2 = pre2;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    public String getPre3_txt() {
        return pre3_txt;
    }

    public void setPre3_txt(String pre3_txt) {
        this.pre3_txt = "异常率:"+pre3_txt;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public String getPer_title1() {
        return per_title1;
    }

    public void setPer_title1(String per_title1) {
        this.per_title1 = "日数据:"+per_title1+"条";
    }

    public String getPer_title2() {
        return per_title2;
    }

    public void setPer_title2(String per_title2) {
        this.per_title2 = "分数据:"+per_title2+"条";
    }

    public String getPer_title3() {
        return per_title3;
    }

    public void setPer_title3(String per_title3) {
        this.per_title3 = "异常:"+per_title3+"条";
    }
}
