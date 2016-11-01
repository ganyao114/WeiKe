package com.gy.just.VoltageMonitor.EventFlags;

import com.gy.just.VoltageMonitor.Model.Bean.T10Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pc on 16/5/23.
 */
public class ShowT10Event implements Serializable{
    private List<T10Bean> list;

    public List<T10Bean> getList() {
        return list;
    }

    public void setList(List<T10Bean> list) {
        this.list = list;
    }
}
