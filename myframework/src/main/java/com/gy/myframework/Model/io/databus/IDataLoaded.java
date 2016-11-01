package com.gy.myframework.Model.io.databus;

import android.view.View;

import com.gy.myframework.MVP.Model.IModelDriver;

/**
 * Created by gy on 2016/3/30.
 */
public interface IDataLoaded {
    public void onDataLoaded(IModelDriver data,View view);
    public void onDataGeted(IModelDriver data);
}
