package com.gy.just.VoltageMonitor.View.Adapter;

import com.gy.just.VoltageMonitor.Model.Bean.T11Bean;
import com.gy.just.VoltageMonitor.Model.Bean.T12Bean;
import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.UI.view.collectionview.viewholder.IViewHolder;

import java.util.List;

/**
 * Created by pc on 16/5/24.
 */
public class T12Adapter extends BaseMultiHostTabAdapter<T12Bean>{
    public T12Adapter(List<T12Bean> list) {
        super(list);
    }

    @Override
    protected void covert(IViewHolder holder, int position) {
        T12Bean bean = list.get(position);
        holder.setText(R.id.t12_id,bean.getInstall());
        holder.setText(R.id.t12_meterid,bean.getMeterid());
        holder.setText(R.id.t12_install,bean.getUnit());
        holder.setText(R.id.t12_fuzeren,bean.getFuzeren());
        holder.setText(R.id.t12_date,bean.getDate());
        holder.setText(R.id.t12_xunshiren,bean.getXunshiren());
        holder.setText(R.id.t12_starttime,bean.getStart());
        holder.setText(R.id.t12_endtime,bean.getEnd());
        holder.setText(R.id.t12_wjindu,bean.getGpsx());
        holder.setText(R.id.t12_weidu,bean.getGpsy());
        holder.setText(R.id.t12_height,bean.getGpsz());
        holder.setText(R.id.t12_xunshikind,bean.getKind());
    }
}
