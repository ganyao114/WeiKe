package com.gy.just.VoltageMonitor.View.Activity;

import android.os.Bundle;

import com.gy.just.VoltageMonitor.Control.Presenter.ShowWebPresenter;
import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.Mvp.annotation.InjectPresenter;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ContentView;
import com.gy.myframework.MVP.View.context.activity.BaseAppCompactActivity;

@ContentView(R.layout.activity_web_view)
@InjectPresenter(ShowWebPresenter.class)
public class WebViewActivity extends BaseAppCompactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
