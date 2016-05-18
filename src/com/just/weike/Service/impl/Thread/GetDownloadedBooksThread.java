package com.just.weike.Service.impl.Thread;

import android.os.Handler;

import com.just.weike.Dao.bean.HttpTheadConfigBean;
import com.just.weike.Service.impl.Thread.Templet.LocalIOThreadTemplet;

public class GetDownloadedBooksThread extends LocalIOThreadTemplet {

	public GetDownloadedBooksThread(Handler handler) {
		super(handler);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void OnRun() throws Exception {
		// TODO Auto-generated method stub
		//msg.what = 1;
	}

	@Override
	protected HttpTheadConfigBean SetConfig() {
		// TODO Auto-generated method stub
		HttpTheadConfigBean configBean = new HttpTheadConfigBean
				(false, 0, "文件不存在", "IO异常","未知异常");
		return configBean; 
	}

}
