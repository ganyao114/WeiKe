package com.gy.just.VoltageMonitor.View.Adapter;

import android.support.annotation.IdRes;
import android.widget.RelativeLayout;

import com.gy.myframework.UI.customwidget.mutihostlist.MyHScrollView;
import com.gy.myframework.UI.view.collectionview.IAdapterCallBack;
import com.gy.myframework.UI.view.collectionview.viewholder.IViewHolder;

import java.util.List;

/**
 * Created by gy on 2016/4/24.
 */
public abstract class BaseMultiHostTabAdapter<T> implements IAdapterCallBack {

    protected List<T> list;
    private RelativeLayout mHead;
    private MyHScrollView headSrcrollView;
    private @IdRes int scrollViewId;

    public BaseMultiHostTabAdapter(List<T> list) {
        this.list = list;
    }

    public void init(RelativeLayout head, @IdRes int scrollViewId){
        mHead = head;
        this.scrollViewId = scrollViewId;
        headSrcrollView = (MyHScrollView) mHead
                .findViewById(scrollViewId);
    }

    @Override
    public void adapterCall(IViewHolder holder, int position) {
        headSrcrollView.AddOnScrollChangedListener(new OnScrollChangedListenerImp(
                (MyHScrollView) holder.getView(scrollViewId)));
        covert(holder, position);
    }

    protected abstract void covert(IViewHolder holder, int position);

    class OnScrollChangedListenerImp implements MyHScrollView.OnScrollChangedListener {
        MyHScrollView mScrollViewArg;

        public OnScrollChangedListenerImp(MyHScrollView scrollViewar) {
            mScrollViewArg = scrollViewar;
        }

        @Override
        public void onScrollChanged(int l, int t, int oldl, int oldt) {
            mScrollViewArg.smoothScrollTo(l, t);
        }
    }
}
