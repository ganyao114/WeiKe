package com.gy.myframework.Model.net.http.cache;

import com.gy.myframework.Model.net.http.entity.HttpToken;
import com.gy.myframework.Service.cache.IDiskCache;

/**
 * Created by gy on 2016/4/2.
 */
public class HttpDiskCache implements IDiskCache<HttpToken,String>{
    @Override
    public boolean put(HttpToken key, String value) {
        return false;
    }

    @Override
    public String get(HttpToken key) {
        return null;
    }

    @Override
    public void clear() {

    }
}
