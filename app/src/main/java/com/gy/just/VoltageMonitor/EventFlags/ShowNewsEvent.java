package com.gy.just.VoltageMonitor.EventFlags;

import com.gy.just.VoltageMonitor.Model.Bean.NewsListPojo;

import java.util.List;

/**
 * Created by gy on 2016/4/13.
 * Control->View
 */
public class ShowNewsEvent {
    private List<NewsListPojo> listPojos;

    public ShowNewsEvent(List<NewsListPojo> listPojos) {
        this.listPojos = listPojos;
    }

    public List<NewsListPojo> getListPojos() {
        return listPojos;
    }

    public void setListPojos(List<NewsListPojo> listPojos) {
        this.listPojos = listPojos;
    }
}
