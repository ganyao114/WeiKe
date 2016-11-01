package com.gy.just.VoltageMonitor.EventFlags;

import com.gy.just.VoltageMonitor.Model.Bean.T06Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pc on 16/5/18.
 */
public class ShowT06Event implements Serializable{
    private List<T06Bean> list;

    public List<T06Bean> getList() {
        return list;
    }

    public void setList(List<T06Bean> list) {
        this.list = list;
    }
}
