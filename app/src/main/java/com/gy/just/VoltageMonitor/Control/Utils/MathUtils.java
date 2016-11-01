package com.gy.just.VoltageMonitor.Control.Utils;

import java.text.NumberFormat;

/**
 * Created by gy on 2016/4/27.
 */
public class MathUtils {
    public static String getFloatLastTwo(int a,int b){
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format((float)a/(float)b*100f);
        return result+"%";
    }

    public static float convert(float value){
        long l1 = Math.round(value * 100); //四舍五入
        float ret = (float) (l1/100.0); //注意:使用 100.0 而不是 100
        return ret;
    }
    public static float convert4(float value){
        long l1 = Math.round(value*10000); //四舍五入
        float ret = (float) (l1/10000.0); //注意:使用 100.0 而不是 100
        return ret;
    }
    public static int per2int(String s){
        String str = s.replace("%", "");
        float f = Float.parseFloat(str);
        return (int) f;
    }

}
