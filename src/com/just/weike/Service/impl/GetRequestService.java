package com.just.weike.Service.impl;

import java.lang.ref.WeakReference;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

/*public class GetRequestService extends Service implements IGetRequestService{
	
	private IBinder myBinder;
	private Thread thread;
	private Handler handler;
	protected Context mContext;
	private IUpdateUI iupdateui;
	private GetRequestThread getrquestThread;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		myBinder = new MyBinder2();
		handler = new MyHandler(this);
		
	}
	
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return myBinder;
	}

	public class MyBinder2 extends Binder
	{
		public IGetRequestService getService()
		{
			return GetRequestService.this;
		}
	}
	
	private static class MyHandler extends Handler{
		private final WeakReference<GetRequestService> mcontext;
		public MyHandler(GetRequestService context) {
			// TODO Auto-generated constructor stub
			this.mcontext = new WeakReference<GetRequestService>(context);
		}
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			int Flag = msg.what;
			switch (Flag) {
			case 0:
				String errmsg = (String) msg.getData().getSerializable(
						"ErroMsg");
				((GetRequestService) mcontext.get()).showTip(errmsg);
				break;
			case 1:
				RequestBean requestBean = msg.getData().getParcelable("RequestBean");
				((GetRequestService) mcontext.get()).dailRequest(requestBean);
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
	
	private void dailRequest(RequestBean requestBean)
	{	
		if (requestBean.Guests != null) {
			iupdateui.RefreshGuestList(requestBean.Guests);
		}
		if (requestBean.Msgs != null) {
			iupdateui.RefreshMsgs(requestBean.Msgs);
		}
		if (requestBean.request != null) {
			iupdateui.OnReceiveRequest(requestBean.request);
		}
		if (requestBean.stoped.equals("stoped")) {
			iupdateui.OnStopRecorder();
		}
	}

	

	@Override
	public void startRequest(Context context,IUpdateUI i) {
		// TODO Auto-generated method stub
		iupdateui = i;
		mContext = context;
		getrquestThread = new GetRequestThread(handler);
		thread = new Thread(getrquestThread);
		thread.start();
	}

	@Override
	public void stopRequest() {
		// TODO Auto-generated method stub
		if (getrquestThread != null) 
		getrquestThread.StopThread();
	}

	
	
} */
