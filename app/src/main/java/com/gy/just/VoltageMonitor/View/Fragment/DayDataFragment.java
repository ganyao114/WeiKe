package com.gy.just.VoltageMonitor.View.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gy.just.VoltageMonitor.Control.Presenter.MeterInfoPresenter;
import com.gy.just.VoltageMonitor.Control.Utils.TimeUtils;
import com.gy.just.VoltageMonitor.Model.Bean.DataMaintan;
import com.gy.just.VoltageMonitor.Model.Bean.ShowMaintanData;
import com.gy.just.VoltageMonitor.R;
import com.gy.just.VoltageMonitor.View.Interface.IShowList;
import com.gy.myframework.IOC.Service.event.annotation.InjectEvent;
import com.gy.myframework.IOC.Service.event.entity.EventThreadType;
import com.gy.myframework.IOC.Service.event.impl.EventPoster;
import com.gy.myframework.IOC.UI.view.viewbinder.impl.ListBinder;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ContentView;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ViewInject;
import com.gy.myframework.IOC.UI.view.viewinject.fragment.BaseFragmentV4;
import com.gy.myframework.MVP.Presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gy939 on 2016/10/4.
 */
@ContentView(R.layout.fragment_maintandata_day)
public class DayDataFragment extends BaseFragmentV4 implements IShowList{

    @ViewInject(R.id.maintandata_datlist)
    private RecyclerView list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    public void refresh(ShowMaintanData data){
        ListBinder.With(list).setClass(DataMaintan.class).bind(data.getList());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
