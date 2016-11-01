package com.gy.just.VoltageMonitor.View.Adapter;

import com.gy.just.VoltageMonitor.Control.Utils.MathUtils;
import com.gy.just.VoltageMonitor.Model.Bean.T05Bean;
import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.UI.view.collectionview.viewholder.IViewHolder;

import java.util.List;

/**
 * Created by gy on 2016/5/3.
 */
public class T05Adapter extends BaseMultiHostTabAdapter<T05Bean>{

    public T05Adapter(List<T05Bean> list) {
        super(list);
    }

    @Override
    protected void covert(IViewHolder holder, int position) {
        T05Bean bean = list.get(position);
        holder.setText(R.id.t05_unit,bean.getUnit());
        holder.setText(R.id.t05_kind,bean.getP_kind());
        holder.setText(R.id.t05_pass_per, MathUtils.convert(bean.getPer_pass())+"%");
        holder.setText(R.id.t05_meter_count,bean.getMeter_counts()+"");
        holder.setText(R.id.t05_gay_cut,bean.getDay_cut()+"");
        holder.setText(R.id.t05_min_cut,bean.getMin_cut()+"");
        holder.setText(R.id.t05_exc_cut,bean.getExc_cut()+"");
        holder.setText(R.id.t05_over_upper_cut,bean.getOver_upper_cut()+"");
        holder.setText(R.id.t05_over_down_cut,bean.getOver_down_cut()+"");
        holder.setText(R.id.t05_count_rate,bean.getCount_rate());
        holder.setText(R.id.t05_sum,MathUtils.convert(bean.getSum())+"");
    }
}
