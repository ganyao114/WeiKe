package com.just.weike.Service.impl.Thread;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;

import com.just.weike.Dao.bean.HttpTheadConfigBean;
import com.just.weike.Dao.bean.PositionBean;
import com.just.weike.Service.impl.Thread.Templet.HttpThreadTemplet;
import com.just.weike.test.TestDao;
import com.just.weike.utils.ExceptionContent;

public class GetPositionItemsThread extends HttpThreadTemplet {
	
	private String colume;
	private String key;
	
	public GetPositionItemsThread(Handler handler,String colume,String key) {
		super(handler);
		// TODO Auto-generated constructor stub
		this.colume = colume;
		this.key = key;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void OnRun() throws Exception {
		// TODO Auto-generated method stub
		List<PositionBean> list = TestDao.getPositionBeans(colume);//httpService.getPositionItems(colume, key);
		if(list == null)
			throw new Exception();
		Message msg = new Message();
		msg.what = 1;
		Bundle bundle = new Bundle();
		bundle.putSerializable("positionbean",  (Serializable) list);
		msg.setData(bundle);
		handler.sendMessage(msg);
	}

	@Override
	protected HttpTheadConfigBean SetConfig() {
		// TODO Auto-generated method stub
		HttpTheadConfigBean configBean = new HttpTheadConfigBean
				(false, 0, ExceptionContent.MSG_LINK_ERROR, ExceptionContent.MSG_LOGIN_TIMEOUT,ExceptionContent.MEG_LOGIN_ERROR);
		return configBean;
	}

}
