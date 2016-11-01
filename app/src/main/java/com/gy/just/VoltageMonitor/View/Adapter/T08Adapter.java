package com.gy.just.VoltageMonitor.View.Adapter;

import com.gy.just.VoltageMonitor.Model.Bean.T07Bean;
import com.gy.just.VoltageMonitor.Model.Bean.T08Bean;
import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.UI.view.collectionview.viewholder.IViewHolder;

import java.util.List;

/**
 * Created by pc on 16/5/23.
 */
public class T08Adapter extends BaseMultiHostTabAdapter<T08Bean>{

    public T08Adapter(List<T08Bean> list) {
        super(list);
    }

    @Override
    protected void covert(IViewHolder holder, int position) {
        T08Bean bean = list.get(position);
        holder.setText(R.id.t08_id,bean.getInstall());
        holder.setText(R.id.t08_vollevel,bean.getVollevel());
        holder.setText(R.id.t08_citykind,bean.getCitykind());
        holder.setText(R.id.t08_count_per,bean.getCountper());
        holder.setText(R.id.t08_day_cut,bean.getDaycut());
        holder.setText(R.id.t08_exc_cut,bean.getExccut());
        holder.setText(R.id.t08_install,bean.getUnit());
        holder.setText(R.id.t08_kind,bean.getKind());
        holder.setText(R.id.t08_min_cut,bean.getMincut());
        holder.setText(R.id.t08_overdown_cut,bean.getOverdncut());
        holder.setText(R.id.t08_overup_cut,bean.getOverupcut());
        holder.setText(R.id.t08_sum_cut,bean.getSumcut());
    }
}
