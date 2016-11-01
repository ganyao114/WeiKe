package com.gy.just.VoltageMonitor.Control.Configs;

import com.gy.myframework.config.IConfigs;

/**
 * Created by gy on 2016/4/11.
 */
public class FrameConfigs implements IConfigs{
    @Override
    public boolean getIsInjcetView() {
        return false;
    }

    @Override
    public boolean getIsInjectAsycTask() {
        return false;
    }

    @Override
    public boolean getIsInjectHttp() {
        return false;
    }

    @Override
    public boolean getIsInjectDB() {
        return false;
    }

    @Override
    public boolean getIsInjectList() {
        return false;
    }

}
