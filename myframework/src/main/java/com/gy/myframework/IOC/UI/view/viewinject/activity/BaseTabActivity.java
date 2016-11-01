package com.gy.myframework.IOC.UI.view.viewinject.activity;

import android.app.TabActivity;
import android.os.Bundle;

import com.gy.myframework.IOC.UI.view.viewinject.impl.ViewInjectAll;
import com.gy.myframework.IOC.Model.net.http.impl.HttpInjectUtil;
import com.gy.myframework.IOC.Service.event.impl.EventPoster;
import com.gy.myframework.IOC.Service.thread.impl.InjectAsycTask;

/**
 * Created by gy on 2015/11/30.
 */
public abstract class BaseTabActivity extends TabActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectAll.getInstance().inject(this);
        InjectAsycTask.getInstance().inject(this);
        HttpInjectUtil.getInstance().inject(this);
        EventPoster.getInstance().regist(this);
    }

    @Override
    protected void onDestroy() {
        InjectAsycTask.getInstance().remove(this);
        HttpInjectUtil.getInstance().remove(this);
        ViewInjectAll.getInstance().remove(this);
        EventPoster.getInstance().unregist(this);
        super.onDestroy();
    }
}
