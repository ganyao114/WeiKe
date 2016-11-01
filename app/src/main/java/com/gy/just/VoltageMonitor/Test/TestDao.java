package com.gy.just.VoltageMonitor.Test;

import com.gy.just.VoltageMonitor.Model.Bean.CardPojo.DaliCardPojo;
import com.gy.just.VoltageMonitor.Model.Bean.DailinfoPojo;
import com.gy.just.VoltageMonitor.Model.Bean.NewsListPojo;
import com.gy.just.VoltageMonitor.Model.Bean.NotifyCardPojo;
import com.gy.myframework.UI.customwidget.mypageview.PagerViewBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gy on 2016/4/13.
 */
public class TestDao {
    public static PagerViewBean getPagerViewBean()
    {
        PagerViewBean bean = new PagerViewBean();
        bean.setTitle(new String[]{"公告1","公告2","公告3","公告4","公告5"});
        bean.setImgsrc(new String[]{"http://down1.sucaitianxia.com/ppt/19/ppt5045.jpg","http://pic.58pic.com/58pic/16/84/10/558PICc58PICQ9N_1024.jpg"
                ,"http://www.yanj.cn/upload/editor/20140120160119_13197.png","http://www.yanj.cn/upload/store/goods/144/144_869c84546a5fd9ca77cbf016c94f151e.jpg_max.jpg","http://pic25.nipic.com/20121209/9252150_194258033000_2.jpg"});
        return bean;
    }
    public static List<NewsListPojo> getBews(){
        List<NewsListPojo> listPojos = new ArrayList<NewsListPojo>();
        for (int i = 0;i < 10;i++){
            NewsListPojo pojo = new NewsListPojo();
            pojo.setDate("2016/4/13 8:47");
            pojo.setContent("我也不知道要写什么");
            pojo.setCoverurl("http://down1.sucaitianxia.com/ppt/19/ppt5045.jpg");
            pojo.setTitle("标题");
            pojo.setSubscriptCounts("10人已订阅");
            pojo.setSubscriptButton("空");
            listPojos.add(pojo);
        }
        return listPojos;
    }


    public static List<DaliCardPojo> getInfosCard() {
        List<DaliCardPojo> list = new ArrayList<DaliCardPojo>();
        for (int i = 0;i < 15;i++){
            DaliCardPojo dailinfoPojo = new DaliCardPojo();
            dailinfoPojo.setUnit("A类仪表");
            dailinfoPojo.setPer1(50);
            dailinfoPojo.setPer1_txt("50%");
            dailinfoPojo.setPre2(30);
            dailinfoPojo.setPre2_txt("30%");
            dailinfoPojo.setPre3(70);
            dailinfoPojo.setPre3_txt("70%");
            dailinfoPojo.setPer_title1("50");
            dailinfoPojo.setPer_title2("30");
            dailinfoPojo.setPer_title3("70");
            list.add(dailinfoPojo);
        }
        return list;
    }

    public static List<EmptyPojo> getEmptyList() {
        List<EmptyPojo> list = new ArrayList<EmptyPojo>();
        for (int i = 0;i < 30;i++){
            EmptyPojo dailinfoPojo = new EmptyPojo();
            list.add(dailinfoPojo);
        }
        return list;
    }

    public static List<DailinfoPojo> getEmptyList2() {
        List<DailinfoPojo> list = new ArrayList<DailinfoPojo>();
        for (int i = 0;i < 30;i++){
            DailinfoPojo dailinfoPojo = new DailinfoPojo();
//            dailinfoPojo.setRn("dwdwa");
            dailinfoPojo.setVol("dawdwad");
            dailinfoPojo.setTime("ewawad");
            list.add(dailinfoPojo);
        }
        return list;
    }

    public static List<EmptyPojo2> getEmpty2List() {
        List<EmptyPojo2> list = new ArrayList<EmptyPojo2>();
        for (int i = 0;i < 30;i++){
            EmptyPojo2 dailinfoPojo = new EmptyPojo2();
            list.add(dailinfoPojo);
        }
        return list;
    }


    public static List<NotifyCardPojo> getNotifies() {
        List<NotifyCardPojo> list = new ArrayList<NotifyCardPojo>();
        for (int i = 0;i < 8;i++){
            NotifyCardPojo dailinfoPojo = new NotifyCardPojo();
            dailinfoPojo.setTitle("这是一则通知");
            dailinfoPojo.setCoverurl("http://img.taopic.com/uploads/allimg/130510/235092-1305101P63184.jpg");
            dailinfoPojo.setContent("首个“中国航天日”到来之际，中国首位航天员杨利伟在接受记者采访时说，一脸坚定地说：“如果有机会，我想去月球。”杨利伟至今记得，当年回到地球，他的嘴角还带着刚擦完又渗出的血。他回忆说，当时飞船着地后又弹了起来，二次接地时没有缓冲，落地比较重。");
            list.add(dailinfoPojo);
        }
        return list;
    }

    public static String getJSON(){
        return "{data:[{\"yb_id\":\"0014920346\",\"yb_name\":\"测试4846\",\"yb_address\":\"测试4846\",\"yb_ratio\":0,\"yb_unit\":\"V\",\"yb_mobileNo\":\"18100004846\",\"yb_uniqueCode\":\"07M00000000004846\",\"yb_termNo\":\"1\",\"yb_longitude\":0,\"yb_latitude\":0,\"yb_height\":0,\"yb_lowerLimit\":198,\"yb_upperLimit\":235.4,\"yb_pointTypeId\":\"1212\",\"pt_name\":\"B类\",\"yb_pointType2Id\":\"24\",\"pt2_name\":\"城网\",\"yb_voltageLevelCode\":\"2\",\"v_name\":\"0.22KV\",\"yb_memo\":null,\"yb_orgCode\":\"010101\",\"o_name\":\"TL\",\"yb_type\":\"DT8\",\"ybt_name\":\"DT8\",\"yb_status\":0,\"yb_createTime\":{\"nanos\":337000000,\"time\":1461738789337,\"minutes\":33,\"seconds\":9,\"hours\":14,\"month\":3,\"year\":116,\"timezoneOffset\":-480,\"day\":3,\"date\":27},\"yb_updateTime\":{\"nanos\":337000000,\"time\":1461738789337,\"minutes\":33,\"seconds\":9,\"hours\":14,\"month\":3,\"year\":116,\"timezoneOffset\":-480,\"day\":3,\"date\":27}}],meta:{success:true,msg:'',total:1}}";
    }
}
