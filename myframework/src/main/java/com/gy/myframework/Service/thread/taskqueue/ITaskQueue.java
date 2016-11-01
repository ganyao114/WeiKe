package com.gy.myframework.Service.thread.taskqueue;

import com.gy.myframework.Service.thread.taskqueue.entity.TaskPrio;

/**
 * Created by gy on 2016/1/27.
 */
public interface ITaskQueue {
    public boolean addTask(Runnable runnable, TaskPrio prio);
    public boolean runTask();
    public boolean removeTask(Runnable runnable);
    public boolean clearTaskQueue();
}
