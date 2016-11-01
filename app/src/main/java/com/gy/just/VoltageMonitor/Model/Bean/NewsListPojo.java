package com.gy.just.VoltageMonitor.Model.Bean;

import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.BindAsyncImgUrl;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.BindText;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.ListDataSrc;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.OnBtClick;

import java.io.Serializable;

/**
 * Created by gy on 2016/4/11.
 */
@ListDataSrc(R.layout.list_item_card_main)
public class NewsListPojo implements Serializable{

    @BindText(R.id.title)
    private String title;
    @BindText(R.id.content)
    private String content;
    @BindText(R.id.date)
    private String date;
    @BindAsyncImgUrl(R.id.cover)
    private String coverurl;
    @BindText(R.id.subcounts)
    private String subscriptCounts;
    @OnBtClick(R.id.subbt)
    private String subscriptButton;



    public NewsListPojo() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCoverurl() {
        return coverurl;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
    }

    public String getSubscriptButton() {
        return subscriptButton;
    }

    public void setSubscriptButton(String subscriptButton) {
        this.subscriptButton = subscriptButton;
    }

    public String getSubscriptCounts() {
        return subscriptCounts;
    }

    public void setSubscriptCounts(String subscriptCounts) {
        this.subscriptCounts = subscriptCounts;
    }
}
