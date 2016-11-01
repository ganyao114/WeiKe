package com.gy.just.VoltageMonitor.View.Fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gy.just.VoltageMonitor.EventFlags.ShowMainList2Event;
import com.gy.just.VoltageMonitor.EventFlags.ShowMainListEvent;
import com.gy.just.VoltageMonitor.Model.Bean.CardPojo.DaliCardPojo;
import com.gy.just.VoltageMonitor.Model.Bean.CardPojo.MainTabCard2Pojo;
import com.gy.just.VoltageMonitor.Model.Bean.CardPojo.MainTabCardPojo;
import com.gy.just.VoltageMonitor.Model.Net.GetExcListModel;
import com.gy.just.VoltageMonitor.Model.Net.GetListModel;
import com.gy.just.VoltageMonitor.R;
import com.gy.just.VoltageMonitor.Test.TestDao;
import com.gy.just.VoltageMonitor.View.Activity.LarTabListActivity;
import com.gy.myframework.IOC.Service.event.annotation.InjectEvent;
import com.gy.myframework.IOC.Service.event.entity.EventThreadType;
import com.gy.myframework.IOC.Service.event.impl.EventPoster;
import com.gy.myframework.IOC.UI.view.viewbinder.impl.ListBinder;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ContentView;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.OnClick;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ViewInject;
import com.gy.myframework.IOC.UI.view.viewinject.fragment.BaseFragmentV4;
import com.gy.myframework.UI.customwidget.mypageview.MyPageView;
import com.gy.myframework.UI.customwidget.mypageview.MyPageViewListerner;
import com.gy.myframework.UI.customwidget.mypageview.PagerViewBean;
import com.gy.myframework.UI.view.baserecycleview.recyclerview.OnItemClickListener;
import com.gy.myframework.UI.view.recyclerview.adapter.NomRcViewAdapter;
import com.gy.myframework.UI.view.recyclerview.header.RecyclerViewHeader;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by gy on 2016/4/11.
 */
@ContentView(R.layout.fragment_main_layout)
public class MainFragment extends BaseFragmentV4 implements MyPageViewListerner,View.OnClickListener,OnItemClickListener{
    @ViewInject(R.id.newslist)
    private RecyclerView mRecyclerView;
    @ViewInject(R.id.header)
    private RecyclerViewHeader header;
    @ViewInject(R.id.mypageview)
    private MyPageView pageView;
//    private TextView morebt;
    private boolean first = true;
    private List<MainTabCardPojo> cardlist;
    private List<MainTabCard2Pojo> card2list;
    private int tabId;
    @ViewInject(R.id.switchtab)
    private TabLayout switcher;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (first) {
            EventPoster.Regist(this);
            init();
            first = false;
        }
        return view;
    }

    private void getList() {
        GetListModel model = new GetListModel();
        model.doHttp();
    }

    private void getExcList() {
        GetExcListModel model = new GetExcListModel();
        model.doHttp();
    }

    @InjectEvent(EventThreadType.MainLoop)
    public void setList(ShowMainListEvent event){
        cardlist = event.getList();
        ListBinder.With(mRecyclerView).setClass(MainTabCardPojo.class).bind(cardlist);
        NomRcViewAdapter adapter = (NomRcViewAdapter) mRecyclerView.getAdapter();
        adapter.setOnItemClickListener(this);
    }

    @InjectEvent(EventThreadType.MainLoop)
    public void setEXCList(ShowMainList2Event event){
        card2list = event.getList();
        ListBinder.With(mRecyclerView).setClass(MainTabCard2Pojo.class).bind(card2list);
        NomRcViewAdapter adapter = (NomRcViewAdapter) mRecyclerView.getAdapter();
        adapter.setOnItemClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void init() {


//        cards = TestDao.getInfosCard();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));

//        ListBinder.With(mRecyclerView).bind(cards);

        header.attachTo(mRecyclerView);

        initHeader();

//
//        morebt = (TextView) header.findViewById(R.id.morebt1);
//
//        morebt.setOnClickListener(this);
    }

    private void initHeader(){
        PagerViewBean pagerViewBean = TestDao.getPagerViewBean();
        pageView.setPagerView(pagerViewBean);
        pageView.Start();
        switcher.addTab(switcher.newTab().setText("所有仪表"));
        switcher.addTab(switcher.newTab().setText("异常仪表"));
        switcher.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    getList();
                }else if (tab.getPosition() == 1){
                    getExcList();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

//    @InjectEvent(EventThreadType.MainThread)
//    public void showNewsFormControl(ShowNewsEvent value){
//        news = value.getListPojos();
//        init();
//    }
//
//    @InjectEvent(EventThreadType.MainThread)
//    public void showNewsFromModel(List<NewsListPojo> value){
//        news = value;
//        init();
//    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
     //   RecyclerView mRecyclerView = (RecyclerView) view;
      //  mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
      //  mRecyclerView.setAdapter(new RecyclerViewAdapter(getActivity()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventPoster.Unregist(this);
    }

    @Override
    public void OnItemClicked(int index) {

    }

    @OnClick({R.id.morebt1,R.id.pass_rate,R.id.lowvol_rate,R.id.cut_rate})
    public void onMore(View v){
        switch (v.getId()){
            case R.id.morebt1:
                Intent intent = new Intent();
                intent.setClass(getContext(), LarTabListActivity.class);
                intent.putExtra("tabid",4);
                startActivity(intent);
                break;
            case R.id.pass_rate:
                Intent intent2 = new Intent();
                intent2.setClass(getActivity(), LarTabListActivity.class);
                intent2.putExtra("tabid",6);
                startActivity(intent2);
                break;
            case R.id.lowvol_rate:
                Intent intent3 = new Intent();
                intent3.setClass(getContext(), LarTabListActivity.class);
                intent3.putExtra("tabid",10);
                startActivity(intent3);
                break;
            case R.id.cut_rate:
                Intent intent4 = new Intent();
                intent4.setClass(getContext(), LarTabListActivity.class);
                intent4.putExtra("tabid",8);
                startActivity(intent4);
                break;
        }

    }


    @Override
    public void onClick(View v) {
        onMore(v);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object object, int position) {
        Intent intent = new Intent();
        intent.setClass(getContext(), LarTabListActivity.class);
        intent.putExtra("tabid", 2);
        if (object instanceof MainTabCardPojo)
            intent.putExtra("pt_id",((MainTabCardPojo)object).getPtid());
        else if (object instanceof MainTabCard2Pojo)
            intent.putExtra("pt_id", ((MainTabCard2Pojo) object).getPtid());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, Object object, int position) {
        return false;
    }


}
