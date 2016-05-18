package com.just.weike.Service.impl.Thread;

import android.os.Handler;
import android.os.Message;

import com.just.weike.Dao.bean.HttpTheadConfigBean;
import com.just.weike.Service.impl.Thread.Templet.HttpThreadTemplet;
import com.just.weike.utils.StaticDataPackage;

public class ShowDetailThread extends HttpThreadTemplet {
	
	private String url;
	
	public ShowDetailThread(Handler handler,String url) {
		super(handler);
		// TODO Auto-generated constructor stub
		this.url = url;
	}

	@Override
	protected void OnRun() throws Exception {
		// TODO Auto-generated method stub
		StaticDataPackage.detailbitmap = httpService.getImage(url);
		Message msg = new Message();
		msg.what = 1;
		handler.sendMessage(msg);
	}

	@Override
	protected HttpTheadConfigBean SetConfig() {
		HttpTheadConfigBean configBean = new HttpTheadConfigBean
				(false, 0, "连接超时", "加载超时","加载错误");
		return configBean; 
	}

}
