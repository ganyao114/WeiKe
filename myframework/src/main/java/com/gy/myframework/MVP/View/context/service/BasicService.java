package com.gy.myframework.MVP.View.context.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.gy.myframework.MVP.Presenter.Presenter;
import com.gy.myframework.MVP.View.context.IContext;

/**
 * Created by gy on 2016/1/30.
 */
public class BasicService extends Service implements IContext,IService{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public Presenter getPresent() {
        return null;
    }
}
