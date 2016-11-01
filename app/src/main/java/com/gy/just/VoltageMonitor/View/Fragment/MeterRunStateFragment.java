package com.gy.just.VoltageMonitor.View.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gy.just.VoltageMonitor.Control.Presenter.MeterInfoPresenter;
import com.gy.just.VoltageMonitor.Control.Utils.TimeUtils;
import com.gy.just.VoltageMonitor.Model.Bean.DetailInfo.DateOlInfo;
import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.Service.event.annotation.InjectEvent;
import com.gy.myframework.IOC.Service.event.entity.EventThreadType;
import com.gy.myframework.IOC.Service.event.impl.EventPoster;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ContentView;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ViewInject;
import com.gy.myframework.IOC.UI.view.viewinject.fragment.BaseFragmentV4;
import com.gy.myframework.MVP.Presenter.Presenter;
import com.gy.myframework.UI.customwidget.calendar2.CalendarView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by gy on 2016/5/8.
 */
@ContentView(R.layout.fragment_meterdate_layout)
public class MeterRunStateFragment extends BaseFragmentV4 implements CalendarView.OnDateSelectedListener,CalendarView.OnMonthChangedListener{
    /**
     * 日历相关
     */
    @ViewInject(R.id.meterdate_loading_per)
    private ProgressBar date_probar;
    @ViewInject(R.id.calendar)
    private CalendarView calendar;

    @ViewInject(R.id.date_select)
    private TextView Date_Sel;

    private MeterInfoPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EventPoster.Regist(this);
        super.onCreateView(inflater, container, savedInstanceState);
        presenter = (MeterInfoPresenter)Presenter.getPresent(MeterInfoPresenter.class);
        CalenderInt();
        presenter.getDetailOLDate(TimeUtils.getDateMonthStr(presenter.curDataMonth));
        return view;
    }

    private void CalenderInt() {
        Calendar cal = Calendar.getInstance();
        calendar.setOnDateSelectedListener(this);
        calendar.setOnMonthChangedListener(this);
    }

    @InjectEvent(EventThreadType.MainLoop)
    public void setDetailDate(DateOlInfo info){
        date_probar.setVisibility(View.GONE);
        Calendar cal = Calendar.getInstance();
        cal.setTime(presenter.curDataMonth);
        Integer[] ols = info.getOLDays().toArray(new Integer[info.getOLDays().size()]);
        calendar.markDatesOfMonth(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                false, true,ols);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventPoster.Unregist(this);
    }

    @Override
    public void onDateSelected(Date date) {
        presenter.curData = date;
        presenter.showDate = TimeUtils.getDateStr(date);
        presenter.recfresh();
        Toast.makeText(getContext(),"正在切换日期...",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDateUnselected(Date date) {

    }

    @Override
    public void onChangedToPreMonth(Date dateOfMonth) {
        date_probar.setVisibility(View.VISIBLE);
        presenter.curDataMonth = dateOfMonth;
        presenter.getDetailOLDate(TimeUtils.getDateMonthStr(dateOfMonth));
    }

    @Override
    public void onChangedToNextMonth(Date dateOfMonth) {
        date_probar.setVisibility(View.VISIBLE);
        presenter.curDataMonth = dateOfMonth;
        presenter.getDetailOLDate(TimeUtils.getDateMonthStr(dateOfMonth));
    }
}
