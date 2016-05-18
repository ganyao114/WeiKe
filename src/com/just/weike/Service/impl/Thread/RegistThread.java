package com.just.weike.Service.impl.Thread;

import com.just.weike.Dao.bean.HttpTheadConfigBean;
import com.just.weike.Dao.bean.RegistBean;
import com.just.weike.Service.impl.Thread.Templet.HttpThreadTemplet;
import com.just.weike.utils.ExceptionContent;

import android.os.Handler;

public class RegistThread extends HttpThreadTemplet {
	
	private RegistBean registBean;
	
	public RegistThread(Handler handler,RegistBean registBean) {
		// TODO Auto-generated constructor stub
		super(handler);
		this.registBean = registBean;
	}

	
	@Override
	protected void OnRun() throws Exception {
		// TODO Auto-generated method stub
		httpService.regist(registBean);
		handler.sendEmptyMessage(ExceptionContent.FLAG_REGIST_SUCCESS);
	}

	@Override
	protected HttpTheadConfigBean SetConfig() {
		// TODO Auto-generated method stub
		HttpTheadConfigBean configBean = new HttpTheadConfigBean
				(false, 0, ExceptionContent.MSG_REGIST_CONNECT_TIMEOUT, ExceptionContent.MSG_REGIST_CONNECT_TIMEOUT,ExceptionContent.MEG_REGIST_ERROR);
		return configBean;
	}

}
