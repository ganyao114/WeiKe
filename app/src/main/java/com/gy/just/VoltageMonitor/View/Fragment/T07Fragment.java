package com.gy.just.VoltageMonitor.View.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.gy.just.VoltageMonitor.Control.Presenter.SubLarTabPresenter;
import com.gy.just.VoltageMonitor.EventFlags.ShowT06Event;
import com.gy.just.VoltageMonitor.Model.Bean.T07Bean;
import com.gy.just.VoltageMonitor.Model.Net.GetT06Model;
import com.gy.just.VoltageMonitor.R;
import com.gy.just.VoltageMonitor.View.Adapter.BaseMultiHostTabAdapter;
import com.gy.just.VoltageMonitor.View.Adapter.T07Adapter;
import com.gy.myframework.IOC.Service.event.annotation.InjectEvent;
import com.gy.myframework.IOC.Service.event.entity.EventThreadType;
import com.gy.myframework.IOC.Service.event.impl.EventPoster;
import com.gy.myframework.IOC.UI.view.viewbinder.impl.ListBinder;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ContentView;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ViewInject;
import com.gy.myframework.IOC.UI.view.viewinject.fragment.BaseFragmentV4;
import com.gy.myframework.MVP.Presenter.Presenter;

import java.util.List;

/**
 * Created by pc on 16/5/15.
 */
@ContentView(R.layout.content_lar_tab7_list)
public class T07Fragment extends BaseFragmentV4 implements AdapterView.OnItemClickListener{
    @ViewInject(R.id.mutihost_list)
    private ListView listView;
    @ViewInject(R.id.list_head)
    private RelativeLayout mHead;
    private @IdRes int headSrcrollViewId = R.id.horizontalScrollView1;
    private SubLarTabPresenter presenter;
    private List<T07Bean> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EventPoster.Regist(this);
        super.onCreateView(inflater, container, savedInstanceState);
        presenter = (SubLarTabPresenter) Presenter.getPresenter(SubLarTabPresenter.class);
        init();
        getT07();
        return view;
    }

    private void init() {
        mHead.setFocusable(true);
        mHead.setClickable(true);
        mHead.setBackgroundColor(Color.parseColor("#696969"));
        mHead.setOnTouchListener(new ListViewAndHeadViewTouchLinstener());
        listView.setOnTouchListener(new ListViewAndHeadViewTouchLinstener());
        listView.setOnItemClickListener(this);
    }

    private void getT07(){
        GetT06Model model = new GetT06Model(presenter.meterId,presenter.from,presenter.to);
        model.setCallback("showT07List");
        model.doHttp();
    }

    @InjectEvent(EventThreadType.MainThread)
    public void showT07List(ShowT06Event event){
        list = presenter.getT07List(event.getList());
        BaseMultiHostTabAdapter adapter = new T07Adapter(list);
        adapter.init(mHead,headSrcrollViewId);
        ListBinder.With(listView).setCallBack(adapter).bind(list);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    class ListViewAndHeadViewTouchLinstener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View arg0, MotionEvent arg1) {
            //当在列头 和 listView控件上touch时，将这个touch的事件分发给 ScrollView
            HorizontalScrollView headSrcrollView = (HorizontalScrollView) mHead
                    .findViewById(headSrcrollViewId);
            headSrcrollView.onTouchEvent(arg1);
            return false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventPoster.Unregist(this);
    }
}
