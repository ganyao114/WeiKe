package com.gy.just.VoltageMonitor.View.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.gy.just.VoltageMonitor.Control.Presenter.MeterInfoPresenter;
import com.gy.just.VoltageMonitor.R;
import com.gy.just.VoltageMonitor.View.Adapter.FragmentAdapter;
import com.gy.just.VoltageMonitor.View.Fragment.MeterDayInfoFragment;
import com.gy.just.VoltageMonitor.View.Fragment.MeterDetailInfoFragment;
import com.gy.just.VoltageMonitor.View.Fragment.MeterRunStateFragment;
import com.gy.just.VoltageMonitor.View.Fragment.MeterVolTabFragment;
import com.gy.myframework.IOC.Mvp.annotation.InjectPresenter;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ContentView;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ViewInject;
import com.gy.myframework.MVP.View.context.activity.BaseAppCompactActivity;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_meter_info)
@InjectPresenter(MeterInfoPresenter.class)
public class MeterInfoActivity extends BaseAppCompactActivity {

    @ViewInject(R.id.tabs)
    private TabLayout mTabLayout;
    @ViewInject(R.id.viewpager)
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupViewPager();
    }

    private void setupViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("基本资料");
        titles.add("运行状态");
        titles.add("日数据");
        titles.add("电压曲线");
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(2)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(3)));
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MeterDetailInfoFragment());
        fragments.add(new MeterRunStateFragment());
        fragments.add(new MeterDayInfoFragment());
        fragments.add(new MeterVolTabFragment());
        FragmentAdapter adapter =
                new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);
    }

}
