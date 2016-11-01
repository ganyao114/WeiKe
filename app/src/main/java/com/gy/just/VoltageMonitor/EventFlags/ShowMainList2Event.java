package com.gy.just.VoltageMonitor.EventFlags;

import com.gy.just.VoltageMonitor.Model.Bean.CardPojo.MainTabCard2Pojo;
import com.gy.just.VoltageMonitor.Model.Bean.CardPojo.MainTabCardPojo;

import java.util.List;

/**
 * Created by gy on 2016/4/28.
 */
public class ShowMainList2Event {
    private List<MainTabCard2Pojo> list;

    public List<MainTabCard2Pojo> getList() {
        return list;
    }

    public void setList(List<MainTabCard2Pojo> list) {
        this.list = list;
    }
}
