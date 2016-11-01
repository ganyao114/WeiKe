package com.gy.just.VoltageMonitor.Control.Presenter;

import android.content.Context;

import com.gy.just.VoltageMonitor.EventFlags.ShowLarTabEvent;
import com.gy.just.VoltageMonitor.R;
import com.gy.just.VoltageMonitor.Test.EmptyPojo;
import com.gy.just.VoltageMonitor.Test.TestAdapter;
import com.gy.just.VoltageMonitor.Test.TestDao;
import com.gy.myframework.IOC.Service.event.impl.EventPoster;
import com.gy.myframework.MVP.Presenter.Presenter;
import com.gy.myframework.MVP.View.context.entity.ContextChangeEvent;

import java.util.List;

/**
 * Created by gy on 2016/4/25.
 */
public class LarTabPresenter extends Presenter{
    @Override
    protected void onContextChanged(ContextChangeEvent event) {
    }

    private void setTabView(){
        ShowLarTabEvent event = new ShowLarTabEvent();
        List list = TestDao.getEmptyList();
        event.setContentViewId(R.layout.activity_lar_tab_list);
        event.setTitle("大图表数据");
        event.setList(list);
        event.setFabViewId(R.id.fab);
        event.setHeadSrcrollViewId(R.id.horizontalScrollView1);
        event.setHeadId(R.id.list_head);
        event.setListViewId(R.id.mutihost_list);
        event.setPojoclazz(EmptyPojo.class);
        event.setToolBarId(R.id.toolbar);
        event.setAdapter(new TestAdapter(list));
        EventPoster.BroadInMainloop(event);
    }

    private void setTabView2(){
        ShowLarTabEvent event = new ShowLarTabEvent();
        List list = TestDao.getEmpty2List();
        event.setContentViewId(R.layout.activity_month_tab_list);
        event.setTitle("大图表数据");
        event.setList(list);
        event.setFabViewId(R.id.fab);
        event.setHeadSrcrollViewId(R.id.horizontalScrollView1);
        event.setHeadId(R.id.list_head);
        event.setListViewId(R.id.mutihost_list);
        event.setPojoclazz(EmptyPojo.class);
        event.setToolBarId(R.id.toolbar);
        event.setAdapter(new TestAdapter(list));
        EventPoster.BroadInMainloop(event);
    }

    @Override
    public void OnPresentInited(Context context) {
//        setTabView2();
    }
}
