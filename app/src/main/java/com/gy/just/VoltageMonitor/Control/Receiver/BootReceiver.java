package com.gy.just.VoltageMonitor.Control.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.gy.just.VoltageMonitor.Control.Service.NotiService;
import com.gy.just.VoltageMonitor.Model.Bean.UserSp;
import com.gy.just.VoltageMonitor.Model.Global.Global;
import com.gy.myframework.IOC.Model.local.shareprefrence.impl.SharePrefrenceUtils;

public class BootReceiver extends BroadcastReceiver {
    public BootReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        UserSp userSp = new UserSp();
        SharePrefrenceUtils.Get(context,userSp);
        if (userSp.getUsername()==null)
            return;
        Intent intent1 = new Intent();
        intent1.setClass(context, NotiService.class);
        context.startService(intent1);
    }
}
