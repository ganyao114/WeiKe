package com.gy.just.VoltageMonitor.EventFlags;

import com.gy.just.VoltageMonitor.Model.Bean.T12Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pc on 16/5/24.
 */
public class ShowT12Event implements Serializable{
    private List<T12Bean> list;

    public List<T12Bean> getList() {
        return list;
    }

    public void setList(List<T12Bean> list) {
        this.list = list;
    }
}
