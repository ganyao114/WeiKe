package com.gy.just.VoltageMonitor.EventFlags;

import com.gy.just.VoltageMonitor.Model.Bean.T09Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pc on 16/5/24.
 */
public class ShowT09Event implements Serializable{
    private List<T09Bean> list;

    public List<T09Bean> getList() {
        return list;
    }

    public void setList(List<T09Bean> list) {
        this.list = list;
    }
}
