package com.gy.just.VoltageMonitor.Control.Listener;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.gy.just.VoltageMonitor.EventFlags.ShowLarTabEvent;
import com.gy.just.VoltageMonitor.View.Activity.DetaillInfoActivity;

/**
 * Created by gy on 2016/4/28.
 */
public class MianTabItemClick implements AdapterView.OnItemClickListener{
    private ShowLarTabEvent event;

    public MianTabItemClick(ShowLarTabEvent event) {
        this.event = event;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(event.getAcRef().get(), DetaillInfoActivity.class);
        event.getAcRef().get().startActivity(intent);
    }
}
