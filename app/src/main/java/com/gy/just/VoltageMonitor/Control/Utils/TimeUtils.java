package com.gy.just.VoltageMonitor.Control.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by gy on 2016/4/29.
 */
public class TimeUtils {
    public static String getCurrentDateStr(){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }
    public static String getDateStr(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

    public static String getTimeStr(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("HHmm");
        return sdf.format(date);
    }

    public static String getDateMonthStr(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
        return sdf.format(date);
    }
    public static String getDateStrCn(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR)+"年"+(cal.get(Calendar.MONTH)+1)+"月"+cal.get(Calendar.DAY_OF_MONTH)+"日";
    }
}
