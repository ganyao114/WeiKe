package com.gy.just.VoltageMonitor.View.Activity;

import android.os.Bundle;

import com.gy.just.VoltageMonitor.Control.Presenter.NewTopicPresenter;
import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.Mvp.annotation.InjectPresenter;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ContentView;
import com.gy.myframework.MVP.View.context.activity.BaseAppCompactActivity;

@InjectPresenter(NewTopicPresenter.class)
@ContentView(R.layout.activity_new_topic)
public class NewTopicActivity extends BaseAppCompactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
