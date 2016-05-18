package com.just.weike.Service.impl.Thread;

/*
import com.just.AudioRecorder.Dao.Bean.HttpTheadConfigBean;
import com.just.AudioRecorder.Dao.Bean.RequestBean;
import com.just.AudioRecorder.Service.impl.Thread.Templet.HttpThreadTemplet;
import com.just.AudioRecorder.utils.ExceptionContent;
import com.just.AudioRecorder.utils.StaticDataPackage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class GetRequestThread extends HttpThreadTemplet {
	private RequestBean requestBean;
	public GetRequestThread(Handler handler) {
		// TODO Auto-generated constructor stub
		super(handler);
	}


	@Override
	protected void OnRun() throws Exception {
		// TODO Auto-generated method stub
		requestBean = httpService.getRequest(StaticDataPackage.UserName);
		Message msg = new Message();
		msg.what = 1;
		Bundle bundle = new Bundle();
		bundle.putParcelable("RequestBean", requestBean);
		msg.setData(bundle);
		handler.sendMessage(msg);
	}

	@Override
	protected HttpTheadConfigBean SetConfig() {
		// TODO Auto-generated method stub
		HttpTheadConfigBean configBean = new HttpTheadConfigBean
				(true, 2000, ExceptionContent.MSG_REQUEST_ERROR, ExceptionContent.MSG_REQUEST_ERROR, ExceptionContent.UNKOWN_ERROR);
		return configBean;
	}

}

*/
