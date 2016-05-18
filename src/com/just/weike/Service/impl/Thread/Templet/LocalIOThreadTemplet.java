package com.just.weike.Service.impl.Thread.Templet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketTimeoutException;

import org.apache.http.conn.ConnectTimeoutException;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.just.weike.Dao.bean.HttpTheadConfigBean;
import com.just.weike.Service.ServiceRulesException;

public abstract class LocalIOThreadTemplet implements Runnable {

	protected Handler handler;
	private boolean isLoop = false;
	private int tickTime = 0;
	private HttpTheadConfigBean configBean;
	
	public LocalIOThreadTemplet(Handler handler) {
		// TODO Auto-generated constructor stub
		this.handler = handler;
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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Message msg = new Message();
			Bundle data = new Bundle();
			data.putSerializable("ErroMsg",
					configBean.ErrorMsgLevel1);
			msg.setData(data);
			handler.sendMessage(msg);
		} catch (IOException e) {
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
