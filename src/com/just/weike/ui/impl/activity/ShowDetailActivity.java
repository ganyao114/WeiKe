package com.just.weike.ui.impl.activity;

import java.lang.ref.WeakReference;

import uk.co.senab.photoview.PhotoView;

import com.just.weike.R;
import com.just.weike.Service.impl.Thread.ShowDetailThread;
import com.just.weike.Service.impl.Thread.Pool.MyWorkThreadQueue;
import com.just.weike.utils.StaticDataPackage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class ShowDetailActivity extends Activity {
	
	private PhotoView picview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detailview_layout);
		picview = (PhotoView) findViewById(R.id.photoView1);
		Intent intent = getIntent();
		String url = intent.getStringExtra("url");
		MyWorkThreadQueue.AddTask(new ShowDetailThread(new MyHandler(this), url));
	}
	
	private static class MyHandler extends Handler {
		private final WeakReference<ShowDetailActivity> mcontext;

		public MyHandler(ShowDetailActivity context) {
			mcontext = new WeakReference<ShowDetailActivity>(context);
		}

		@Override
		public void handleMessage(Message msg) {

			int Flag = msg.what;
			switch (Flag) {
			case 0:
				String errmsg = (String) msg.getData().getSerializable(
						"ErroMsg");
				((ShowDetailActivity) mcontext.get()).showTip(errmsg);
				break;
			case 1:
				((ShowDetailActivity) mcontext.get()).doShow();
				break;
			default:

				break;
			}

		}
	}
	
	private void showTip(String errmsg) {
		// TODO Auto-generated method stub
		Toast.makeText(this, errmsg, Toast.LENGTH_LONG).show();
	}

	private void doShow() {
		// TODO Auto-generated method stub
		picview.setImageBitmap(StaticDataPackage.detailbitmap); 
	}
	

}
