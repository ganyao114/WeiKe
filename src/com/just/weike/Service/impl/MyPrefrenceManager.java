package com.just.weike.Service.impl;

import android.os.Environment;

import com.just.weike.Service.IPrefrenceManager;

public class MyPrefrenceManager implements IPrefrenceManager {
	public static String rootpath = Environment.getExternalStorageDirectory().getAbsolutePath();
	public MyPrefrenceManager() {
		// TODO Auto-generated constructor stub
	}

}
