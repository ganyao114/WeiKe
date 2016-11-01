package com.gy.just.VoltageMonitor.EventFlags;

import com.gy.just.VoltageMonitor.Model.Bean.DB.NotifyDB;

import java.util.List;

/**
 * Created by gy on 2016/5/4.
 */
public class RefreshNotifyListEvent {
    private List<NotifyDB> list;

    public List<NotifyDB> getList() {
        return list;
    }

    public void setList(List<NotifyDB> list) {
        this.list = list;
    }
}
