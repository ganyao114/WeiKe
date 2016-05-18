package com.just.weike.Service.impl.Thread;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.gy.materialdesign.widgets.ProgressDialog;
import com.just.weike.Dao.IHttpService;
import com.just.weike.Dao.impl.UserServiceiml;
import com.just.weike.utils.StaticDataPackage;

public class UploadThread implements Runnable {
	
	private Context mContext;
	private InputStream inputStream;
	private Map<String, String> data;
	private ProgressDialog dialog;
	private String filename; 
	private IHttpService userService;
	private WeakReference<Activity> activityReference;
	public UploadThread(Context context,InputStream in,Map<String, String> data,String filename) {
		// TODO Auto-generated constructor stub
		mContext = context;
		inputStream = in;
		this.data = data;
		this.filename = filename;
		dialog = new ProgressDialog(mContext, "正在上传");
		userService = new UserServiceiml();
		activityReference = new WeakReference<Activity>((Activity) mContext);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			activityReference.get().runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					dialog.setCancelable(false);
					dialog.show();
				}
			});
			final String result = userService.UpLoad(null,inputStream, data, filename,StaticDataPackage.LoginName);
			activityReference.get().runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					dialog.dismiss();
					if (result.equals("success")) {
						Toast.makeText(mContext, filename+"上传成功", Toast.LENGTH_SHORT).show();
					}else {
						Toast.makeText(mContext, filename+"上传失败", Toast.LENGTH_SHORT).show();
					}
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
