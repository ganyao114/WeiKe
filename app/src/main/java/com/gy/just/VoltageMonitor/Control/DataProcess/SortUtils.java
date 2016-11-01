package com.gy.just.VoltageMonitor.Control.DataProcess;

import java.util.List;

/**
 * Created by gy on 2016/4/24.
 */
public class SortUtils implements ISort{
    private static ISort sort;

    public static ISort getSort(){
        synchronized (SortUtils.class){
            if (sort == null)
                sort = new SortUtils();
        }
        return sort;
    }

    @Override
    public List sortListByUserId(List list) {
        return null;
    }
}
