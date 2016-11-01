package com.gy.just.VoltageMonitor.Model.Net;

import android.os.Message;

import com.gy.just.VoltageMonitor.Model.Bean.NewsListPojo;
import com.gy.myframework.IOC.Model.net.http.annotation.InjectHttp;
import com.gy.myframework.IOC.Model.net.http.entity.HttpConnectMode;
import com.gy.myframework.IOC.Model.net.http.entity.HttpRunMode;
import com.gy.myframework.IOC.Model.net.http.impl.HttpInjectUtil;
import com.gy.myframework.MVP.Model.BaseHttpModel;

import java.util.List;

/**
 * Created by gy on 2016/4/22.
 */
public class GetNewsModel extends BaseHttpModel<List<NewsListPojo>> implements IGetNewsModel{

    public GetNewsModel() {
        super();
    }

    @InjectHttp(url = "",mode = HttpRunMode.Async,connectmode = HttpConnectMode.Post)
    public void getNews(Message message){
        String resRaw = (String) HttpInjectUtil.GetResult(message);
        List<NewsListPojo> list = parseJSON(resRaw);
        onResult(list);
    }

    private List<NewsListPojo> parseJSON(String resRaw) {
        return null;
    }

    public void getNews(){

    }
}
