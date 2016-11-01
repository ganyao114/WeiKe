package com.gy.just.VoltageMonitor.Control.Presenter;

import android.content.Context;

import com.gy.just.VoltageMonitor.Model.Bean.DetailInfo.VolTabBean;
import com.gy.myframework.MVP.Presenter.Presenter;
import com.gy.myframework.MVP.View.context.entity.ContextChangeEvent;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PointValue;

/**
 * Created by gy on 2016/4/23.
 */
public class ShowTabPresenter extends Presenter{
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
            PointValue value = new PointValue();
            String[] timestr = bean.getList()[i].timeStr.split(":");
            float time = Float.parseFloat(timestr[0]) + Float.parseFloat(timestr[1])/60f;
            value.set(time,bean.getList()[i].vol);
            value.setLabel(bean.getList()[i].timeStr);
            values.add(value);
            if (bean.getList()[i].vol < min)
                min = bean.getList()[i].vol;
            if (bean.getList()[i].vol > max)
                max = bean.getList()[i].vol;
        }
        bean.setMin(min);
        bean.setMax(max);
        bean.setLeft(values.get(0).getX());
        bean.setRight(values.get(values.size()-1).getX());
        return values;
    }
}
