package com.gy.just.VoltageMonitor.EventFlags;


import com.gy.just.VoltageMonitor.Model.Bean.DailinfoPojo;

import java.util.List;

/**
 * Created by gy on 2016/5/3.
 */
public class ShowVolListEvent {
    private List<DailinfoPojo> list;

    public List<DailinfoPojo> getList() {
        return list;
    }

    public void setList(List<DailinfoPojo> list) {
        this.list = list;
    }
}
