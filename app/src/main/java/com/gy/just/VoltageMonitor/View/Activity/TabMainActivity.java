package com.gy.just.VoltageMonitor.View.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.gy.just.VoltageMonitor.Control.Presenter.MainPresenter;
import com.gy.just.VoltageMonitor.Model.Bean.DailinfoPojo;
import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.Mvp.annotation.InjectPresenter;
import com.gy.myframework.IOC.UI.view.viewbinder.impl.ListBinder;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ContentView;
import com.gy.myframework.MVP.View.context.activity.BaseAppCompactActivity;
import com.gy.myframework.UI.view.recyclerview.adapter.NomRcViewAdapter;
import com.gy.myframework.UI.view.recyclerview.header.RecyclerViewHeader;

import java.util.List;

@InjectPresenter(MainPresenter.class)
@ContentView(R.layout.activity_tab_main)
public class TabMainActivity extends BaseAppCompactActivity {
    private RecyclerView mRecyclerView;
    private RecyclerViewHeader header;
    private List<DailinfoPojo> list;
    private boolean first = true;
    private String dateStr,meterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        meterId = getIntent().getStringExtra("meterid");
        dateStr = getIntent().getStringExtra("date");
        list = (List<DailinfoPojo>) getIntent().getSerializableExtra("list");
        setVolTabList();
    }

    public void setVolTabList(){

        mRecyclerView = (RecyclerView) findViewById(R.id.daili_rcview);
        header = (RecyclerViewHeader) findViewById(R.id.daili_rcview_header);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));

        ListBinder.With(mRecyclerView).bind(list);

        header.attachTo(mRecyclerView);

        mRecyclerView.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager lm= (LinearLayoutManager) mRecyclerView.getLayoutManager();
                View tip = getView(R.id.tabtips_top);
                if (first){
                    first = false;
                    return;
                }
                if (lm.findFirstVisibleItemPosition() > 0)
                    return;
                if (lm.findViewByPosition(lm.findFirstVisibleItemPosition()).getTop() > 0&&lm.findFirstVisibleItemPosition()==0){
                    if (tip.getVisibility() == View.VISIBLE){
                        tip.setAnimation(AnimationUtils.loadAnimation(
                                TabMainActivity.this, R.anim.tvgone_animation));
                        tip.setVisibility(View.GONE);
                    }
                }else {
                    if (tip.getVisibility() != View.VISIBLE){
                        tip.setVisibility(View.VISIBLE);
                        tip.setAnimation(AnimationUtils.loadAnimation(
                                TabMainActivity.this, R.anim.tv_animation));
                    }
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

    private void init() {



        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));

        ListBinder.With(mRecyclerView).bind(list);

        header.attachTo(mRecyclerView);

        mRecyclerView.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager lm= (LinearLayoutManager) mRecyclerView.getLayoutManager();
                View tip = getView(R.id.tabtips_top);
                if (first){
                    first = false;
                    return;
                }
                if (lm.findFirstVisibleItemPosition() > 0)
                    return;
                if (lm.findViewByPosition(lm.findFirstVisibleItemPosition()).getTop() > 0&&lm.findFirstVisibleItemPosition()==0){
                    if (tip.getVisibility() == View.VISIBLE){
                        tip.setAnimation(AnimationUtils.loadAnimation(
                                TabMainActivity.this, R.anim.tvgone_animation));
                        tip.setVisibility(View.GONE);
                    }
                }else {
                    if (tip.getVisibility() != View.VISIBLE){
                        tip.setVisibility(View.VISIBLE);
                        tip.setAnimation(AnimationUtils.loadAnimation(
                                TabMainActivity.this, R.anim.tv_animation));
                    }
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        NomRcViewAdapter adapter = (NomRcViewAdapter) mRecyclerView.getAdapter();

//
//        morebt = (TextView) header.findViewById(R.id.morebt1);
//
//        morebt.setOnClickListener(this);
    }

}
