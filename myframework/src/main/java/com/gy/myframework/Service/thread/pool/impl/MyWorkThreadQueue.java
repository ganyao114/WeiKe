package com.gy.myframework.Service.thread.pool.impl;

import com.gy.myframework.Service.thread.pool.control.ThreadPoolControl;
import com.gy.myframework.Service.thread.pool.strategy.ExecutorThreadPool;

public class MyWorkThreadQueue {

    private static ThreadPoolControl mythreadpool;

    private static void initPool() {
        mythreadpool = new ThreadPoolControl(new ExecutorThreadPool());
        mythreadpool.poolstart();
    }


    public static void AddTask(Runnable r) {
        if (mythreadpool == null)
            initPool();
        mythreadpool.submit(r);
    }

}
