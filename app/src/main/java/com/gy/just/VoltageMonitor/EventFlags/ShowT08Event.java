package com.gy.just.VoltageMonitor.EventFlags;

import com.gy.just.VoltageMonitor.Model.Bean.T08Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pc on 16/5/24.
 */
public class ShowT08Event implements Serializable{
    private List<T08Bean> list;

    public List<T08Bean> getList() {
        return list;
    }

    public void setList(List<T08Bean> list) {
        this.list = list;
    }
}
