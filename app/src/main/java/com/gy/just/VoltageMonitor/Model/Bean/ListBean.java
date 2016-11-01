package com.gy.just.VoltageMonitor.Model.Bean;

import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.BindAsyncImgUrl;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.BindText;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.ListDataSrc;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.OnImgOnclick;

/**
 * Created by gy on 2016/3/15.
 */
@ListDataSrc(R.layout.testlist_item)
public class ListBean {
    @BindText(R.id.testtext)
    private String str;
    @BindAsyncImgUrl(R.id.testimg)
    @OnImgOnclick(R.id.testimg)
    private String url;

    public ListBean(String str, String url) {
        this.url = url;
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
