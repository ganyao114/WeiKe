package com.gy.just.VoltageMonitor.View.Activity;

import android.os.Bundle;
import android.widget.Toast;

import com.gy.just.VoltageMonitor.Control.Presenter.ShowTabPresenter;
import com.gy.just.VoltageMonitor.Model.Bean.TabViewBean.TabValueBean;
import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.Mvp.annotation.InjectPresenter;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ContentView;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ViewInject;
import com.gy.myframework.MVP.View.context.activity.BaseAppCompactActivity;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

@InjectPresenter(ShowTabPresenter.class)
@ContentView(R.layout.activity_tab_view)
public class TabViewActivity extends BaseAppCompactActivity {
    @ViewInject(R.id.column_tab)
    private ColumnChartView columnView;
    private ColumnChartData data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        columnView.setOnValueTouchListener(new ValueTouchListener());
        init();
    }

    private void init() {
        test();
    }

    private void show(){
        TabValueBean bean = (TabValueBean) getIntent().getSerializableExtra("tabinfo");
        if (bean == null){
            showTips();
            return;
        }
        int numSubcolumns = 1;
        int numColumns = bean.getValue().length;

        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;

        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                SubcolumnValue subcolumnValue = new SubcolumnValue(bean.getValue()[i], ChartUtils.pickColor());
                subcolumnValue.setLabel(bean.getValueTips()[i]);
                values.add(subcolumnValue);
            }

            Column column = new Column(values);
            column.setHasLabels(false);
            column.setHasLabelsOnlyForSelected(false);
            columns.add(column);

            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);

            axisX.setName(bean.getTipX());
            axisY.setName(bean.getTipY());

            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);

            columnView.setColumnChartData(data);
        }
    }

    private void showTips() {

    }

    private void test() {
        int numSubcolumns = 1;
        int numColumns = 8;
        // Column can have many subcolumns, here by default I use 1 subcolumn in each of 8 columns.
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue((float) Math.random() * 50f + 5, ChartUtils.pickColor()));
            }

            Column column = new Column(values);
            column.setHasLabels(false);
            column.setHasLabelsOnlyForSelected(false);
            columns.add(column);


        }

        data = new ColumnChartData(columns);

        if (true) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (true) {
                axisX.setName("Axis X");
                axisY.setName("Axis Y");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        columnView.setColumnChartData(data);

        columnView.setZoomEnabled(true);

    }

    private class ValueTouchListener implements ColumnChartOnValueSelectListener {

        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
            Toast.makeText(TabViewActivity.this, "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }
}
