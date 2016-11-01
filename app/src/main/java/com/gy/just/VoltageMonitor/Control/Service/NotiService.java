package com.gy.just.VoltageMonitor.Control.Service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.IBinder;

import com.gy.just.VoltageMonitor.Control.Receiver.PhoneMngReceicer;
import com.gy.just.VoltageMonitor.Control.Utils.NotifyUtils;
import com.gy.just.VoltageMonitor.EventFlags.HasNotifyEvent;
import com.gy.just.VoltageMonitor.EventFlags.RefreshNotifyListEvent;
import com.gy.just.VoltageMonitor.Model.Bean.DB.NotifyDB;
import com.gy.just.VoltageMonitor.Model.Bean.UserSp;
import com.gy.just.VoltageMonitor.Model.Dao.NotifyDao;
import com.gy.just.VoltageMonitor.Model.Net.GetNotifyModel;
import com.gy.myframework.IOC.Model.local.shareprefrence.impl.SharePrefrenceUtils;
import com.gy.myframework.IOC.Service.event.annotation.InjectEvent;
import com.gy.myframework.IOC.Service.event.entity.EventThreadType;
import com.gy.myframework.IOC.Service.event.impl.EventPoster;

import java.util.List;

public class NotiService extends Service {
    public NotiService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventPoster.Regist(this);
        start();
        regsitReceiver();
    }

    private void regsitReceiver(){
        PhoneMngReceicer receicer = new PhoneMngReceicer();
        IntentFilter filter1, filter2, filter3, filter4,filter5,filter6,filter7,filter8;
        filter1 = new IntentFilter("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        filter2 = new IntentFilter(android.bluetooth.BluetoothDevice.ACTION_ACL_DISCONNECTED);
        filter3 = new IntentFilter(android.bluetooth.BluetoothDevice.ACTION_ACL_CONNECTED);
        filter4 = new IntentFilter(android.bluetooth.BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
        filter5 = new IntentFilter("android.bluetooth.BluetoothAdapter.STATE_OFF");
        filter6 =new IntentFilter("android.bluetooth.BluetoothAdapter.STATE_ON");
        filter7 = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter8 = new IntentFilter(WifiManager.EXTRA_WIFI_STATE);
        // BroadcastReceiver mReceiver;
        this.registerReceiver(receicer, filter1);
        this.registerReceiver(receicer, filter2);
        this.registerReceiver(receicer, filter3);
        this.registerReceiver(receicer, filter4);
        this.registerReceiver(receicer, filter5);
        this.registerReceiver(receicer, filter6);
        this.registerReceiver(receicer, filter7);
        this.registerReceiver(receicer, filter8);
    }

    @InjectEvent(EventThreadType.MainThread)
    public void onHasNotify(HasNotifyEvent event){
        List<NotifyDB> list = event.getList();
        NotifyDao.save(list);
        NotifyUtils.Show(this,"有仪表电压超限","您有"+list.size()+"个表电压超限,请速到APP中查看详情!");
        EventPoster.Broadcast(new RefreshNotifyListEvent());
    }

    private void start() {
        UserSp user = new UserSp();
        SharePrefrenceUtils.Get(this,user);
        GetNotifyModel model = new GetNotifyModel(user.getSid());
        model.doHttp();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventPoster.Unregist(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
