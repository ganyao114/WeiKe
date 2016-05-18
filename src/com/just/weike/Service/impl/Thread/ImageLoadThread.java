package com.just.weike.Service.impl.Thread;

import java.io.File;

import android.graphics.Bitmap;
import android.os.Handler;

import com.just.weike.Dao.bean.HttpTheadConfigBean;
import com.just.weike.Dao.bean.PhotoToLoad;
import com.just.weike.Service.ImgLoadThreadCallBack;
import com.just.weike.Service.impl.Imgloader.ImageLoader;
import com.just.weike.Service.impl.Thread.Templet.HttpThreadTemplet;

public class ImageLoadThread extends HttpThreadTemplet {
	
	private PhotoToLoad photoToLoad;
	private ImgLoadThreadCallBack imgReusedCallBack;
	
	public ImageLoadThread(Handler handler,PhotoToLoad photoToLoad,ImgLoadThreadCallBack imgReusedCallBack) {
		super(handler);
		// TODO Auto-generated constructor stub
		this.photoToLoad = photoToLoad;
		this.imgReusedCallBack = imgReusedCallBack;
	}

	@Override
	protected void OnRun() throws Exception {
		// TODO Auto-generated method stub
		
		if(imgReusedCallBack.getInstance().imageViewReused(photoToLoad))
			return;
		File file = imgReusedCallBack.getInstance().
				fileCache.getFile(photoToLoad.url);
		Bitmap bitmap = ImageLoader
				.decodeFile(file);
		if(bitmap != null){
			imgReusedCallBack.threadCall(photoToLoad, bitmap);
		}
		else{
			bitmap = httpService.getBitmap(photoToLoad.url,file);
			imgReusedCallBack.threadCall(photoToLoad, bitmap);
		}
	}

	@Override
	protected HttpTheadConfigBean SetConfig() {
		// TODO Auto-generated method stub
		HttpTheadConfigBean configBean = new HttpTheadConfigBean
				(false, 0, "连接超时", "加载超时","加载错误");
		return configBean; 
	}

}
