package com.just.weike.Service.impl.Thread;

import android.os.Handler;

import com.just.weike.Dao.bean.HttpTheadConfigBean;
import com.just.weike.Dao.bean.TokeBean;
import com.just.weike.Service.impl.Thread.Templet.HttpThreadTemplet;
import com.just.weike.utils.StaticDataPackage;

public class AddTokeThread extends HttpThreadTemplet {
		
	private TokeBean toke;
	
	public AddTokeThread(Handler handler,TokeBean toke) {
		super(handler);
		// TODO Auto-generated constructor stub
		this.toke = toke;
	}

	@Override
	protected void OnRun() throws Exception {
		// TODO Auto-generated method stub
		httpService.SendToke(StaticDataPackage.LoginName, toke);
		handler.sendEmptyMessage(1);
	}

	@Override
	protected HttpTheadConfigBean SetConfig() {
		// TODO Auto-generated method stub
		HttpTheadConfigBean configBean = new HttpTheadConfigBean
				(false, 0, "连接超时", "操作超时","操作错误");
		return configBean; 
	}

}
