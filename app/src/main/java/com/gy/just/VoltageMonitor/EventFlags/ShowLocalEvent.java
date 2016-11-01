package com.gy.just.VoltageMonitor.EventFlags;

import java.io.Serializable;

/**
 * Created by gy on 2016/4/23.
 */
public class ShowLocalEvent implements Serializable{

    private String gps_x;
    private String gps_y;

    public ShowLocalEvent(String gps_x, String gps_y) {
        this.gps_x = gps_x;
        this.gps_y = gps_y;
    }

    public String getGps_x() {
        return gps_x;
    }

    public void setGps_x(String gps_x) {
        this.gps_x = gps_x;
    }

    public String getGps_y() {
        return gps_y;
    }

    public void setGps_y(String gps_y) {
        this.gps_y = gps_y;
    }
}
