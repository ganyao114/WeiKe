package com.just.weike.Service.impl.Thread.Templet;
/**
 * 子线程模板,二次封装Runnable接口，封装异常处理
 */
import java.net.SocketTimeoutException;

import org.apache.http.conn.ConnectTimeoutException;

import com.just.weike.Dao.IHttpService;
import com.just.weike.Dao.bean.HttpTheadConfigBean;
import com.just.weike.Dao.impl.UserServiceiml;
import com.just.weike.Service.ServiceRulesException;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

@SuppressWarnings("deprecation")
public abstract class HttpThreadTemplet implements Runnable {
	
	protected Handler handler;
	protected IHttpService httpService;
	private boolean isLoop = false;
	private int tickTime = 0;
	private HttpTheadConfigBean configBean;
	
	public HttpThreadTemplet(Handler handler) {
		// TODO Auto-generated constructor stub
		this.handler = handler;
		httpService = UserServiceiml.getInstance();
		configBean = SetConfig();
		isLoop = configBean.isLoop;
		tickTime = configBean.tickTime;
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (isLoop) {
			while(isLoop)
			{	
				doRun();
				try {
						Thread.currentThread().sleep(tickTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}else {
			doRun();
		}
		
	}

	private void doRun()
	{
		try {
			OnRun();
		} catch (ServiceRulesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Message msg = new Message();
			Bundle data = new Bundle();
			data.putSerializable("ErroMsg", e.getMessage());
			msg.setData(data);
			handler.sendMessage(msg);
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
			Message msg = new Message();
			Bundle data = new Bundle();
			data.putSerializable("ErroMsg",
					configBean.ErrorMsgLevel1);
			msg.setData(data);
			handler.sendMessage(msg);
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			Message msg = new Message();
			Bundle data = new Bundle();
			data.putSerializable("ErroMsg",
					configBean.ErrorMsgLevel2);
			msg.setData(data);
			handler.sendMessage(msg);
		} catch (Exception e) {
			e.printStackTrace();
			Message msg = new Message();
			Bundle data = new Bundle();
			data.putSerializable("ErroMsg", configBean.ErrorMsgLevel3);
			msg.setData(data);
			handler.sendMessage(msg);
		}
	}
	
	public synchronized void StopThread(){
		isLoop = false;
	}
	
	protected abstract void OnRun() throws Exception;
	
	protected abstract HttpTheadConfigBean SetConfig();
	

}
