package com.gy.just.VoltageMonitor.View.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.gy.just.VoltageMonitor.Control.Presenter.OnlineStatuePresenter;
import com.gy.just.VoltageMonitor.EventFlags.ShowOlListEvent;
import com.gy.just.VoltageMonitor.Model.Bean.DetailInfo.DateOlInfo;
import com.gy.just.VoltageMonitor.Model.Bean.OnlineStatueBean;
import com.gy.just.VoltageMonitor.Model.Global.Global;
import com.gy.just.VoltageMonitor.Model.Net.GetMeterDateInfoModel;
import com.gy.just.VoltageMonitor.Model.Net.GetOLSatueModel;
import com.gy.just.VoltageMonitor.Model.Net.GetOlDetailModel;
import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.Mvp.annotation.InjectPresenter;
import com.gy.myframework.IOC.Service.event.annotation.InjectEvent;
import com.gy.myframework.IOC.Service.event.entity.EventThreadType;
import com.gy.myframework.IOC.UI.view.viewbinder.impl.ListBinder;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ContentView;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ViewInject;
import com.gy.myframework.MVP.View.context.activity.BaseAppCompactActivity;
import com.gy.myframework.UI.customwidget.calendar2.CalendarView;
import com.gy.myframework.UI.customwidget.materaldialog.MaterialDialog;
import com.gy.myframework.UI.view.baserecycleview.recyclerview.OnItemClickListener;
import com.gy.myframework.UI.view.recyclerview.adapter.NomRcViewAdapter;
import com.gy.myframework.UI.view.recyclerview.header.RecyclerViewHeader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ContentView(R.layout.activity_online_satue)
@InjectPresenter(OnlineStatuePresenter.class)
public class OnlineSatueActivity extends BaseAppCompactActivity implements CalendarView.OnMonthChangedListener,OnItemClickListener {

    private MaterialDialog dialog;
    private CalendarView calendarView;
    private Date curDataMonth = Global.defaultData;
    private RecyclerViewHeader header;
    @ViewInject(R.id.ol_statue_rclist)
    private RecyclerView mRecyclerView;
    @ViewInject(R.id.olstatue_time)
    private TextView time;
    private List<OnlineStatueBean> list;
    private boolean first = true;
    private String year;
    private String month;
    private String curName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        header = (RecyclerViewHeader) findViewById(R.id.ol_statue_header);
        mRecyclerView = (RecyclerView) findViewById(R.id.ol_statue_rclist);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        header.attachTo(mRecyclerView);
        init(curDataMonth);
        time.setText(year+"年"+month+"月");
        DateSelInt();
        getOlLIst();
    }

    private void getOlLIst() {
        GetOLSatueModel model = new GetOLSatueModel(year,month,this);
        model.doHttp();
    }

    private void DateSelInt() {
        dialog = new MaterialDialog(this);
        calendarView = new CalendarView(this);
        calendarView.setOnMonthChangedListener(this);
        dialog.setTitle("签到情况");
        dialog.setContentView(calendarView);
        dialog.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOlLIst();
                time.setText(year+"年"+month+"月");
                dialog.dismiss();
            }
        });
    }

    private void init(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        year = calendar.get(Calendar.YEAR)+"";
        int m = calendar.get(Calendar.MONTH)+1;
        if (m > 9){
            month = m+"";
        }else {
            month = "0"+m;
        }
    }

    private void getDetailOLDate(String name){
        GetOlDetailModel model = new GetOlDetailModel(name,year,month,this);
        model.doHttp();
    }

    @InjectEvent(EventThreadType.MainThread)
    public void setOlStatue(DateOlInfo info){
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDataMonth);
        Integer[] ols = info.getOLDays().toArray(new Integer[info.getOLDays().size()]);
        calendarView.markDatesOfMonth(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                false, true, ols);
    }

    @InjectEvent(EventThreadType.MainThread)
    public void showOlStatueList(ShowOlListEvent event){
        list = event.getList();
        Show();
    }

    private void Show(){
        ListBinder.With(mRecyclerView).setClass(OnlineStatueBean.class).bind(list);
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
                                OnlineSatueActivity.this, R.anim.tvgone_animation));
                        tip.setVisibility(View.GONE);
                    }
                }else {
                    if (tip.getVisibility() != View.VISIBLE){
                        tip.setVisibility(View.VISIBLE);
                        tip.setAnimation(AnimationUtils.loadAnimation(
                                OnlineSatueActivity.this, R.anim.tv_animation));
                    }
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        NomRcViewAdapter adapter = (NomRcViewAdapter) mRecyclerView.getAdapter();
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onChangedToPreMonth(Date dateOfMonth) {
        curDataMonth = dateOfMonth;
        init(curDataMonth);
        getDetailOLDate(curName);
    }

    @Override
    public void onChangedToNextMonth(Date dateOfMonth) {
        curDataMonth = dateOfMonth;
        init(curDataMonth);
        getDetailOLDate(curName);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        curName = ((OnlineStatueBean)list.get(position)).getUsername();
        showDia();
    }

    private void showDia() {
        dialog.show();
        getDetailOLDate(curName);
    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
        return false;
    }
}
