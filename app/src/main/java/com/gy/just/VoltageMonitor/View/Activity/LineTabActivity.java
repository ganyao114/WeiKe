package com.gy.just.VoltageMonitor.View.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.gy.just.VoltageMonitor.Control.Presenter.ShowTabPresenter;
import com.gy.just.VoltageMonitor.Control.Utils.TimeUtils;
import com.gy.just.VoltageMonitor.Model.Bean.DetailInfo.VolTabBean;
import com.gy.just.VoltageMonitor.Model.Net.GetVolTabModel;
import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.Mvp.annotation.InjectPresenter;
import com.gy.myframework.IOC.Service.event.annotation.InjectEvent;
import com.gy.myframework.IOC.Service.event.entity.EventThreadType;
import com.gy.myframework.IOC.Service.event.impl.EventPoster;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ContentView;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.OnClick;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ViewInject;
import com.gy.myframework.MVP.View.context.activity.BaseAppCompactActivity;
import com.gy.myframework.UI.customwidget.calendar2.CalendarView;
import com.gy.myframework.UI.customwidget.floatactionbt.FloatingActionButton;
import com.gy.myframework.UI.customwidget.materaldialog.MaterialDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

@ContentView(R.layout.activity_line_tab)
@InjectPresenter(ShowTabPresenter.class)
public class LineTabActivity extends BaseAppCompactActivity {

    private String meterId;
    private VolTabBean curvolTab;
    private List<List<PointValue>> valuesList = new ArrayList<List<PointValue>>();

    @ViewInject(R.id.action_sel_dates)
    private FloatingActionButton chooseDateBt;


    @ViewInject(R.id.linechart_test)
    private LineChartView chart;
    private LineChartData data;
    private int maxNumberOfLines = 4;


    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean hasLabels = false;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = false;
    private boolean pointsHaveDifferentColor;
    private List<Date> date_selected;
    private Viewport v;


    private MaterialDialog dialog;
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EventPoster.Regist(this);
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        meterId = getIntent().getStringExtra("meterid");
        DateSelInt();
        initTab();
        GetVolTabModel model = new GetVolTabModel(meterId, getIntent().getStringExtra("date"),"setVols");
        model.doHttp();
    }

    private void getVols(Date date){
        GetVolTabModel model = new GetVolTabModel(meterId, TimeUtils.getDateStr(date),"setVols");
        model.doHttp();
    }

    @InjectEvent(EventThreadType.MainThread)
    public void setVols(VolTabBean bean){
        this.curvolTab = bean;
        List<PointValue> values = ((ShowTabPresenter)getPresent()).processVolBean(bean);
        valuesList.add(values);
        generateData();
        resetViewport();
    }

    private void initTab(){
        chart.setOnValueTouchListener(new ValueTouchListener());

        chart.setViewportCalculationEnabled(false);

    }

    private void resetViewport() {
        // Reset viewport height range to (0,100)
        if (v == null){
            v = new Viewport(chart.getMaximumViewport());
            v.bottom = curvolTab.getMin();
            v.top = curvolTab.getMax();
            v.left = curvolTab.getLeft();
            v.right = curvolTab.getRight();
            chart.setMaximumViewport(v);
            chart.setCurrentViewport(v);
            return;
        }
        if (curvolTab.getMin() < v.bottom)
            v.bottom = curvolTab.getMin();
        if (curvolTab.getMax() > v.top)
            v.top = curvolTab.getMax();
        if (curvolTab.getLeft() < v.left)
            v.left = curvolTab.getLeft();
        if (curvolTab.getRight() > v.right)
            v.right = curvolTab.getRight();
        chart.setMaximumViewport(v);
        chart.setCurrentViewport(v);
    }

    private void generateData() {

        List<Line> lines = new ArrayList<Line>();
        for (int i = 0; i < valuesList.size(); ++i) {

            Line line = new Line(valuesList.get(i));
            line.setColor(ChartUtils.COLORS[i]);
            line.setShape(shape);
            line.setCubic(isCubic);
            line.setFilled(isFilled);
            line.setHasLabels(hasLabels);
            line.setHasLabelsOnlyForSelected(hasLabelForSelected);
            line.setHasLines(hasLines);
            line.setHasPoints(hasPoints);
            if (pointsHaveDifferentColor){
                line.setPointColor(ChartUtils.COLORS[(i + 1) % ChartUtils.COLORS.length]);
            }
            lines.add(line);
        }

        data = new LineChartData(lines);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("小时(24)");
                axisY.setName("电压值(V)");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        data.setBaseValue(Float.NEGATIVE_INFINITY);
        chart.setLineChartData(data);

    }

    private void DateSelInt() {
        date_selected = new ArrayList<Date>();
        dialog = new MaterialDialog(this);
        calendarView = new CalendarView(this);
        calendarView.setOnDateSelectedListener(new CalendarView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                if (date_selected.size() < 4) {
                    if (date_selected.contains(date))
                        return;
                    date_selected.add(date);
                    upDatemarkSeled();
                }
                else
                    Toast.makeText(LineTabActivity.this,"最多只能选择4个",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDateUnselected(Date date) {
                date_selected.remove(date);
                upDatemarkSeled();
            }
        });
        dialog.setTitle("选择日期");
        dialog.setContentView(calendarView);
        dialog.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton("确定选择", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuesList.clear();
                for (Date date:date_selected)
                    getVols(date);
                dialog.dismiss();
            }
        });
    }

    private void upDatemarkSeled(){
        List<Datemark> datemarks = new ArrayList<Datemark>();
        for (Date date:date_selected){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            Integer day = calendar.get(calendar.DAY_OF_MONTH);
            boolean isadd = false;
            for (Datemark datemark:datemarks){
                if (datemark.year == year&&datemark.month == month){
                    datemark.days.add(day);
                    isadd = true;
                    break;
                }
            }
            if (!isadd){
                Datemark datemark = new Datemark();
                datemark.month = month;
                datemark.year = year;
                datemark.days.add(day);
                datemarks.add(datemark);
            }
        }
        for (Datemark datemark:datemarks){
            calendarView.markDatesOfMonth(datemark.year, datemark.month,
                    true, false, (Integer[]) datemark.days.toArray(new Integer[datemark.days.size()]));
        }
    }

    @OnClick({R.id.action_sel_dates,R.id.action_gone_line,R.id.action_gone_point})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.action_sel_dates:
                dialog.show();
                break;
            case R.id.action_gone_line:
                if (hasLines)
                    hasLines = false;
                else
                    hasLines = true;
                generateData();
                break;
            case R.id.action_gone_point:
                if (hasPoints)
                    hasPoints = false;
                else
                    hasPoints = true;
                generateData();
                break;
        }
    }

    private class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
            Toast.makeText(LineTabActivity.this, "时间:" + value.getX() + "h," + "电压" +value.getY() + "V", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventPoster.Unregist(this);
    }

    private class Datemark{
        public int year;
        public int month;
        public List<Integer> days = new ArrayList<Integer>();
    }

}
