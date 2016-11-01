package com.gy.just.VoltageMonitor.Model.Bean.CardPojo;

import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.BindProgress;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.BindText;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.ListDataSrc;

import java.io.Serializable;

/**
 * Created by gy on 2016/4/27.
 */
@ListDataSrc(R.layout.list_item_card_detail)
public class MainTabCardPojo implements Serializable{
    @BindText(R.id.card_info_title)
    private String meterkind;
    private String coverurl;
    private String ptid;
    private int metersum;
    private int daycount;
    private int mincount;
    private int exceptions;
    @BindText(R.id.card_info_title_sub)
    private String sumstr;
    @BindProgress(R.id.card_progressbar1)
    private int day_progress;
    @BindProgress(R.id.card_progressbar2)
    private int min_progress;
    @BindProgress(R.id.card_progressbar3)
    private int exception_progress;
    @BindText(R.id.card_pre_title1)
    private String day_text;
    @BindText(R.id.card_pre_title2)
    private String min_text;
    @BindText(R.id.card_pre_title3)
    private String exception_text;
    @BindText(R.id.card_progress_txt1)
    private String day_per;
    @BindText(R.id.card_progress_txt2)
    private String min_per;
    @BindText(R.id.card_progress_txt3)
    private String exception_per;

    public String getMeterkind() {
        return meterkind;
    }

    public void setMeterkind(String meterkind) {
        this.meterkind = meterkind;
    }

    public String getCoverurl() {
        return coverurl;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
    }

    public int getMetersum() {
        return metersum;
    }

    public void setMetersum(int metersum) {
        this.metersum = metersum;
        sumstr = "共计:" + metersum + "台";
    }

    public int getDaycount() {
        return daycount;
    }

    public void setDaycount(int daycount) {
        this.daycount = daycount;
        day_text = "日数据:" + daycount + "条";
    }

    public int getMincount() {
        return mincount;
    }

    public void setMincount(int mincount) {
        this.mincount = mincount;
        min_text = "分数据:" + mincount + "条";
    }

    public int getExceptions() {
        return exceptions;
    }

    public void setExceptions(int exceptions) {
        this.exceptions = exceptions;
        exception_text = "异常数据:" + exceptions + "条";
    }

    public String getPtid() {
        return ptid;
    }

    public void setPtid(String ptid) {
        this.ptid = ptid;
    }

    public String getException_per() {
        return exception_per;
    }

    public void setException_per(String exception_per) {
        this.exception_per = exception_per;
    }

    public String getMin_per() {
        return min_per;
    }

    public void setMin_per(String min_per) {
        this.min_per = min_per;
    }

    public String getDay_per() {
        return day_per;
    }

    public void setDay_per(String day_per) {
        this.day_per = day_per;
    }

    public int getException_progress() {
        return exception_progress;
    }

    public void setException_progress(int exception_progress) {
        this.exception_progress = exception_progress;
    }

    public int getMin_progress() {
        return min_progress;
    }

    public void setMin_progress(int min_progress) {
        this.min_progress = min_progress;
    }

    public int getDay_progress() {
        return day_progress;
    }

    public void setDay_progress(int day_progress) {
        this.day_progress = day_progress;
    }

    public String getSumstr() {
        return sumstr;
    }

    public void setSumstr(String sumstr) {
        this.sumstr = sumstr;
    }
}
