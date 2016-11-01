package com.gy.just.VoltageMonitor.Control.Presenter;

import android.content.Context;

import com.gy.myframework.MVP.Presenter.Presenter;
import com.gy.myframework.MVP.View.context.entity.ContextChangeEvent;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pc on 16/5/16.
 */
public class YunWeiPresenter extends Presenter{
    public static AtomicInteger YwTimes = new AtomicInteger(3*60);
    public static Date startDate;
    public static Date stopDate;
    @Override
    protected void onContextChanged(ContextChangeEvent event) {

    }

    @Override
    public void OnPresentInited(Context context) {

    }
}
