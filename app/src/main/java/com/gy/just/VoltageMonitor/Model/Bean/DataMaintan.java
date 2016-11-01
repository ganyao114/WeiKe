package com.gy.just.VoltageMonitor.Model.Bean;

import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.BindText;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.ListDataSrc;

/**
 * Created by gy939 on 2016/10/6.
 */
@ListDataSrc(R.layout.item_maintandata)
public class DataMaintan {

    @BindText(R.id.test_position)
    private String name;
    private boolean statue;
    @BindText(R.id.test_statue)
    private String statueStr = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getStatue() {
        return statue;
    }

    public void setStatue(boolean statue) {
        if (statue){
            statueStr = "";
        }else {
            statueStr = "否";
        }
        this.statue = statue;
    }
}
