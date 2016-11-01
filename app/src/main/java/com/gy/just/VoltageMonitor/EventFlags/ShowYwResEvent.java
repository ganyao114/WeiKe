package com.gy.just.VoltageMonitor.EventFlags;

import java.io.Serializable;

/**
 * Created by pc on 16/5/22.
 */
public class ShowYwResEvent implements Serializable{
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
