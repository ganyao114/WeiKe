package com.gy.just.VoltageMonitor.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gy.just.VoltageMonitor.Control.Service.NotiService;
import com.gy.just.VoltageMonitor.EventFlags.LoginedEvent;
import com.gy.just.VoltageMonitor.Model.Bean.UserBean;
import com.gy.just.VoltageMonitor.Model.Bean.UserSp;
import com.gy.just.VoltageMonitor.Model.Global.Global;
import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.Model.local.shareprefrence.impl.SharePrefrenceUtils;
import com.gy.myframework.IOC.Service.event.annotation.InjectEvent;
import com.gy.myframework.IOC.Service.event.entity.EventThreadType;
import com.gy.myframework.IOC.Service.event.impl.EventPoster;

public class HelloActivity extends AppCompatActivity {

    private Button loginBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EventPoster.Regist(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        loginBt = (Button) findViewById(R.id.bt_login);
        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLoginView();
            }
        });
    }

    @InjectEvent(EventThreadType.MainThread)
    public void setDrawUserView(LoginedEvent event){
        UserBean user = Global.user;
        startNotifySer();
        Intent intent = new Intent();
        intent.setClass(this,StartActivity.class);
        startActivity(intent);
    }

    private void startNotifySer(){
        UserSp userSp = new UserSp();
        userSp.setUsername(Global.user.getLoginname());
        userSp.setSid(Global.user.getSid());
        SharePrefrenceUtils.Save(userSp);
        Intent intent = new Intent();
        intent.setClass(this, NotiService.class);
        startService(intent);
    }

    private void toLoginView(){
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventPoster.Unregist(this);
    }
}
