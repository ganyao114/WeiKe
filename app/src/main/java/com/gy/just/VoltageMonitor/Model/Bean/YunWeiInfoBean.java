package com.gy.just.VoltageMonitor.Model.Bean;

import java.io.Serializable;

/**
 * Created by pc on 16/5/16.
 */
public class YunWeiInfoBean implements Serializable{
    private String date;
    private String person;
    private float gpsx;
    private float gpsy;
    private String tarmeter;
    private String kind;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public float getGpsx() {
        return gpsx;
    }

    public void setGpsx(float gpsx) {
        this.gpsx = gpsx;
    }

    public float getGpsy() {
        return gpsy;
    }

    public void setGpsy(float gpsy) {
        this.gpsy = gpsy;
    }

    public String getTarmeter() {
        return tarmeter;
    }

    public void setTarmeter(String tarmeter) {
        this.tarmeter = tarmeter;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
