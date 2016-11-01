package com.gy.just.VoltageMonitor.View.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gy.just.VoltageMonitor.Control.Presenter.MeterInfoPresenter;
import com.gy.just.VoltageMonitor.Control.Presenter.SubLarTabPresenter;
import com.gy.just.VoltageMonitor.EventFlags.ShowPositionEvent;
import com.gy.just.VoltageMonitor.Model.Bean.DetailInfo.BaseMessage;
import com.gy.just.VoltageMonitor.Model.Global.Global;
import com.gy.just.VoltageMonitor.Model.Net.GetBaseMessageModel;
import com.gy.just.VoltageMonitor.R;
import com.gy.just.VoltageMonitor.View.Activity.LarTabListActivity;
import com.gy.just.VoltageMonitor.View.Activity.MeterInfoActivity;
import com.gy.just.VoltageMonitor.View.Activity.SubLartabActivity;
import com.gy.myframework.IOC.Service.event.annotation.InjectEvent;
import com.gy.myframework.IOC.Service.event.entity.EventThreadType;
import com.gy.myframework.IOC.Service.event.impl.EventPoster;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ContentView;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ViewInject;
import com.gy.myframework.IOC.UI.view.viewinject.fragment.BaseFragmentV4;
import com.gy.myframework.MVP.Presenter.Presenter;

/**
 * Created by gy on 2016/5/8.
 */
@ContentView(R.layout.fragment_meterdetail_layout)
public class MeterDetailInfoFragment extends BaseFragmentV4{

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
    @ViewInject(R.id.position_xy)
    private TextView postion_xy;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        EventPoster.Regist(this);
        getBaseMsg();
        return view;
    }

    public void getBaseMsg() {
        probar_detail.setVisibility(View.VISIBLE);
        String meterId = null;
        if (getActivity() instanceof MeterInfoActivity){
            meterId = ((MeterInfoPresenter)Presenter.getPresenter(MeterInfoPresenter.class)).meterId;
        }else if(getActivity() instanceof SubLartabActivity){
            meterId = ((SubLarTabPresenter)Presenter.getPresent(SubLarTabPresenter.class)).meterId;
        }
        GetBaseMessageModel model = new GetBaseMessageModel(meterId);
        model.doHttp();
    }

    @InjectEvent(EventThreadType.MainLoop)
    public void setDetail(BaseMessage info){
        getActivity().setTitle(info.getInstall_Loacl());
        probar_detail.setVisibility(View.GONE);
        Meter_No.setText(info.getMeter_No());
        Produce_Local.setText(info.getProduce_Local());
        User_Unit.setText(info.getUser_Unit());
        Meter_kind.setText(info.getMeter_kind());
        Meter_City_kind.setText(info.getMeter_City_kind());
        Vol_Level.setText(info.getVol_Level());
        Mng_Person.setText(info.getMng_Person());
        Install_Loacl.setText(info.getInstall_Loacl());
        postion_xy.setText("经度:"+ info.getPx()+"纬度:"+ info.getPy());
    }

//    @InjectEvent(EventThreadType.MainThread)
//    public void showPositionXY(ShowPositionEvent event){
//        postion_xy.setText("经度:"+event.x+"纬度:"+event.y);
//    }

    @Override
    public void onDestroy() {
        EventPoster.Unregist(this);
        super.onDestroy();
    }
}
