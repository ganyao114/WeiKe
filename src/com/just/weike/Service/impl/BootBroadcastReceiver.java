package com.just.weike.Service.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		 Intent service = new Intent(context,NotifyService.class);  
		 context.startService(service);  

	}

}
