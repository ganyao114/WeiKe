package com.gy.just.VoltageMonitor.View.Adapter;

import com.gy.just.VoltageMonitor.Model.Bean.T08Bean;
import com.gy.just.VoltageMonitor.Model.Bean.T10Bean;
import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.UI.view.collectionview.viewholder.IViewHolder;

import java.util.List;

/**
 * Created by pc on 16/5/23.
 */
public class T10Adapter extends BaseMultiHostTabAdapter<T10Bean>{

    public T10Adapter(List<T10Bean> list) {
        super(list);
    }

    @Override
    protected void covert(IViewHolder holder, int position) {
        T10Bean bean = list.get(position);
        holder.setText(R.id.t10_id,bean.getInstall());
        holder.setText(R.id.t10_citykind,bean.getCitykind());
        holder.setText(R.id.t10_install,bean.getInstall());
        holder.setText(R.id.t10_kind,bean.getUnit());
        holder.setText(R.id.t10_vollevel,bean.getVollevel());
        holder.setText(R.id.t10_overup1_cut,bean.getOverup1_cut());
        holder.setText(R.id.t10_overup2_cut,bean.getOverup2_cut());
        holder.setText(R.id.t10_overup3_cut,bean.getOverup3_cut());
        holder.setText(R.id.t10_overup4_cut,bean.getOverup4_cut());
        holder.setText(R.id.t10_overupsum_cut,bean.getOverupsum_cut());
        holder.setText(R.id.t10_overdn1_cut,bean.getOverdn1_cut());
        holder.setText(R.id.t10_overdn2_cut,bean.getOverdn2_cut());
        holder.setText(R.id.t10_overdn3_cut,bean.getOverdn3_cut());
        holder.setText(R.id.t10_overdn4_cut,bean.getOverdn4_cut());
        holder.setText(R.id.t10_overdnsum_cut,bean.getOverdnsum_cut());
        holder.setText(R.id.t10_sum_cut,bean.getCut_sum());
    }
}
