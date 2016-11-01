package com.gy.myframework.Service.loader.imgloader.strategy.mystrategy;

import android.graphics.Bitmap;

import com.gy.myframework.Service.loader.imgloader.strategy.mystrategy.entity.PhotoToLoad;
import com.gy.myframework.Service.loader.imgloader.strategy.mystrategy.impl.ImageLoader;

public interface ImgLoadThreadCallBack {
    public ImageLoader getInstance();

    public void threadCall(PhotoToLoad photoToLoad, Bitmap bitmap);
}
