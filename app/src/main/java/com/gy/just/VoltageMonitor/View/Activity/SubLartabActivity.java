package com.gy.just.VoltageMonitor.View.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.gy.just.VoltageMonitor.Control.Presenter.SubLarTabPresenter;
import com.gy.just.VoltageMonitor.R;
import com.gy.just.VoltageMonitor.View.Adapter.FragmentAdapter;
import com.gy.just.VoltageMonitor.View.Fragment.DataFragment;
import com.gy.just.VoltageMonitor.View.Fragment.MainFragment;
import com.gy.just.VoltageMonitor.View.Fragment.MeterDetailInfoFragment;
import com.gy.just.VoltageMonitor.View.Fragment.NotifyFragment;
import com.gy.just.VoltageMonitor.View.Fragment.T07Fragment;
import com.gy.just.VoltageMonitor.View.Fragment.T09Fragment;
import com.gy.just.VoltageMonitor.View.Fragment.T11Fragment;
import com.gy.myframework.IOC.Mvp.annotation.InjectPresenter;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ContentView;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ViewInject;
import com.gy.myframework.IOC.UI.view.viewinject.fragment.BaseFragmentV4;
import com.gy.myframework.MVP.View.context.activity.BaseAppCompactActivity;

import java.util.ArrayList;
import java.util.List;

@InjectPresenter(SubLarTabPresenter.class)
@ContentView(R.layout.activity_sub_lartab)
public class SubLartabActivity extends BaseAppCompactActivity {

    @ViewInject(R.id.tabs)
    private TabLayout mTabLayout;
    @ViewInject(R.id.container)
    private ViewPager mViewPager;

    private String Fragtitle;
    private BaseFragmentV4 frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initFrag();
        setupViewPager();
    }

    private void initFrag(){
        int tabid = ((SubLarTabPresenter)getPresent()).tabid;
        switch (tabid){
            case 7:
                frag = new T07Fragment();
                Fragtitle = "合格率报表";
                break;
            case 9:
                frag = new T09Fragment();
                Fragtitle = "扣分项报表";
                break;
            case 11:
                frag = new T11Fragment();
                Fragtitle = "低电压明细";
                break;
        }
    }

    private void setupViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("仪表基本资料");
        titles.add(Fragtitle);
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MeterDetailInfoFragment());
        fragments.add(frag);
        FragmentAdapter adapter =
                new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub_lartab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
