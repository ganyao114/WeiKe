package com.gy.just.VoltageMonitor.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gy.just.VoltageMonitor.Control.Presenter.MainPresenter;
import com.gy.just.VoltageMonitor.Control.Service.NotiService;
import com.gy.just.VoltageMonitor.EventFlags.LoginedEvent;
import com.gy.just.VoltageMonitor.EventFlags.RefreshNotifyListEvent;
import com.gy.just.VoltageMonitor.EventFlags.ShowLocalEvent;
import com.gy.just.VoltageMonitor.Model.Bean.UserBean;
import com.gy.just.VoltageMonitor.Model.Bean.UserSp;
import com.gy.just.VoltageMonitor.Model.Dao.NotifyDao;
import com.gy.just.VoltageMonitor.Model.Global.Global;
import com.gy.just.VoltageMonitor.Model.Net.GetListModel;
import com.gy.just.VoltageMonitor.R;
import com.gy.just.VoltageMonitor.View.Adapter.FragmentAdapter;
import com.gy.just.VoltageMonitor.View.Fragment.DataFragment;
import com.gy.just.VoltageMonitor.View.Fragment.MainFragment;
import com.gy.just.VoltageMonitor.View.Fragment.NotifyFragment;
import com.gy.myframework.IOC.Model.local.shareprefrence.impl.SharePrefrenceUtils;
import com.gy.myframework.IOC.Mvp.annotation.InjectPresenter;
import com.gy.myframework.IOC.Service.event.annotation.InjectEvent;
import com.gy.myframework.IOC.Service.event.entity.EventThreadType;
import com.gy.myframework.IOC.Service.event.impl.EventPoster;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ContentView;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.OnClick;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ViewInject;
import com.gy.myframework.MVP.View.context.activity.BaseAppCompactActivity;
import com.gy.myframework.UI.customwidget.materaldialog.MaterialDialog;

import org.xutils.db.table.DbModel;

import java.util.ArrayList;
import java.util.List;

@InjectPresenter(MainPresenter.class)
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseAppCompactActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @ViewInject(R.id.tabs)
    private TabLayout mTabLayout;
    @ViewInject(R.id.viewpager)
    private ViewPager mViewPager;
    private TextView username;
    private TextView usereamil;
    private ImageView usericon;
    private TextView loacltext;
    private MaterialDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EventPoster.Regist(this);
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setupViewPager();
        View header = navigationView.getHeaderView(0);
        username = (TextView) header.findViewById(R.id.nav_headtxt);
        usereamil = (TextView) header.findViewById(R.id.nav_textsub);
        usericon = (ImageView) header.findViewById(R.id.nav_headicon);
        loacltext = (TextView) header.findViewById(R.id.location);
        usericon.setOnClickListener(this);
        username.setOnClickListener(this);
        username.setText(Global.user.getName());
        usereamil.setText(Global.user.getEmail());
        showLocation(new ShowLocalEvent("经度"+Global.gpsx,"纬度"+Global.gpsy+""));
        getList();
    }

    private void getList() {
        GetListModel model = new GetListModel();
        model.doHttp();
    }


    private void setupViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("主视图");
        titles.add("数据查看");
        titles.add("通知信息");
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(2)));
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MainFragment());
        fragments.add(new DataFragment());
        fragments.add(new NotifyFragment());
        FragmentAdapter adapter =
                new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);
    }

    private void searchMeterById(){
        if (dialog == null){
            dialog = new MaterialDialog(this);
            final AppCompatAutoCompleteTextView editText = new AppCompatAutoCompleteTextView(this);
            editText.setHint("输入仪表ID");
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            dialog.setTitle("根据ID搜索仪表");
            dialog.setContentView(editText);
            dialog.setPositiveButton("搜索", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String meterId = editText.getText().toString();
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this,DetaillInfoActivity.class);
                    intent.putExtra("meterid",meterId);
                    startActivity(intent);
                    dialog.dismiss();
                }
            });
            dialog.setNegativeButton("取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent = new Intent();

        if (id == R.id.nav_collection) {
            // Handle the camera action
        } else if (id == R.id.nav_history) {
            intent.setClass(this,DataMaintanActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_nearby) {

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(this, SettingActivity.class));
        } else if (id == R.id.nav_send) {
            intent.setClass(this,YunWeiActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_search){
            searchMeterById();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventPoster.Unregist(this);
    }

    @InjectEvent(EventThreadType.MainThread)
    public void setDrawUserViewMain(LoginedEvent event){
        UserBean user = Global.user;
        username.setText(user.getName());
        usereamil.setText(user.getEmail());
        getList();
    }

    @InjectEvent(EventThreadType.MainThread)
    public void showLocation(ShowLocalEvent event){
        loacltext.setText(event.getGps_x() + "\n" + event.getGps_y());
    }

    private void startNotifySer(){
        UserSp userSp = new UserSp();
        userSp.setUsername(Global.user.getLoginname());
        SharePrefrenceUtils.Save(userSp);
        Intent intent = new Intent();
        intent.setClass(this, NotiService.class);
        startService(intent);
    }


    public void onClick(View view){
        if (view.getId()==R.id.nav_headtxt){
            Intent intent = new Intent();
            intent.setClass(this, UserActivity.class);
            startActivity(intent);
            return;
        }
        if (Global.user==null) {
            toLoginView();
        }else {
            Intent intent = new Intent();
            intent.setClass(this, UserActivity.class);
            startActivity(intent);
        }
    }

    @OnClick({R.id.action_refresh,R.id.action_delnotis})
    public void click(View view){
        switch (view.getId()){
            case R.id.action_refresh:
                Snackbar.make(view, "正在刷新....", Snackbar.LENGTH_LONG)
                        .setAction("刷新", null).show();
                break;
            case R.id.action_delnotis:
                NotifyDao.delAll();
                EventPoster.Broadcast(new RefreshNotifyListEvent());
                Snackbar.make(view, "通知已清除....", Snackbar.LENGTH_LONG)
                        .setAction("清除", null).show();
                break;
        }
    }

    private void toLoginView(){
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);
    }

}
