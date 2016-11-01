package com.gy.just.VoltageMonitor.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gy.just.VoltageMonitor.R;
import com.gy.just.VoltageMonitor.View.Activity.LarTabListActivity;
import com.gy.just.VoltageMonitor.View.Activity.OnlineSatueActivity;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.ContentView;
import com.gy.myframework.IOC.UI.view.viewinject.annotation.OnClick;
import com.gy.myframework.IOC.UI.view.viewinject.fragment.BaseFragmentV4;

/**
 * Created by gy on 2016/4/13.
 */
@ContentView(R.layout.fragment_data_layout)
public class DataFragment extends BaseFragmentV4{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        init();
        return view;
    }

    private void init() {

    }

    @OnClick({R.id.enter_t06_bt,R.id.enter_olstatue_bt,R.id.enter_t12_bt})
    public void OnClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.enter_t06_bt:
                intent.setClass(getContext(), LarTabListActivity.class);
                intent.putExtra("tabid",5);
                break;
            case R.id.enter_olstatue_bt:
                intent.setClass(getContext(), OnlineSatueActivity.class);
                break;
            case R.id.enter_t12_bt:
                intent.setClass(getContext(), LarTabListActivity.class);
                intent.putExtra("tabid",12);
                break;

        }
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
