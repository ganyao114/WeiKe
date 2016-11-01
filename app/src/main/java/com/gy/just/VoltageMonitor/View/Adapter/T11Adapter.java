package com.gy.just.VoltageMonitor.View.Adapter;

import com.gy.just.VoltageMonitor.Model.Bean.T10Bean;
import com.gy.just.VoltageMonitor.Model.Bean.T11Bean;
import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.UI.view.collectionview.viewholder.IViewHolder;

import java.util.List;

/**
 * Created by pc on 16/5/23.
 */
public class T11Adapter extends BaseMultiHostTabAdapter<T11Bean>{

    public T11Adapter(List<T11Bean> list) {
        super(list);
    }

    @Override
    protected void covert(IViewHolder holder, int position) {
        T11Bean bean = list.get(position);
        holder.setText(R.id.t11_id,bean.getDate());
        holder.setText(R.id.t11_overup1_cut,bean.getOverup1_cut());
        holder.setText(R.id.t11_overup2_cut,bean.getOverup2_cut());
        holder.setText(R.id.t11_overup3_cut,bean.getOverup3_cut());
        holder.setText(R.id.t11_overup4_cut,bean.getOverup4_cut());
        holder.setText(R.id.t11_overupsum_cut,bean.getOverupsum_cut());
        holder.setText(R.id.t11_overdn1_cut,bean.getOverdn1_cut());
        holder.setText(R.id.t11_overdn2_cut,bean.getOverdn2_cut());
        holder.setText(R.id.t11_overdn3_cut,bean.getOverdn3_cut());
        holder.setText(R.id.t11_overdn4_cut,bean.getOverdn4_cut());
        holder.setText(R.id.t11_overdnsum_cut,bean.getOverdnsum_cut());
        holder.setText(R.id.t11_sum_cut,bean.getCut_sum());
    }
}
