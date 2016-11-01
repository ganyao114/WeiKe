package com.gy.just.VoltageMonitor.Control.Presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.gy.just.VoltageMonitor.Model.Bean.T06Bean;
import com.gy.just.VoltageMonitor.Model.Bean.T07Bean;
import com.gy.myframework.IOC.UI.view.viewbinder.impl.ListBinder;
import com.gy.myframework.MVP.Presenter.Presenter;
import com.gy.myframework.MVP.View.context.entity.ActivityOnCreatedListener;
import com.gy.myframework.MVP.View.context.entity.ContextChangeEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 16/5/18.
 */
public class SubLarTabPresenter extends Presenter{
    public String meterId;
    public String from;
    public String to;
    public int tabid;
    @Override
    protected void onContextChanged(ContextChangeEvent event) {

    }

    @Override
    public void OnPresentInited(Context context) {
        getActivityInter().setOnCreateListener(new ActivityOnCreatedListener() {
            @Override
            public void ActivityOnCreated(Bundle savedInstanceState, Activity activity) {
                meterId = activity.getIntent().getStringExtra("meterid");
                from = activity.getIntent().getStringExtra("from");
                to = activity.getIntent().getStringExtra("to");
                tabid = activity.getIntent().getIntExtra("tabid",0);
            }
        });
    }
    public List<T07Bean> getT07List(List<T06Bean> list){
        List<T07Bean> res = new ArrayList<T07Bean>();
        for (T06Bean bean:list){
            T07Bean t07 = new T07Bean();
            t07.setSumruntime(bean.getSumruntime());
            t07.setOveruptime(bean.getOveruptime());
            t07.setOverupper(bean.getOverupper());
            t07.setOverdntime(bean.getOverdntime());
            t07.setOverdnper(bean.getOverdnper());
            t07.setPassper(bean.getPassper());
            t07.setPassTime(bean.getPassTime());
            t07.setMax(bean.getMax());
            t07.setMaxtime(bean.getMaxtime());
            t07.setMin(bean.getMin());
            t07.setMintime(bean.getMintime());
            t07.setAvr(bean.getAvr());
            t07.setDate(bean.getDate());
            res.add(t07);
        }
        return res;
    }
}
