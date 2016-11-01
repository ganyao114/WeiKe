package com.gy.just.VoltageMonitor.View.Adapter;

import com.gy.just.VoltageMonitor.Model.Bean.T06Bean;
import com.gy.just.VoltageMonitor.Model.Bean.T07Bean;
import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.UI.view.collectionview.viewholder.IViewHolder;

import java.util.List;

/**
 * Created by pc on 16/5/23.
 */
public class T07Adapter extends BaseMultiHostTabAdapter<T07Bean>{

    public T07Adapter(List<T07Bean> list) {
        super(list);
    }

    @Override
    protected void covert(IViewHolder holder, int position) {
        T07Bean bean = list.get(position);
        holder.setText(R.id.t07_avr,bean.getAvr());
        holder.setText(R.id.t07_max,bean.getMax());
        holder.setText(R.id.t07_max_time,bean.getMaxtime());
        holder.setText(R.id.t07_min,bean.getMin());
        holder.setText(R.id.t07_min_time,bean.getMintime());
        holder.setText(R.id.t07_overup_time,bean.getOveruptime());
        holder.setText(R.id.t07_overup_per,bean.getOverupper());
        holder.setText(R.id.t07_overdown_time,bean.getOverdntime());
        holder.setText(R.id.t07_overdown_per,bean.getOverdnper());
        holder.setText(R.id.t07_passper,bean.getPassper());
        holder.setText(R.id.t07_id,bean.getDate());
        holder.setText(R.id.t07_sumtime,bean.getSumruntime());
    }
}
