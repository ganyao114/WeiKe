package com.gy.just.VoltageMonitor.View.Adapter;

import android.graphics.Color;
import android.support.annotation.IdRes;

import com.gy.just.VoltageMonitor.Model.Bean.LarTabBean.T02Bean;
import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.UI.view.collectionview.viewholder.IViewHolder;

import java.util.List;

/**
 * Created by gy on 2016/4/30.
 */
public class T02Adapter extends BaseMultiHostTabAdapter<T02Bean>{

    public T02Adapter(List<T02Bean> list) {
        super(list);
    }

    @Override
    protected void covert(IViewHolder holder, int position) {
        holder.setText(R.id.t02_id,list.get(position).getInstalllocal());
        holder.setText(R.id.t02_kind,list.get(position).getMeterkind());
        holder.setText(R.id.t02_installlocation,list.get(position).getMeterid());
        holder.setText(R.id.t02_unit,list.get(position).getOwner());
        if (list.get(position).isOnline())
            holder.setText(R.id.t02_isol,"√");
        else
            holder.setText(R.id.t02_isol,"×");
        holder.setText(R.id.t02_mindatas,list.get(position).getMin_datas()+"");
        @IdRes int E_BaseId = R.id.t02_e1;
        for (int i=0;i<9;i++){
            holder.setText(E_BaseId+i,"");
            if (list.get(position).getExceptions()[i])
                holder.getView(E_BaseId+i).setBackgroundColor(Color.RED);
            else
                holder.getView(E_BaseId+i).setBackgroundColor(Color.GREEN);
        }
        holder.setText(R.id.t02_upper,list.get(position).getOver_upper()+"");
        holder.setText(R.id.t02_downner,list.get(position).getOver_doenner()+"");
        holder.setText(R.id.t02_sum,list.get(position).getSum()+"");
    }
}
