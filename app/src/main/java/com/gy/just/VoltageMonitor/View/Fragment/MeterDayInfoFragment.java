package com.gy.just.VoltageMonitor.View.Fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gy.just.VoltageMonitor.Control.Presenter.MeterInfoPresenter;
import com.gy.just.VoltageMonitor.Control.Utils.MathUtils;
import com.gy.just.VoltageMonitor.EventFlags.ShowT06Event;
import com.gy.just.VoltageMonitor.Model.Bean.DetailInfo.DetailInfo;
import com.gy.just.VoltageMonitor.Model.Bean.T06Bean;
import com.gy.just.VoltageMonitor.Model.Global.Config;
import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.Service.event.annotation.InjectEvent;
import com.gy.myframework.IOC.Service.event.entity.EventThreadType;
import com.gy.myframework.IOC.Service.event.impl.EventPoster;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ContentView;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.OnClick;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ViewInject;
import com.gy.myframework.IOC.UI.view.viewinject.fragment.BaseFragmentV4;
import com.gy.myframework.MVP.Presenter.Presenter;
import com.gy.myframework.UI.customwidget.materaldialog.MaterialDialog;

import java.util.Calendar;

/**
 * Created by gy on 2016/5/8.
 */
@ContentView(R.layout.fragment_daydate_layout)
public class MeterDayInfoFragment extends BaseFragmentV4{
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


    @ViewInject(R.id.meterruninf_loading_per)
    private ProgressBar tab3_progress;
    @ViewInject(R.id.meterruninf2_loading_per)
    private ProgressBar tab3_2_progress;

    private TextView[] exceptionTexts;

    private DetailInfo detailInfo;

    private MeterInfoPresenter presenter;
    /**
     * Tab3_2相关
     */
    @ViewInject(R.id.sum_runtime)
    private TextView sumRuntime;
    @ViewInject(R.id.over_upper_time)
    private TextView over_upper_time;
    @ViewInject(R.id.over_upper_time_per)
    private TextView over_upper_time_per;
    @ViewInject(R.id.over_doenner_time)
    private TextView over_doenner_time;
    @ViewInject(R.id.over_doenner_time_per)
    private TextView over_doenner_time_per;
    @ViewInject(R.id.pass_time)
    private TextView pass_time;
    @ViewInject(R.id.pass_time_per)
    private TextView pass_time_per;
    @ViewInject(R.id.over_upper_time_progress)
    private ProgressBar over_upper_time_progress;
    @ViewInject(R.id.over_doenner_time_progress)
    private ProgressBar over_doenner_time_progress;
    @ViewInject(R.id.pass_time_progress)
    private ProgressBar pass_time_progress;
    @ViewInject(R.id.max_vol)
    private TextView max_vol;
    @ViewInject(R.id.vol_max_time)
    private TextView vol_max_time;
    @ViewInject(R.id.min_vol)
    private TextView min_vol;
    @ViewInject(R.id.vol_min_time)
    private TextView vol_min_time;
    @ViewInject(R.id.vol_avr)
    private TextView vol_avr;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EventPoster.Regist(this);
        super.onCreateView(inflater, container, savedInstanceState);
        presenter = (MeterInfoPresenter) Presenter.getPresent(MeterInfoPresenter.class);
        initExceptionView();
        presenter.getDetailInfo();
        presenter.getDetail2Info();
        return view;
    }
    @OnClick(R.id.exc_info)
    public void onClick(View v){
        showExcTips();
    }

    private void initExceptionView() {
        exceptionTexts = new TextView[9];
        @IdRes int[] viewids = {R.id.detail_exception1,R.id.detail_exception2,R.id.detail_exception3
                ,R.id.detail_exception4,R.id.detail_exception5,R.id.detail_exception6
                ,R.id.detail_exception7,R.id.detail_exception8,R.id.detail_exception9};
        for (int i = 0;i < 9;i ++){
            exceptionTexts[i] = (TextView) view.findViewById(viewids[i]);
        }
    }

    @InjectEvent(EventThreadType.MainThread)
    public void setDetailInfo(DetailInfo info){
//        tab3_load_state --;
//        if (tab3_load_state == 0)
            tab3_progress.setVisibility(View.GONE);
        this.detailInfo = info;
        Sum.setText(detailInfo.getSum()+"");
        Calendar cal = Calendar.getInstance();
        cal.setTime(presenter.curData);
        Detail_Date.setText(cal.get(Calendar.YEAR)+"年"+(cal.get(Calendar.MONTH)+1)+"月"+cal.get(Calendar.DAY_OF_MONTH)+"日");
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
    public void setDetail2Info(ShowT06Event event) {
        tab3_2_progress.setVisibility(View.GONE);
        T06Bean bean = event.getList().get(0);
        sumRuntime.setText(bean.getSumruntime());
        over_upper_time.setText("超上限时间:"+bean.getOveruptime()+"分");
        over_doenner_time.setText("超下限时间:"+bean.getOverdntime()+"分");
        pass_time.setText("合格时间"+bean.getPassTime()+"分");
        pass_time_per.setText(bean.getPassper());
        max_vol.setText(bean.getMax());
        min_vol.setText(bean.getMin());
        vol_max_time.setText(bean.getMaxtime());
        vol_min_time.setText(bean.getMintime());
        vol_avr.setText(bean.getAvr());
        over_doenner_time_per.setText(bean.getOverdnper());
        over_upper_time_per.setText(bean.getOverupper());
        pass_time_progress.setProgress(MathUtils.per2int(bean.getPassper()));
        over_upper_time_progress.setProgress(MathUtils.per2int(bean.getOverupper()));
        over_doenner_time_progress.setProgress(MathUtils.per2int(bean.getOverdnper()));
    }

    private void showExcTips(){
        final MaterialDialog dia = new MaterialDialog(getContext());
        dia.setTitle("异常种类");
        TextView textView = new TextView(getContext());
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventPoster.Unregist(this);
    }
}
