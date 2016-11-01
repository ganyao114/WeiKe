package com.gy.just.VoltageMonitor.Control.Presenter;

import android.content.Context;

import com.gy.just.VoltageMonitor.Model.Bean.DetailInfo.VolTabBean;
import com.gy.myframework.MVP.Presenter.Presenter;
import com.gy.myframework.MVP.View.context.entity.ContextChangeEvent;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PointValue;

/**
 * Created by gy on 2016/4/25.
 */
public class DetailInfoPresenter extends Presenter{
    @Override
    protected void onContextChanged(ContextChangeEvent event) {

    }

    @Override
    public void OnPresentInited(Context context) {

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
}
