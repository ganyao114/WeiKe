package com.gy.just.VoltageMonitor.Model.SharePrefrence;

import com.gy.myframework.IOC.Model.local.shareprefrence.annotation.SPColumeName;
import com.gy.myframework.IOC.Model.local.shareprefrence.annotation.SPOptions;

/**
 * Created by gy939 on 2016/11/1.
 */
@SPOptions(name = "phonesetting")
public class PhoneSetting {

    @SPColumeName("wifi")
    private boolean iswifi = false;
    @SPColumeName("bt")
    private boolean isbluetooth = false;

    public boolean iswifi() {
        return iswifi;
    }

    public void setIswifi(boolean iswifi) {
        this.iswifi = iswifi;
    }

    public boolean isbluetooth() {
        return isbluetooth;
    }

    public void setIsbluetooth(boolean isbluetooth) {
        this.isbluetooth = isbluetooth;
    }
}
