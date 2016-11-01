package com.gy.just.VoltageMonitor.Control.Presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.gy.just.VoltageMonitor.Control.Utils.TimeUtils;
import com.gy.just.VoltageMonitor.Model.Bean.DetailInfo.BaseMessage;
import com.gy.just.VoltageMonitor.Model.Bean.DetailInfo.DateInfo;
import com.gy.just.VoltageMonitor.Model.Bean.DetailInfo.DetailInfo;
import com.gy.just.VoltageMonitor.Model.Bean.DetailInfo.VolTabBean;
import com.gy.just.VoltageMonitor.Model.Global.Global;
import com.gy.just.VoltageMonitor.Model.Net.GetBaseMessageModel;
import com.gy.just.VoltageMonitor.Model.Net.GetDetailInfoModel;
import com.gy.just.VoltageMonitor.Model.Net.GetMeterDateInfoModel;
import com.gy.just.VoltageMonitor.Model.Net.GetT06Model;
import com.gy.just.VoltageMonitor.Model.Net.GetVolListTabModel;
import com.gy.just.VoltageMonitor.Model.Net.GetVolTabModel;
import com.gy.myframework.MVP.Presenter.Presenter;
import com.gy.myframework.MVP.View.context.entity.ActivityOnCreatedListener;
import com.gy.myframework.MVP.View.context.entity.ContextChangeEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.model.PointValue;

/**
 * Created by gy on 2016/5/8.
 */
public class MeterInfoPresenter extends Presenter{

    public String meterId;
    public String showDate = TimeUtils.getDateStr(Global.defaultData);
    public BaseMessage baseMessage;
    public DateInfo dateInfo;
    public DetailInfo detailInfo;

    public VolTabBean volTabBean;
    public List<PointValue> values;

    public List<Date> date_selected;
    public Date curData = Global.defaultData;
    public Date curDataMonth = Global.defaultData;

    @Override
    protected void onContextChanged(ContextChangeEvent event) {

    }

    @Override
    public void OnPresentInited(Context context) {
        showDate = TimeUtils.getDateStr(Global.defaultData);
        getActivityInter().setOnCreateListener(new ActivityOnCreatedListener() {
            @Override
            public void ActivityOnCreated(Bundle savedInstanceState, Activity activity) {
                meterId = activity.getIntent().getStringExtra("meterid");
            }
        });
    }


    public void getVolTab() {
//        tab3_load_state = 2;
//        tab3_progress.setVisibility(View.VISIBLE);
        GetVolTabModel model = new GetVolTabModel(meterId,showDate,"setVolChart");
        model.doHttp();
    }

    public void getBaseMsg() {
//        probar_detail.setVisibility(View.VISIBLE);
        GetBaseMessageModel model = new GetBaseMessageModel(meterId);
        model.doHttp();
    }

    public void getDetailInfo(){
        GetDetailInfoModel model = new GetDetailInfoModel(meterId,showDate);
        model.doHttp();
    }

    public void getDetail2Info(){
        GetT06Model model = new GetT06Model(meterId,showDate,showDate);
        model.setCallback("setDetail2Info");
        model.doHttp();
    }

    public void getDetailOLDate(String monthStr){
//        date_probar.setVisibility(View.VISIBLE);
        GetMeterDateInfoModel model = new GetMeterDateInfoModel(meterId,monthStr);
        model.doHttp();
    }

    public void getVolListData(){
        GetVolListTabModel model = new GetVolListTabModel(meterId,showDate);
        model.doHttp();
    }

    public List<PointValue> processVolBean(VolTabBean bean){
        List<PointValue> values = new ArrayList<PointValue>();
        float min = bean.getList()[0].vol;
        float max = bean.getList()[0].vol;
        for(int i = 0;i < bean.getList().length;i ++){
            if ((i-1)%12 == 0){
                PointValue value = new PointValue();
                value.set((i-1)/12,bean.getList()[i].vol);
                value.setLabel(bean.getList()[i].timeStr);
                values.add(value);
                if (bean.getList()[i].vol < min)
                    min = bean.getList()[i].vol;
                if (bean.getList()[i].vol > max)
                    max = bean.getList()[i].vol;
            }
        }
        bean.setMin(min);
        bean.setMax(max);
        String[] strs = bean.getList()[0].timeStr.split(":");
        bean.setLeft(Float.parseFloat(strs[0]));
        return values;
    }

    public void recfresh(){
        getDetailInfo();
        getDetail2Info();
        getVolListData();
        getVolTab();
    }



}
