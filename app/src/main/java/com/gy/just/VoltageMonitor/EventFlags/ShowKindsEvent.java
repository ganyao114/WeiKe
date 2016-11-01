package com.gy.just.VoltageMonitor.EventFlags;

import com.gy.just.VoltageMonitor.Model.Bean.KindsCardPojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gy on 2016/5/2.
 */
public class ShowKindsEvent implements Serializable{
    private List<KindsCardPojo> list;

    public List<KindsCardPojo> getList() {
        return list;
    }

    public void setList(List<KindsCardPojo> list) {
        this.list = list;
    }
}
