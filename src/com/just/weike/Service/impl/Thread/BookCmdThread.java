package com.just.weike.Service.impl.Thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.just.weike.Dao.bean.Books;
import com.just.weike.Dao.bean.HttpTheadConfigBean;
import com.just.weike.Service.ServiceRulesException;
import com.just.weike.Service.impl.Thread.Templet.HttpThreadTemplet;
import com.just.weike.utils.StaticDataPackage;

public class BookCmdThread extends HttpThreadTemplet{

	private Books book;
	private String cmd;

	public BookCmdThread(Handler handler,Books book,String cmd) {
		super(handler);
		this.book = book;
		this.cmd = cmd;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void OnRun() throws Exception {
		// TODO Auto-generated method stub
		if(StaticDataPackage.LoginName == null)
			throw new ServiceRulesException("未登录");
		String result = httpService.BookCmd(StaticDataPackage.LoginName,book,cmd);
		Message msg = new Message();
		msg.what = 1;
		Bundle data = new Bundle();
		data.putString("result", result);
		msg.setData(data);
		handler.sendMessage(msg);
	}

	@Override
	protected HttpTheadConfigBean SetConfig() {
		// TODO Auto-generated method stub
		HttpTheadConfigBean configBean = new HttpTheadConfigBean
				(false, 0, "连接超时", "操作超时","操作错误");
		return configBean; 
	}

}
