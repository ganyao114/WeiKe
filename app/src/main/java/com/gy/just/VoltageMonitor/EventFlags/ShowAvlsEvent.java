package com.gy.just.VoltageMonitor.EventFlags;

import com.gy.just.VoltageMonitor.Model.Bean.AvlMeterBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pc on 16/5/19.
 */
public class ShowAvlsEvent implements Serializable{
    private List<AvlMeterBean> list;

    public List<AvlMeterBean> getList() {
        return list;
    }

    public void setList(List<AvlMeterBean> list) {
        this.list = list;
    }
}
