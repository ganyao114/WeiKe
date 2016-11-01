package com.gy.just.VoltageMonitor.Model.Bean;

import java.util.List;

/**
 * Created by gy939 on 2016/10/6.
 */
public class ShowMaintanData {

    private List<DataMaintan> list;
    private String tar;

    public String getTar() {
        return tar;
    }

    public void setTar(String tar) {
        this.tar = tar;
    }

    public List<DataMaintan> getList() {
        return list;
    }

    public void setList(List<DataMaintan> list) {
        this.list = list;
    }
}
