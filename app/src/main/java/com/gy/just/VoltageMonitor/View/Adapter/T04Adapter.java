package com.gy.just.VoltageMonitor.View.Adapter;

import android.graphics.Color;
import android.support.annotation.IdRes;

import com.gy.just.VoltageMonitor.Model.Bean.LarTabBean.T04Bean;
import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.UI.view.collectionview.viewholder.IViewHolder;

import java.util.List;

/**
 * Created by gy on 2016/4/30.
 */
public class T04Adapter extends BaseMultiHostTabAdapter<T04Bean>{

    public T04Adapter(List<T04Bean> list) {
        super(list);
    }

    @Override
    protected void covert(IViewHolder holder, int position) {
        @IdRes int stateolBase = R.id.t04_ol01;
        T04Bean bean = list.get(position);
        holder.setText(R.id.t04_id,bean.getInstall());
        holder.setText(R.id.t04_install,bean.getMeterid());
        holder.setText(R.id.t04_kind,bean.getKind());
        holder.setText(R.id.t04_unit,bean.getOwner());
        for (int i=0;i<31;i++){
            holder.setText(stateolBase+i,"");
            if (list.get(position).getIsol()[i])
                holder.getView(stateolBase+i).setBackgroundColor(Color.GREEN);
            else
                holder.getView(stateolBase+i).setBackgroundColor(Color.RED);
        }
    }
}
