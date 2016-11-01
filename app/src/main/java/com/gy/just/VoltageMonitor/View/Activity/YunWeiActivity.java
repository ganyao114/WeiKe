package com.gy.just.VoltageMonitor.View.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.gy.just.VoltageMonitor.Control.Presenter.YunWeiPresenter;
import com.gy.just.VoltageMonitor.Control.Utils.TimeUtils;
import com.gy.just.VoltageMonitor.EventFlags.ShowAvlsEvent;
import com.gy.just.VoltageMonitor.EventFlags.ShowKindsEvent;
import com.gy.just.VoltageMonitor.EventFlags.ShowYwKindEvent;
import com.gy.just.VoltageMonitor.EventFlags.ShowYwResEvent;
import com.gy.just.VoltageMonitor.Model.Bean.AvlMeterBean;
import com.gy.just.VoltageMonitor.Model.Bean.KindsCardPojo;
import com.gy.just.VoltageMonitor.Model.Bean.YunWeiInfoBean;
import com.gy.just.VoltageMonitor.Model.Bean.YunWeiKindBean;
import com.gy.just.VoltageMonitor.Model.Global.Global;
import com.gy.just.VoltageMonitor.Model.Net.GetAvlMeterModel;
import com.gy.just.VoltageMonitor.Model.Net.GetKindsModel;
import com.gy.just.VoltageMonitor.Model.Net.GetYwKindModel;
import com.gy.just.VoltageMonitor.Model.Net.SendYwResModel;
import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.Mvp.annotation.InjectPresenter;
import com.gy.myframework.IOC.Service.event.annotation.InjectEvent;
import com.gy.myframework.IOC.Service.event.entity.EventThreadType;
import com.gy.myframework.IOC.Service.event.impl.EventPoster;
import com.gy.myframework.IOC.Service.thread.annotation.AsycTask;
import com.gy.myframework.IOC.Service.thread.annotation.AsycTaskHandler;
import com.gy.myframework.IOC.Service.thread.entity.Asyc;
import com.gy.myframework.IOC.Service.thread.impl.InjectAsycTask;
import com.gy.myframework.IOC.UI.view.viewbinder.impl.ListBinder;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ContentView;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.OnClick;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ViewInject;
import com.gy.myframework.MVP.View.context.activity.BaseAppCompactActivity;
import com.gy.myframework.UI.customwidget.materaldialog.MaterialDialog;

import java.util.Date;
import java.util.List;

@InjectPresenter(YunWeiPresenter.class)
@ContentView(R.layout.activity_yun_wei)
public class YunWeiActivity extends BaseAppCompactActivity {
    @ViewInject(R.id.yw_date)
    private TextView date;
    @ViewInject(R.id.yw_person)
    private TextView person;
    @ViewInject(R.id.yw_gpsx)
    private TextView gps_x;
    @ViewInject(R.id.yw_gpsy)
    private TextView gps_y;
    @ViewInject(R.id.yw_meter)
    private TextView meter;
    @ViewInject(R.id.yw_kind)
    private TextView kind;
    @ViewInject(R.id.yw_start_bt)
    private CardView start;
    @ViewInject(R.id.yw_end_bt)
    private CardView end;
    private MaterialDialog filterDialog;
    private ListView kindslist;
    private List kinds;

    private AvlMeterBean tar;
    private MaterialDialog dia,resultDia;
    private ListView listview;

    private YunWeiKindBean kindtar;

    private TextView yw_time_msg;

    private boolean active = true;
    private boolean ywing = false;
    @ViewInject(R.id.loading_per)
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventPoster.Regist(this);
        InjectAsycTask.Inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
    }

    private void initView(){
        yw_time_msg = (TextView) end.findViewById(R.id.yw_end_msg);
        date.setText(TimeUtils.getDateStrCn(new Date()));
        person.setText(Global.user.getLoginname());
        gps_x.setText(Global.gpsx + "");
        gps_y.setText(Global.gpsy + "");
    }

    private void setChooseMeterDialog(){
        if (filterDialog == null){
            filterDialog = new MaterialDialog(this);
            kindslist = new ListView(this);
            filterDialog.setTitle("请选择");
            filterDialog.setContentView(kindslist);
            filterDialog.setPositiveButton("确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tar!=null) {
                        meter.setText(tar.getName());
                        check();
                    }
                    filterDialog.dismiss();
                }
            });
            filterDialog.setNegativeButton("取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    filterDialog.dismiss();
                }
            });
        }
        filterDialog.show();
        getMetersAvl();
    }

    private void check(){
        if(tar!=null&&kindtar!=null)
            progressBar.setVisibility(View.GONE);
    }

    private void setResultDia(){
        if (resultDia == null){
            resultDia = new MaterialDialog(this);
            resultDia.setTitle("请选择");
            final EditText editText = new EditText(this);
            editText.setLines(5);
            resultDia.setContentView(editText);
            resultDia.setPositiveButton("成功", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ywing = false;
                    YunWeiPresenter.stopDate = new Date();
                    sendYwres(editText.getText().toString());
                    resultDia.dismiss();
                }
            });
            resultDia.setNegativeButton("失败", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ywing = false;
                    YunWeiPresenter.stopDate = new Date();
                    sendYwres(editText.getText().toString());
                    resultDia.dismiss();
                }
            });
        }
        resultDia.show();
    }

    private void setChooseKindDialog(){
        if (dia == null){
            dia = new MaterialDialog(this);
            listview = new ListView(this);
            dia.setTitle("请选择");
            dia.setContentView(listview);
            dia.setPositiveButton("确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (kindtar!=null) {
                        kind.setText(kindtar.getName());
                        check();
                    }
                    dia.dismiss();
                }
            });
            dia.setNegativeButton("取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dia.dismiss();
                }
            });
        }
        dia.show();
        getKinds();
    }

    private void sendYwres(String res){
        SendYwResModel model = new SendYwResModel(tar.getMeterid(),kindtar.getCode(),TimeUtils.getCurrentDateStr()
                ,YunWeiPresenter.startDate,YunWeiPresenter.stopDate,res);
        model.doHttp();
    }

    private void getKinds() {
        GetYwKindModel model = new GetYwKindModel();
        model.doHttp();
    }

    private void getMetersAvl() {
        GetAvlMeterModel model = new GetAvlMeterModel();
        model.doHttp();
    }

    @InjectEvent(EventThreadType.MainThread)
    public void setMeterAvls(ShowAvlsEvent event){
        kinds = event.getList();
        ListBinder.With(kindslist).setClass(AvlMeterBean.class).bind(kinds);
        filterDialog.setListViewHeightBasedOnChildren(kindslist);
        kindslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tar = (AvlMeterBean) kinds.get(position);
            }
        });
    }

    @InjectEvent(EventThreadType.MainThread)
    public void setYWKinds(ShowYwKindEvent event){
        kinds = event.getList();
        ListBinder.With(listview).setClass(YunWeiKindBean.class).bind(kinds);
        dia.setListViewHeightBasedOnChildren(listview);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                kindtar = (YunWeiKindBean) kinds.get(position);
            }
        });
    }

    @InjectEvent(EventThreadType.MainThread)
    public void SetYunWeiInfo(YunWeiInfoBean bean){
        person.setText(Global.user.getName());
        gps_x.setText(bean.getGpsx() + "");
        gps_y.setText(bean.getGpsy() + "");
    }
    @InjectEvent(EventThreadType.MainThread)
    public void sendYwResBack(ShowYwResEvent event){
        Toast.makeText(this,event.getMsg(),Toast.LENGTH_LONG).show();
    }

    @OnClick({R.id.yw_start_bt,R.id.yw_end_bt,R.id.yw_meter,R.id.yw_kind})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.yw_start_bt:
                startYW();
                break;
            case R.id.yw_end_bt:
                stopYW();
                break;
            case R.id.yw_meter:
                chooseMeter();
                break;
            case R.id.yw_kind:
                setChooseKindDialog();
                break;
        }
    }

    private void chooseMeter() {
        setChooseMeterDialog();
    }

    private void startYW() {
        if (tar!=null&&kindtar!=null){
            if (ywing)
                return;
            ywing = true;
            YunWeiPresenter.startDate = new Date();
            InjectAsycTask.With(this,"cutSeconds").start();
        }
    }

    private void stopYW() {
        if (ywing)
            setResultDia();
    }

    public Asyc cutSeconds(){
        YunWeiPresenter.YwTimes.set(60);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                end.setClickable(false);
            }
        });
        while (YunWeiPresenter.YwTimes.get() > 0&&active){
            final int sec = YunWeiPresenter.YwTimes.decrementAndGet();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    yw_time_msg.setText("距离结束还有"+sec+"秒");
                }
            });try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                yw_time_msg.setText("可以结束运维");
                end.setClickable(true);
            }
        });
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        active = false;
        InjectAsycTask.Remove(this);
        EventPoster.Unregist(this);
    }
}
