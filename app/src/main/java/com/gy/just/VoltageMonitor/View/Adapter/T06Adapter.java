package com.gy.just.VoltageMonitor.View.Adapter;

import com.gy.just.VoltageMonitor.Model.Bean.T06Bean;
import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.UI.view.collectionview.viewholder.IViewHolder;

import java.util.List;

/**
 * Created by pc on 16/5/15.
 */
public class T06Adapter extends BaseMultiHostTabAdapter<T06Bean>{
    public T06Adapter(List<T06Bean> list) {
        super(list);
    }

    @Override
    protected void covert(IViewHolder holder, int position) {
        T06Bean bean = list.get(position);
        holder.setText(R.id.t06_avr,bean.getAvr());
        holder.setText(R.id.t06_citykind,bean.getCitykind());
        holder.setText(R.id.t06_id,bean.getInstall());
        holder.setText(R.id.t06_install,bean.getUnit());
        holder.setText(R.id.t06_kind,bean.getKind());
        holder.setText(R.id.t06_max,bean.getMax());
        holder.setText(R.id.t06_max_time,bean.getMaxtime());
        holder.setText(R.id.t06_min,bean.getMin());
        holder.setText(R.id.t06_min_time,bean.getMintime());
        holder.setText(R.id.t06_vollevel,bean.getVollevel());
        holder.setText(R.id.t06_overup_time,bean.getOveruptime());
        holder.setText(R.id.t06_overup_per,bean.getOverupper());
        holder.setText(R.id.t06_overdown_time,bean.getOverdntime());
        holder.setText(R.id.t06_overdown_per,bean.getOverdnper());
        holder.setText(R.id.t06_passper,bean.getPassper());
        holder.setText(R.id.t06_sumtime,bean.getSumruntime());
    }
}
