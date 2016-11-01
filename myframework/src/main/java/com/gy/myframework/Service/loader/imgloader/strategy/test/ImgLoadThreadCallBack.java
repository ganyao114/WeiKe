package com.gy.myframework.Service.loader.imgloader.strategy.test;

import android.graphics.Bitmap;

import com.gy.myframework.Service.loader.imgloader.strategy.mystrategy.entity.PhotoToLoad;

public interface ImgLoadThreadCallBack {
	public ImageLoader getInstance();
	public void threadCall(PhotoToLoad photoToLoad, Bitmap bitmap);
}
