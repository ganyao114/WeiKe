package com.just.weike.Service.impl.Thread;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.conn.ConnectTimeoutException;

import com.just.weike.Dao.IHttpService;
import com.just.weike.Service.ServiceRulesException;
import com.just.weike.utils.ExceptionContent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class GetSpinnerItemsThread implements Runnable {
	 private Handler handler;
	 private IHttpService userService;
	 private int id;
	 private String kind;
	 private String value;
	public GetSpinnerItemsThread(String kind,String value,int id,
			IHttpService userService,Handler handler) {
		this.kind = kind;
		this.value = value;
		this.id = id;
		this.userService = userService;
		this.kind = kind;
		this.handler = handler;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			List<String> items = new ArrayList<String>();
			items =  userService.GetSpinnerItems(kind,value);
			Message msg = new Message();
			msg.what=1;
			Bundle data= new Bundle();
			data.putStringArrayList("items", (ArrayList<String>) items);
			data.putInt("id", id);
			msg.setData(data);
			handler.sendMessage(msg);
		} catch (ServiceRulesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Message msg = new Message();
			Bundle data= new Bundle();
			data.putSerializable("ErroMsg",e.getMessage());
			msg.setData(data);
			handler.sendMessage(msg);
		}catch (ConnectTimeoutException e) {
			e.printStackTrace();
			Message msg = new Message();
			Bundle data= new Bundle();
			data.putSerializable("ErroMsg",ExceptionContent.MSG_REGIST_CONNECT_TIMEOUT);
			msg.setData(data);
			handler.sendMessage(msg);
		}catch (SocketTimeoutException e) {
			e.printStackTrace();
			Message msg = new Message();
			Bundle data= new Bundle();
			data.putSerializable("ErroMsg",ExceptionContent.MSG_REGIST_CONNECT_TIMEOUT);
			msg.setData(data);
			handler.sendMessage(msg);
		}
		 catch (Exception e) {
			e.printStackTrace();
			Message msg = new Message();
			Bundle data= new Bundle();
			data.putSerializable("ErroMsg",ExceptionContent.MSG_REGIST_FAILED);
			msg.setData(data);
			handler.sendMessage(msg);
		}
	}

}
