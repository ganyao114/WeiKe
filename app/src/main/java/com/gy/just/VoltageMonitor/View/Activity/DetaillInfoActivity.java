package com.gy.just.VoltageMonitor.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gy.just.VoltageMonitor.Control.Presenter.DetailInfoPresenter;
import com.gy.just.VoltageMonitor.Control.Utils.MathUtils;
import com.gy.just.VoltageMonitor.Control.Utils.TimeUtils;
import com.gy.just.VoltageMonitor.EventFlags.ShowVolListEvent;
import com.gy.just.VoltageMonitor.Model.Bean.DetailInfo.BaseMessage;
import com.gy.just.VoltageMonitor.Model.Bean.DetailInfo.DateInfo;
import com.gy.just.VoltageMonitor.Model.Bean.DetailInfo.DateOlInfo;
import com.gy.just.VoltageMonitor.Model.Bean.DetailInfo.DetailInfo;
import com.gy.just.VoltageMonitor.Model.Bean.DetailInfo.VolTabBean;
import com.gy.just.VoltageMonitor.Model.Global.Config;
import com.gy.just.VoltageMonitor.Model.Global.Global;
import com.gy.just.VoltageMonitor.Model.Net.GetBaseMessageModel;
import com.gy.just.VoltageMonitor.Model.Net.GetDetailInfoModel;
import com.gy.just.VoltageMonitor.Model.Net.GetMeterDateInfoModel;
import com.gy.just.VoltageMonitor.Model.Net.GetVolListTabModel;
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
import com.gy.myframework.UI.customwidget.materaldialog.MaterialDialog;

import java.io.Serializable;
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

@ContentView(R.layout.activity_detaill_info)
@InjectPresenter(DetailInfoPresenter.class)
public class DetaillInfoActivity extends BaseAppCompactActivity implements CalendarView.OnDateSelectedListener,CalendarView.OnMonthChangedListener {

    private String meterId;
    private String showDate;
    private BaseMessage baseMessage;
    private DateInfo dateInfo;
    private DetailInfo detailInfo;

    private Toolbar toolbar;

    /**
     * 折线图相关
     */
    @ViewInject(R.id.linechart)
    private LineChartView chart;

    private LineChartData data;
    private int numberOfLines = 1;
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
    private VolTabBean volTabBean;
    private List<PointValue> values;


    /**
     * 日历相关
     */
    @ViewInject(R.id.meterdate_loading_per)
    private ProgressBar date_probar;
    @ViewInject(R.id.calendar)
    private CalendarView calendar;

    @ViewInject(R.id.date_select)
    private TextView Date_Sel;
    private List<Date> date_selected;
    private Date curData = Global.defaultData;
    private Date curDataMonth = Global.defaultData;

    /**
     * 信息
     */
    @ViewInject(R.id.meterdetail_loading_per)
    private ProgressBar probar_detail;
    @ViewInject(R.id.meter_num)
    private TextView Meter_No;
    @ViewInject(R.id.produce_place)
    private TextView Produce_Local;
    @ViewInject(R.id.user_unit)
    private TextView User_Unit;
    @ViewInject(R.id.meter_kind)
    private TextView Meter_kind;
    @ViewInject(R.id.meter_city_kind)
    private TextView Meter_City_kind;
    @ViewInject(R.id.vol_level)
    private TextView Vol_Level;
    @ViewInject(R.id.mng_person)
    private TextView Mng_Person;
    @ViewInject(R.id.install_local)
    private TextView Install_Loacl;

    /**
     * Tab3相关
     */
    @ViewInject(R.id.sum)
    private TextView Sum;
    @ViewInject(R.id.date_select)
    private TextView Detail_Date;
    @ViewInject(R.id.isonline)
    private TextView Ol_State;
    @ViewInject(R.id.permin_date_count_title)
    private TextView permin_date_count_title;
    @ViewInject(R.id.permin_date_out_title)
    private TextView permin_date_out_title;
    @ViewInject(R.id.permin_date_out_title2)
    private TextView permin_date_out_title2;
    @ViewInject(R.id.permin_date_out_value2)
    private TextView permin_date_out_value2;
    @ViewInject(R.id.card_progress_value)
    private TextView card_progress_value;
    @ViewInject(R.id.card_pre_title1)
    private TextView card_pre_title1;
    @ViewInject(R.id.permin_date_count_progress)
    private ProgressBar permin_date_count_progress;
    @ViewInject(R.id.permin_date_out_progress)
    private ProgressBar permin_date_out_progress;
    @ViewInject(R.id.permin_date_out_progress2)
    private ProgressBar permin_date_out_progress2;

    /**
     * 异常相关
     */
    private TextView[] exceptionTexts;
    @ViewInject(R.id.meterruninf_loading_per)
    private ProgressBar tab3_progress;

    private int tab3_load_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EventPoster.Regist(this);
        super.onCreate(savedInstanceState);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        meterId = getIntent().getStringExtra("meterid");
        showDate = TimeUtils.getDateStr(Global.defaultData);
        initExceptionView();
        initTimeView();
        getBaseMsg();
        getVolTab();
        getDetailInfo();
        CalenderInt();
        DateSelInt();
        getDetailOLDate(TimeUtils.getDateMonthStr(curDataMonth));
    }

    private void initExceptionView() {
        exceptionTexts = new TextView[9];
        @IdRes int[] viewids = {R.id.detail_exception1,R.id.detail_exception2,R.id.detail_exception3
                ,R.id.detail_exception4,R.id.detail_exception5,R.id.detail_exception6
                ,R.id.detail_exception7,R.id.detail_exception8,R.id.detail_exception9};
        for (int i = 0;i < 9;i ++){
            exceptionTexts[i] = (TextView) findViewById(viewids[i]);
        }
    }

    private void initTimeView() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(Global.defaultData);
        Detail_Date.setText(cal.get(Calendar.YEAR)+"年"+(cal.get(Calendar.MONTH)+1)+"月"+cal.get(Calendar.DAY_OF_MONTH)+"日");
    }

    private void getVolTab() {
        tab3_load_state = 2;
        tab3_progress.setVisibility(View.VISIBLE);
        GetVolTabModel model = new GetVolTabModel(meterId,showDate,"setVolTab");
        model.doHttp();
    }

    private void getBaseMsg() {
        probar_detail.setVisibility(View.VISIBLE);
        GetBaseMessageModel model = new GetBaseMessageModel(meterId);
        model.doHttp();
    }

    private void getDetailInfo(){
        GetDetailInfoModel model = new GetDetailInfoModel(meterId,showDate);
        model.doHttp();
    }

    private void getDetailOLDate(String monthStr){
        date_probar.setVisibility(View.VISIBLE);
        GetMeterDateInfoModel model = new GetMeterDateInfoModel(meterId,monthStr);
        model.doHttp();
    }

    @InjectEvent(EventThreadType.MainThread)
    public void setDetail(BaseMessage info){
        probar_detail.setVisibility(View.GONE);
        this.baseMessage = info;
        setTitle("地址:"+baseMessage.getInstall_Loacl());
        Meter_No.setText(baseMessage.getMeter_No());
        Produce_Local.setText(baseMessage.getProduce_Local());
        User_Unit.setText(baseMessage.getUser_Unit());
        Meter_kind.setText(baseMessage.getMeter_kind());
        Meter_City_kind.setText(baseMessage.getMeter_City_kind());
        Vol_Level.setText(baseMessage.getVol_Level());
        Mng_Person.setText(baseMessage.getMng_Person());
        Install_Loacl.setText(baseMessage.getInstall_Loacl());
    }

    @InjectEvent(EventThreadType.MainThread)
    public void setDate(DateInfo info){
        date_probar.setVisibility(View.GONE);
        this.dateInfo = info;
        calendar.markDatesOfMonth(dateInfo.getYear(), dateInfo.getMonth(),
                false, true,dateInfo.getOLDays());
    }

    @InjectEvent(EventThreadType.MainThread)
    public void setDetailDate(DateOlInfo info){
        date_probar.setVisibility(View.GONE);
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDataMonth);
        Integer[] ols = info.getOLDays().toArray(new Integer[info.getOLDays().size()]);
        calendar.markDatesOfMonth(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                false, true,ols);
    }

    @InjectEvent(EventThreadType.MainThread)
    public void setVolTab(VolTabBean bean){
        tab3_load_state --;
        if (tab3_load_state == 0)
            tab3_progress.setVisibility(View.GONE);
        volTabBean = bean;
        values = ((DetailInfoPresenter)getPresent()).processVolBean(volTabBean);
        ChartInit();
    }

    @InjectEvent(EventThreadType.MainThread)
    public void setDetailInfo(DetailInfo info){
        tab3_load_state --;
        if (tab3_load_state == 0)
            tab3_progress.setVisibility(View.GONE);
        this.detailInfo = info;
        Sum.setText(detailInfo.getSum()+"");
        boolean isol = detailInfo.isOnline();
        if (isol)
            Ol_State.setText("上线");
        else
            Ol_State.setText("下线");

        permin_date_count_title.setText("分钟数据条数:"+detailInfo.getMinites_data() + "/" + detailInfo.getDataSum());
        card_progress_value.setText(MathUtils.getFloatLastTwo(detailInfo.getMinites_data(),detailInfo.getDataSum()));
        float minper = ((float)detailInfo.getMinites_data()/(float) detailInfo.getDataSum())*100;
        permin_date_count_progress.setProgress((int) minper);
        permin_date_out_title.setText("分钟数据超上限:" + detailInfo.getMindate_maxlimite() + "/" + detailInfo.getDataSum());
        card_pre_title1.setText(MathUtils.getFloatLastTwo(detailInfo.getMindate_maxlimite(),detailInfo.getDataSum()));
        float over_up = ((float)detailInfo.getMindate_maxlimite()/(float) detailInfo.getDataSum())*100;
        permin_date_out_progress.setProgress((int) over_up);
        permin_date_out_title2.setText("分钟数据超下限:" + detailInfo.getMindate_minlimite() + "/" + detailInfo.getDataSum());
        permin_date_out_value2.setText(MathUtils.getFloatLastTwo(detailInfo.getMindate_minlimite(),detailInfo.getDataSum()));
        float over_down = ((float)detailInfo.getMindate_minlimite()/(float) detailInfo.getDataSum())*100;
        permin_date_out_progress2.setProgress((int) over_down);
        for (int i = 0;i < 9;i ++){
            if (detailInfo.getExceptions()[i]){
                exceptionTexts[i].setText("√");
            }else {
                exceptionTexts[i].setText("X");
            }
        }
    }

    @InjectEvent(EventThreadType.MainThread)
    public void navtoVolList(ShowVolListEvent event){
        Intent intent = new Intent();
        intent.setClass(this,TabMainActivity.class);
        intent.putExtra("meterid",meterId);
        intent.putExtra("date",TimeUtils.getDateStr(curData));
        intent.putExtra("list", (Serializable) event.getList());
        startActivity(intent);
    }

    private void DateSelInt() {
        date_selected = new ArrayList<Date>();
        final MaterialDialog dialog = new MaterialDialog(DetaillInfoActivity.this);
        final CalendarView calendarView = new CalendarView(DetaillInfoActivity.this);
        calendarView.setOnDateSelectedListener(new CalendarView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                curData = date;
                showDate = TimeUtils.getDateStr(date);
//                Calendar cal = Calendar.getInstance();
//                cal.setTime(date);
//                Log.e("gy",cal.get(Calendar.YEAR)+"");
//                Log.e("gy",cal.get(Calendar.MONTH)+"");
//                Log.e("gy",cal.get(Calendar.DAY_OF_MONTH)+"");
//                calendarView.markDatesOfMonth(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
//                        true, true,cal.get(Calendar.DAY_OF_MONTH));
//                date_selected.add(date);
            }

            @Override
            public void onDateUnselected(Date date) {
//                calendarView.updateDataSetChanged();
//                calendarView.markDatesOfMonth(date.getYear(), date.getMonth(),
//                        false, false,date.getDay());
//                date_selected.remove(date);
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
                getVolTab();
                getDetailInfo();
                Calendar cal = Calendar.getInstance();
                cal.setTime(curData);
                Detail_Date.setText(cal.get(Calendar.YEAR)+"年"+(cal.get(Calendar.MONTH)+1)+"月"+cal.get(Calendar.DAY_OF_MONTH)+"日");
                dialog.dismiss();
            }
        });
        Date_Sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    private void CalenderInt() {
        Calendar cal = Calendar.getInstance();
        calendar.setOnDateSelectedListener(this);
        calendar.setOnMonthChangedListener(this);
    }

    private void ChartInit(){
        chart.setOnValueTouchListener(new ValueTouchListener());
        generateData();
        chart.setViewportCalculationEnabled(false);
        resetViewport();
    }

    private void resetViewport() {
        // Reset viewport height range to (0,100)
        final Viewport v = new Viewport(chart.getMaximumViewport());
        v.bottom = volTabBean.getMin();
        v.top = volTabBean.getMax();
        v.left = volTabBean.getLeft();
        v.right = volTabBean.getLeft() + values.size();
        chart.setMaximumViewport(v);
        chart.setCurrentViewport(v);
    }
    private void generateData() {

        List<Line> lines = new ArrayList<Line>();
        for (int i = 0; i < numberOfLines; ++i) {
            Line line = new Line(values);
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
                axisX.setName("小时(24)-点击图表查看大表");
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

    @Override
    public void onDateSelected(Date date) {
        Log.e("gy",date.getYear()+"");
        Log.e("gy",date.getMonth()+"");
        Log.e("gy",date.getDay()+"");
        changeInfo(date);
    }

    private void changeInfo(Date date) {

    }

    @Override
    public void onDateUnselected(Date date) {

    }

    @Override
    public void onChangedToPreMonth(Date dateOfMonth) {
        Log.e("gy","monthchange"+TimeUtils.getDateMonthStr(dateOfMonth));
        curDataMonth = dateOfMonth;
        getDetailOLDate(TimeUtils.getDateMonthStr(dateOfMonth));
    }

    @Override
    public void onChangedToNextMonth(Date dateOfMonth) {
        Log.e("gy","monthchange"+TimeUtils.getDateMonthStr(dateOfMonth));
        curDataMonth = dateOfMonth;
        getDetailOLDate(TimeUtils.getDateMonthStr(dateOfMonth));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventPoster.Unregist(this);
    }

    private void getVolListData(){
        GetVolListTabModel model = new GetVolListTabModel(meterId,showDate);
        model.doHttp();
    }

    @OnClick({R.id.linechart,R.id.sum,R.id.exc_info})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.sum:
                getVolListData();
                break;
            case R.id.linechart:
                Intent intent = new Intent();
                intent.setClass(this,LineTabActivity.class);
                intent.putExtra("meterid",meterId);
                intent.putExtra("date",TimeUtils.getDateStr(curData));
                startActivity(intent);
                break;
            case R.id.exc_info:
                showExcTips();
                break;
        }
    }

    private void showExcTips(){
        final MaterialDialog dia = new MaterialDialog(this);
        dia.setTitle("异常种类");
        TextView textView = new TextView(this);
        textView.setText(Config.EXC_INFO);
        dia.setContentView(textView);
        dia.setPositiveButton("知道了", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dia.dismiss();
            }
        });
        dia.show();
    }

    private class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
            Toast.makeText(DetaillInfoActivity.this, "时间(h):" + value.getX()+"电压(V):"+value.getY(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }
}
