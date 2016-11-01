package com.gy.just.VoltageMonitor.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.gy.just.VoltageMonitor.Control.Presenter.MainPresenter;
import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.Mvp.annotation.InjectPresenter;
import com.gy.myframework.MVP.View.context.activity.BaseAppCompactActivity;

@InjectPresenter(MainPresenter.class)
public class StartActivity extends BaseAppCompactActivity implements View.OnClickListener {

    private CardView button,button1,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        button = (CardView) findViewById(R.id.sys1);
        button.setOnClickListener(this);
        button1 = (CardView) findViewById(R.id.sys2);
        button1.setOnClickListener(this);
        button2 = (CardView) findViewById(R.id.sys3);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.sys1:
                intent.setClass(this,MainActivity.class);
                break;
            case R.id.sys2:
                intent.setClass(this,YunWeiActivity.class);
                break;
            case R.id.sys3:
                intent.setClass(this,MainActivity.class);
                break;
        }
        startActivity(intent);
    }
}
