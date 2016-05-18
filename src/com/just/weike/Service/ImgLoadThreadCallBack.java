package com.just.weike.Service;

import android.graphics.Bitmap;

import com.just.weike.Dao.bean.PhotoToLoad;
import com.just.weike.Service.impl.Imgloader.ImageLoader;

public interface ImgLoadThreadCallBack {
	public ImageLoader getInstance();
	public void threadCall(PhotoToLoad photoToLoad,Bitmap bitmap);
}
