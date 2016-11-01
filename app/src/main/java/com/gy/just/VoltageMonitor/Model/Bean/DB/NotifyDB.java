package com.gy.just.VoltageMonitor.Model.Bean.DB;

import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.BindText;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.ListDataSrc;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.OnBtClick;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by gy on 2016/5/4.
 */
@ListDataSrc(R.layout.card_notify_item)
@Table(name = "notifies")
public class NotifyDB implements Serializable{
    @Column(name = "id",isId = true)
    private int id;
    @OnBtClick({R.id.btn_known,R.id.btn_more})
    @BindText(R.id.news_title)
    @Column(name = "title")
    private String title;
    @BindText(R.id.news_desc)
    @Column(name = "content")
    private String content;
    private String coverurl;
    @Column(name = "meterid")
    private String meterid;
    @Column(name = "time")
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCoverurl() {
        return coverurl;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
    }

    public String getMeterid() {
        return meterid;
    }

    public void setMeterid(String meterid) {
        this.meterid = meterid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
