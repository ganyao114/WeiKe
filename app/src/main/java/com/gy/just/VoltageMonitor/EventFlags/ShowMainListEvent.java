package com.gy.just.VoltageMonitor.EventFlags;

import com.gy.just.VoltageMonitor.Model.Bean.CardPojo.MainTabCardPojo;

import java.util.List;

/**
 * Created by gy on 2016/4/28.
 */
public class ShowMainListEvent {
    private List<MainTabCardPojo> list;

    public List<MainTabCardPojo> getList() {
        return list;
    }

    public void setList(List<MainTabCardPojo> list) {
        this.list = list;
    }
}
