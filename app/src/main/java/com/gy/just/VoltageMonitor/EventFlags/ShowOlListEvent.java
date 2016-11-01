package com.gy.just.VoltageMonitor.EventFlags;

import com.gy.just.VoltageMonitor.Model.Bean.OnlineStatueBean;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.ListDataSrc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 16/5/24.
 */
public class ShowOlListEvent implements Serializable{
    private List<OnlineStatueBean> list = new ArrayList<>();

    public List<OnlineStatueBean> getList() {
        return list;
    }

    public void setList(List<OnlineStatueBean> list) {
        this.list = list;
    }
}
