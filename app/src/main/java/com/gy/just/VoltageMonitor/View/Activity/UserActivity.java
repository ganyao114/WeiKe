package com.gy.just.VoltageMonitor.View.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gy.just.VoltageMonitor.Control.Presenter.MainPresenter;
import com.gy.just.VoltageMonitor.Model.Bean.UserBean;
import com.gy.just.VoltageMonitor.Model.Global.Global;
import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.Mvp.annotation.InjectPresenter;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ContentView;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.OnClick;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ViewInject;
import com.gy.myframework.MVP.View.context.activity.BaseAppCompactActivity;

@InjectPresenter(MainPresenter.class)
@ContentView(R.layout.activity_user)
public class UserActivity extends BaseAppCompactActivity {

    @ViewInject(R.id.tel_num)
    private TextView Tel_No;
    @ViewInject(R.id.email_num)
    private TextView Mail_No;
    @ViewInject(R.id.local_name)
    private TextView position;
    @ViewInject(R.id.user_icon)
    private ImageView usericon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        init();
    }

    private void init() {
        UserBean user = Global.user;
        if (user == null) {
            showTips();
            return;
        }
        setTitle(user.getName());
        Tel_No.setText(user.getTel());
        Mail_No.setText(user.getEmail());
    }

    private void showTips() {

    }

    @OnClick({R.id.call_icon,R.id.send_msg})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.call_icon:

                break;
            case R.id.send_msg:

                break;
        }
    }

}
