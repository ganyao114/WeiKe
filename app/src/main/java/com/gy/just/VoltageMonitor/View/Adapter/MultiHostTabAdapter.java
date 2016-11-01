package com.gy.just.VoltageMonitor.View.Adapter;

import android.widget.RelativeLayout;

import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.UI.customwidget.mutihostlist.MyHScrollView;
import com.gy.myframework.UI.view.collectionview.IAdapterCallBack;
import com.gy.myframework.UI.view.collectionview.viewholder.IViewHolder;

import java.util.List;

/**
 * Created by gy on 2016/4/24.
 */
public class MultiHostTabAdapter<T> implements IAdapterCallBack {

    private List<T> list;
    private RelativeLayout mHead;
    private MyHScrollView headSrcrollView;

    public MultiHostTabAdapter(List<T> list,RelativeLayout heade) {
        this.list = list;
        mHead = heade;
        headSrcrollView = (MyHScrollView) mHead
                .findViewById(R.id.horizontalScrollView1);
    }

    @Override
    public void adapterCall(IViewHolder holder, int position) {
        headSrcrollView.AddOnScrollChangedListener(new OnScrollChangedListenerImp(
                (MyHScrollView) holder.getView(R.id.horizontalScrollView1)));
        holder.setText(R.id.t02_id,"xx单位");
    }

    class OnScrollChangedListenerImp implements MyHScrollView.OnScrollChangedListener {
        MyHScrollView mScrollViewArg;

        public OnScrollChangedListenerImp(MyHScrollView scrollViewar) {
            mScrollViewArg = scrollViewar;
        }

        @Override
        public void onScrollChanged(int l, int t, int oldl, int oldt) {
            mScrollViewArg.smoothScrollTo(l, t);
        }
    };
}
