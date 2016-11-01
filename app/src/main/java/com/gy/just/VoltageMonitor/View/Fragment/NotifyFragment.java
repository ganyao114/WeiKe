package com.gy.just.VoltageMonitor.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gy.just.VoltageMonitor.EventFlags.RefreshNotifyListEvent;
import com.gy.just.VoltageMonitor.Model.Bean.DB.NotifyDB;
import com.gy.just.VoltageMonitor.Model.Bean.NotifyCardPojo;
import com.gy.just.VoltageMonitor.Model.Dao.NotifyDao;
import com.gy.just.VoltageMonitor.R;
import com.gy.just.VoltageMonitor.Test.TestDao;
import com.gy.just.VoltageMonitor.View.Activity.DetaillInfoActivity;
import com.gy.just.VoltageMonitor.View.Activity.MeterInfoActivity;
import com.gy.myframework.IOC.Service.event.annotation.InjectEvent;
import com.gy.myframework.IOC.Service.event.entity.EventThreadType;
import com.gy.myframework.IOC.Service.event.impl.EventPoster;
import com.gy.myframework.IOC.UI.view.viewbinder.impl.ListBinder;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ContentView;
import com.gy.myframework.IOC.UI.view.viewinject.fragment.BaseFragmentV4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gy on 2016/4/11.
 */
@ContentView(R.layout.fragment_notify_layout)
public class NotifyFragment extends BaseFragmentV4 implements View.OnClickListener{
    private List<NotifyDB> list = new ArrayList<NotifyDB>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EventPoster.Regist(this);
        super.onCreateView(inflater, container, savedInstanceState);
        addNotifys(null);
        return view;
    }

//    private void init() {
//        List<NotifyCardPojo> list = TestDao.getNotifies();
//        ((RecyclerView)view).setLayoutManager(new LinearLayoutManager(getContext()));
//        ListBinder.With((RecyclerView) view).setClass(NotifyCardPojo.class).setLtnImpl(this).bind(list);
//    }

//    @OnClick(R.id.settest)
//    public void OnClick(View view){
//        Intent intent = new Intent();
//        intent.setClass(getContext(), UserActivity.class);
//        startActivity(intent);
//    }

    @InjectEvent(EventThreadType.MainThread)
    public void addNotifys(RefreshNotifyListEvent event){
        list.clear();
        List<NotifyDB> tmp = NotifyDao.getAll();
        if (tmp!=null)
            list.addAll(NotifyDao.getAll());
        ((RecyclerView)view).setLayoutManager(new LinearLayoutManager(getContext()));
        if (event == null) {
            ListBinder.With((RecyclerView) view).setClass(NotifyDB.class).setLtnImpl(this).bind(list);
            ((RecyclerView) view).setItemAnimator(new DefaultItemAnimator());
        }
        else
            ListBinder.With((RecyclerView) view).refresh();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventPoster.Unregist(this);
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        switch (v.getId()){
            case R.id.btn_known:
                NotifyDao.del(list.get(position).getId());
                list.remove(position);
                ((RecyclerView) view).getAdapter().notifyItemRemoved(position);
                ((RecyclerView) view).getAdapter().notifyItemRangeChanged(0,list.size());
                break;
            case R.id.btn_more:
                Intent intent = new Intent();
                intent.setClass(getContext(), MeterInfoActivity.class);
                intent.putExtra("meterid", list.get(position).getMeterid());
                startActivity(intent);
                break;
        }
    }
}
