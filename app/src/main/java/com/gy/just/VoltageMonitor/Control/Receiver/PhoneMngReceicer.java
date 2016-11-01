package com.gy.just.VoltageMonitor.Control.Receiver;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import com.gy.just.VoltageMonitor.Model.SharePrefrence.PhoneSetting;
import com.gy.myframework.IOC.Model.local.shareprefrence.impl.SharePrefrenceUtils;

/**
 * Created by gy939 on 2016/10/29.
 */

public class PhoneMngReceicer extends BroadcastReceiver {
    PhoneSetting setting = new PhoneSetting();
    @Override
    public void onReceive(Context context, Intent intent) {
        SharePrefrenceUtils.Get(setting);
        if (setting.isbluetooth())
            blueDisable(context);
        if (setting.iswifi())
            wifiDisable(context);
    }

    private void wifiDisable(Context context){
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        }
    }

    private void blueDisable(Context context){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
                .getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            return;
        }
        // 如果本地蓝牙没有开启，则开启
        if (mBluetoothAdapter.isEnabled()) {
            // 我们通过startActivityForResult()方法发起的Intent将会在onActivityResult()回调方法中获取用户的选择，比如用户单击了Yes开启，
            // 那么将会收到RESULT_OK的结果，
            // 如果RESULT_CANCELED则代表用户不愿意开启蓝牙
//            Intent mIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            context.startActivity(mIntent);
            // 用enable()方法来开启，无需询问用户(实惠无声息的开启蓝牙设备),这时就需要用到android.permission.BLUETOOTH_ADMIN权限。
            // mBluetoothAdapter.enable();
             mBluetoothAdapter.disable();//关闭蓝牙
        }
    }

}
