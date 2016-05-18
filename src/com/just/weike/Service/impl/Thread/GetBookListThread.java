package com.just.weike.Service.impl.Thread;

import java.io.Serializable;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.just.weike.Dao.bean.Books;
import com.just.weike.Dao.bean.HttpTheadConfigBean;
import com.just.weike.Dao.bean.PagerViewBean;
import com.just.weike.Service.ServiceRulesException;
import com.just.weike.Service.impl.Thread.Templet.HttpThreadTemplet;
import com.just.weike.test.TestDao;
import com.just.weike.utils.StaticDataPackage;

public class GetBookListThread extends HttpThreadTemplet {
	
	public GetBookListThread(Handler handler) {
		super(handler);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void OnRun() throws Exception {
		// TODO Auto-generated method stub
		if(StaticDataPackage.LoginName == null)
			throw new ServiceRulesException("未登录");
		List<Books> books = httpService.GetBooksList(StaticDataPackage.LoginName);
		PagerViewBean pagerViewBean = TestDao.getPagerViewBean();//httpService.getPageView(StaticDataPackage.LoginName);
		Message msg = new Message();
		msg.what = 1;
		Bundle data = new Bundle();
		data.putSerializable("books", (Serializable) books);
		data.putSerializable("pageview", pagerViewBean);
		msg.setData(data);
		handler.sendMessage(msg);
	}

	@Override
	protected HttpTheadConfigBean SetConfig() {
		HttpTheadConfigBean configBean = new HttpTheadConfigBean
				(false, 0, "连接超时", "加载超时","加载错误");
		return configBean; 
	}

}
