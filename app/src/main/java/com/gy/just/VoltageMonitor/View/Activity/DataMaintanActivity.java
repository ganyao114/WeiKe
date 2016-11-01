package com.gy.just.VoltageMonitor.View.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gy.just.VoltageMonitor.Control.Presenter.DataMainTanPresenter;
import com.gy.just.VoltageMonitor.Control.Presenter.MeterInfoPresenter;
import com.gy.just.VoltageMonitor.Control.Utils.TimeUtils;
import com.gy.just.VoltageMonitor.Model.Bean.ShowMaintanData;
import com.gy.just.VoltageMonitor.Model.Net.GetMaintanDataModel;
import com.gy.just.VoltageMonitor.R;
import com.gy.just.VoltageMonitor.View.Adapter.FragmentAdapter;
import com.gy.just.VoltageMonitor.View.Fragment.DayDataFragment;
import com.gy.just.VoltageMonitor.View.Fragment.MeterDayInfoFragment;
import com.gy.just.VoltageMonitor.View.Fragment.MeterDetailInfoFragment;
import com.gy.just.VoltageMonitor.View.Fragment.MeterRunStateFragment;
import com.gy.just.VoltageMonitor.View.Fragment.MeterVolTabFragment;
import com.gy.just.VoltageMonitor.View.Fragment.MonthDataFragment;
import com.gy.myframework.IOC.Mvp.annotation.InjectPresenter;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ContentView;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.OnClick;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ViewInject;
import com.gy.myframework.MVP.View.context.activity.BaseAppCompactActivity;
import com.gy.myframework.UI.customwidget.calendar2.CalendarView;
import com.gy.myframework.UI.customwidget.materaldialog.MaterialDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ContentView(R.layout.activity_data_maintan)
@InjectPresenter(DataMainTanPresenter.class)
public class DataMaintanActivity extends BaseAppCompactActivity implements CalendarView.OnMonthChangedListener, CalendarView.OnDateSelectedListener, TabLayout.OnTabSelectedListener {

    @ViewInject(R.id.tabs)
    private TabLayout mTabLayout;
    @ViewInject(R.id.viewpager)
    private ViewPager mViewPager;
    @ViewInject(R.id.datamaintan_date)
    private TextView time;

    private MaterialDialog dialog;
    private CalendarView calendar;
    private String showDate;
    private String showMonth;
    private Date curData = new Date();
    private Date curDataMonth = new Date();

    private DayDataFragment dayView;
    private MonthDataFragment mothView;

    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupViewPager();
        initView();
        recfresh();
        refreshTitle();
    }

    private void initView(){
        dialog = new MaterialDialog(this);
        dialog.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recfresh();
                refreshTitle();
                Toast.makeText(DataMaintanActivity.this,"正在切换日期...",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.setTitle("选择日期");
        calendar = new CalendarView(this);
        Calendar cal = Calendar.getInstance();
        calendar.setOnDateSelectedListener(this);
        calendar.setOnMonthChangedListener(this);
        dialog.setContentView(calendar);
        showMonth = TimeUtils.getDateMonthStr(curDataMonth);
        showDate = TimeUtils.getDateStr(curData);
    }

    private void setupViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("日数据");
        titles.add("月数据");
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));
        List<Fragment> fragments = new ArrayList<>();
        dayView = new DayDataFragment();
        mothView = new MonthDataFragment();
        fragments.add(dayView);
        fragments.add(mothView);
        FragmentAdapter adapter =
                new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);
        mTabLayout.setOnTabSelectedListener(this);
    }

    @OnClick(R.id.fab)
    public void selectDate(View view){
        dialog.show();
    }

    @Override
    public void onChangedToPreMonth(Date dateOfMonth) {
        curDataMonth = dateOfMonth;
        showMonth = TimeUtils.getDateMonthStr(dateOfMonth);
    }

    @Override
    public void onChangedToNextMonth(Date dateOfMonth) {
        curDataMonth = dateOfMonth;
        showMonth = TimeUtils.getDateMonthStr(dateOfMonth);
    }

    @Override
    public void onDateSelected(Date date) {
        curData = date;
        showDate = TimeUtils.getDateStr(date);
    }

    private void recfresh() {
        GetMaintanDataModel day = new GetMaintanDataModel("day",showDate,dayView);
        GetMaintanDataModel month = new GetMaintanDataModel("month",showMonth,mothView);
        day.doHttp();
        month.doHttp();
    }

    @Override
    public void onDateUnselected(Date date) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        position = tab.getPosition();
        refreshTitle();
    }

    private void refreshTitle(){
        if (position == 0){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(curData);
            time.setText(calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH ) + 1) + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日");
        }else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(curDataMonth);
            time.setText(calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH ) + 1) + "月");
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
