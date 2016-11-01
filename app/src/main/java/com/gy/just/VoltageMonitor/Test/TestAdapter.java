package com.gy.just.VoltageMonitor.Test;

import com.gy.just.VoltageMonitor.View.Adapter.BaseMultiHostTabAdapter;
import com.gy.myframework.UI.view.collectionview.viewholder.IViewHolder;

import java.util.List;

/**
 * Created by gy on 2016/4/25.
 */
public class TestAdapter extends BaseMultiHostTabAdapter<EmptyPojo>{

    public TestAdapter(List<EmptyPojo> list) {
        super(list);
    }

    @Override
    protected void covert(IViewHolder holder, int position) {
//        holder.setText(R.id.t02_id,"00861816");
    }
}
