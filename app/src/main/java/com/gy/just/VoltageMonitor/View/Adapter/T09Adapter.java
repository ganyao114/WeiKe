package com.gy.just.VoltageMonitor.View.Adapter;

import com.gy.just.VoltageMonitor.Model.Bean.T08Bean;
import com.gy.just.VoltageMonitor.Model.Bean.T09Bean;
import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.UI.view.collectionview.viewholder.IViewHolder;

import java.util.List;

/**
 * Created by pc on 16/5/23.
 */
public class T09Adapter extends BaseMultiHostTabAdapter<T09Bean>{

    public T09Adapter(List<T09Bean> list) {
        super(list);
    }

    @Override
    protected void covert(IViewHolder holder, int position) {
        T09Bean bean = list.get(position);
        holder.setText(R.id.t09_id,bean.getDate());
        holder.setText(R.id.t09_count_per,bean.getCountper());
        holder.setText(R.id.t09_day_cut,bean.getDaycut());
        holder.setText(R.id.t09_exc_cut,bean.getExccut());
        holder.setText(R.id.t09_min_cut,bean.getMincut());
        holder.setText(R.id.t09_overdown_cut,bean.getOverdncut());
        holder.setText(R.id.t09_overup_cut,bean.getOverupcut());
        holder.setText(R.id.t09_sum_cut,bean.getSumcut());
    }
}
