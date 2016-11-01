package com.gy.just.VoltageMonitor.Test;

import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.ListDataSrc;

import java.io.Serializable;

/**
 * Created by gy on 2016/4/24.
 */
@ListDataSrc(R.layout.item)
public class EmptyPojo implements Serializable{
//    @BindText(R.id.textView2)
    private String str = "你好";

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
