package com.gy.myframework.Model.io.databus;

import android.view.View;

import com.gy.myframework.MVP.Model.IModelDriver;
import com.gy.myframework.Service.core.IControlDriver;

import java.lang.ref.WeakReference;

/**
 * Created by gy on 2016/4/1.
 */
public interface ControlCallback extends IControlDriver<IModelDriver,WeakReference<View>[]>{
    @Override
    boolean control(IModelDriver data, WeakReference<View>[] views) throws Exception;
}
