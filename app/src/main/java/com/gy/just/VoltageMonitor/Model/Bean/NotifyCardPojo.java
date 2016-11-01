package com.gy.just.VoltageMonitor.Model.Bean;

import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.BindAsyncImgUrl;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.BindText;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.ListDataSrc;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.OnBtClick;

import java.io.Serializable;

/**
 * Created by gy on 2016/4/25.
 */
@ListDataSrc(R.layout.card_notify_item)
public class NotifyCardPojo implements Serializable{
    @BindText(R.id.news_title)
    private String title;
    @BindText(R.id.news_desc)
    private String content;
    @BindAsyncImgUrl(R.id.news_photo)
    private String coverurl;


    @OnBtClick({R.id.btn_known,R.id.btn_more})
    private String btclick;

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

    public String getBtclick() {
        return btclick;
    }

    public void setBtclick(String btclick) {
        this.btclick = btclick;
    }
}
