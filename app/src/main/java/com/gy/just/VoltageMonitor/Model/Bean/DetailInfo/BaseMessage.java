package com.gy.just.VoltageMonitor.Model.Bean.DetailInfo;

import java.io.Serializable;

/**
 * Created by gy on 2016/4/28.
 */
public class BaseMessage implements Serializable{

    private String Meter_No;
    private String Meter_Name;
    private String Produce_Local;
    private String User_Unit;
    private String Meter_kind;
    private String Meter_City_kind;
    private String Vol_Level;
    private String Mng_Person;
    private String Install_Loacl;
    private String px;
    private String py;

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public String getPx() {
        return px;
    }

    public void setPx(String px) {
        this.px = px;
    }

    public String getMeter_No() {
        return Meter_No;
    }

    public void setMeter_No(String meter_No) {
        Meter_No = meter_No;
    }

    public String getProduce_Local() {
        return Produce_Local;
    }

    public void setProduce_Local(String produce_Local) {
        Produce_Local = produce_Local;
    }

    public String getMeter_kind() {
        return Meter_kind;
    }

    public void setMeter_kind(String meter_kind) {
        Meter_kind = meter_kind;
    }

    public String getUser_Unit() {
        return User_Unit;
    }

    public void setUser_Unit(String user_Unit) {
        User_Unit = user_Unit;
    }

    public String getMeter_City_kind() {
        return Meter_City_kind;
    }

    public void setMeter_City_kind(String meter_City_kind) {
        Meter_City_kind = meter_City_kind;
    }

    public String getVol_Level() {
        return Vol_Level;
    }

    public void setVol_Level(String vol_Level) {
        Vol_Level = vol_Level;
    }

    public String getMng_Person() {
        return Mng_Person;
    }

    public void setMng_Person(String mng_Person) {
        Mng_Person = mng_Person;
    }

    public String getInstall_Loacl() {
        return Install_Loacl;
    }

    public void setInstall_Loacl(String install_Loacl) {
        Install_Loacl = install_Loacl;
    }

    public String getMeter_Name() {
        return Meter_Name;
    }

    public void setMeter_Name(String meter_Name) {
        Meter_Name = meter_Name;
    }

    @Override
    public String toString() {
        return "BaseMessage{" +
                "Meter_No=" + Meter_No +
                ", Meter_Name='" + Meter_Name + '\'' +
                ", Produce_Local='" + Produce_Local + '\'' +
                ", User_Unit='" + User_Unit + '\'' +
                ", Meter_kind='" + Meter_kind + '\'' +
                ", Meter_City_kind='" + Meter_City_kind + '\'' +
                ", Vol_Level='" + Vol_Level + '\'' +
                ", Mng_Person='" + Mng_Person + '\'' +
                ", Install_Loacl='" + Install_Loacl + '\'' +
                '}';
    }
}
