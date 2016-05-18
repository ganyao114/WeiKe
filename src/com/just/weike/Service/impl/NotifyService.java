package com.just.weike.Service.impl;

import com.just.weike.Dao.bean.Notify;
import com.just.weike.Service.INotifyControl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class NotifyService extends Service implements INotifyControl{
	private IBinder mBinder;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return mBinder;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		mBinder = new MyBinder();
		super.onCreate();
	}

	@Override
	public boolean sendnotify(Notify notify, String receiver) {
		// TODO Auto-generated method stub
		return false;
	}

	public class MyBinder extends Binder
	{
		INotifyControl getService()
		{
			return NotifyService.this;
		}
	}
	
}
