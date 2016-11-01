package com.gy.just.VoltageMonitor.EventFlags;

import com.gy.just.VoltageMonitor.Model.Bean.YunWeiKindBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pc on 16/5/20.
 */
public class ShowYwKindEvent implements Serializable{
    private List<YunWeiKindBean> list;

    public List<YunWeiKindBean> getList() {
        return list;
    }

    public void setList(List<YunWeiKindBean> list) {
        this.list = list;
    }
}
