package com.gy.just.VoltageMonitor.EventFlags;

import com.gy.just.VoltageMonitor.Model.Bean.T10Bean;
import com.gy.just.VoltageMonitor.Model.Bean.T11Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pc on 16/5/23.
 */
public class ShowT11Event implements Serializable{
    private List<T11Bean> list;

    public List<T11Bean> getList() {
        return list;
    }

    public void setList(List<T11Bean> list) {
        this.list = list;
    }
}
