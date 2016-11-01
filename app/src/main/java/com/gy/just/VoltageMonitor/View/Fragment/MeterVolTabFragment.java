package com.gy.just.VoltageMonitor.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.gy.just.VoltageMonitor.Control.Presenter.MeterInfoPresenter;
import com.gy.just.VoltageMonitor.Control.Utils.TimeUtils;
import com.gy.just.VoltageMonitor.EventFlags.ShowVolListEvent;
import com.gy.just.VoltageMonitor.Model.Bean.DailinfoPojo;
import com.gy.just.VoltageMonitor.Model.Bean.DetailInfo.VolTabBean;
import com.gy.just.VoltageMonitor.R;
import com.gy.just.VoltageMonitor.View.Activity.LineTabActivity;
import com.gy.myframework.IOC.Service.event.annotation.InjectEvent;
import com.gy.myframework.IOC.Service.event.entity.EventThreadType;
import com.gy.myframework.IOC.Service.event.impl.EventPoster;
import com.gy.myframework.IOC.UI.view.viewbinder.impl.ListBinder;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ContentView;
import com.gy.myframework.IOC.UI.view.viewinject.fragment.BaseFragmentV4;
import com.gy.myframework.MVP.Presenter.Presenter;
import com.gy.myframework.UI.view.recyclerview.adapter.NomRcViewAdapter;
import com.gy.myframework.UI.view.recyclerview.header.RecyclerViewHeader;

import java.util.ArrayList;
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

/**
 * Created by gy on 2016/5/8.
 */
@ContentView(R.layout.fragment_meter_voltab_layout)
public class MeterVolTabFragment extends BaseFragmentV4 implements View.OnClickListener {
    /**
     * 折线图相关
     */
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
     * 列表相关
     */
    private RecyclerView mRecyclerView;
    private RecyclerViewHeader header;
    private List<DailinfoPojo> list = new ArrayList<DailinfoPojo>();
    private boolean first = true;

    private MeterInfoPresenter presenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EventPoster.Regist(this);
        super.onCreateView(inflater, container, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.vol_list);
        header = (RecyclerViewHeader) view.findViewById(R.id.vol_header);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        header.attachTo(mRecyclerView);
        chart = (LineChartView) header.findViewById(R.id.linechart);
        chart.setOnClickListener(this);
        presenter = (MeterInfoPresenter)Presenter.getPresent(MeterInfoPresenter.class);
        presenter.getVolListData();
        presenter.getVolTab();
        return view;
    }

    @InjectEvent(EventThreadType.MainThread)
    public void setVolChart(VolTabBean bean){
//        tab3_load_state --;
//        if (tab3_load_state == 0)
//            tab3_progress.setVisibility(View.GONE);
        volTabBean = bean;
        values = presenter.processVolBean(volTabBean);
        ChartInit();
    }

    @InjectEvent(EventThreadType.MainThread)
    public void setVolListTab(ShowVolListEvent event){
        list = event.getList();
        setVolTabList();
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
        chart.setZoomEnabled(false);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(getContext(),LineTabActivity.class);
        intent.putExtra("meterid",presenter.meterId);
        intent.putExtra("date", TimeUtils.getDateStr(presenter.curData));
        startActivity(intent);
    }

    private class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
            Toast.makeText(getContext(), "时间(h):" + value.getX()+"电压(V):"+value.getY(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }

    public void setVolTabList(){



        ListBinder.With(mRecyclerView).bind(list);

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager lm= (LinearLayoutManager) mRecyclerView.getLayoutManager();
                View tip = getView(R.id.tabtips_top);
                if (first){
                    first = false;
                    return;
                }
                if (lm.findFirstVisibleItemPosition() > 0)
                    return;
                if (lm.findViewByPosition(lm.findFirstVisibleItemPosition()).getTop() > 0&&lm.findFirstVisibleItemPosition()==0){
                    if (tip.getVisibility() == View.VISIBLE){
                        tip.setAnimation(AnimationUtils.loadAnimation(
                               getContext(), R.anim.tvgone_animation));
                        tip.setVisibility(View.GONE);
                    }
                }else {
                    if (tip.getVisibility() != View.VISIBLE){
                        tip.setVisibility(View.VISIBLE);
                        tip.setAnimation(AnimationUtils.loadAnimation(
                                getContext(), R.anim.tv_animation));
                    }
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

    private void init() {



        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));

        ListBinder.With(mRecyclerView).bind(list);

        header.attachTo(mRecyclerView);

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager lm= (LinearLayoutManager) mRecyclerView.getLayoutManager();
                View tip = getView(R.id.tabtips_top);
                if (first){
                    first = false;
                    return;
                }
                if (lm.findFirstVisibleItemPosition() > 0)
                    return;
                if (lm.findViewByPosition(lm.findFirstVisibleItemPosition()).getTop() > 0&&lm.findFirstVisibleItemPosition()==0){
                    if (tip.getVisibility() == View.VISIBLE){
                        tip.setAnimation(AnimationUtils.loadAnimation(
                                getContext(), R.anim.tvgone_animation));
                        tip.setVisibility(View.GONE);
                    }
                }else {
                    if (tip.getVisibility() != View.VISIBLE){
                        tip.setVisibility(View.VISIBLE);
                        tip.setAnimation(AnimationUtils.loadAnimation(
                                getContext(), R.anim.tv_animation));
                    }
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        NomRcViewAdapter adapter = (NomRcViewAdapter) mRecyclerView.getAdapter();

//
//        morebt = (TextView) header.findViewById(R.id.morebt1);
//
//        morebt.setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventPoster.Unregist(this);
    }
}
