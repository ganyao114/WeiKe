package com.just.weike.Service.impl.Thread.Pool;

public class MyWorkThreadQueue {
	private static WorkThreadPool mythreadpool = new WorkThreadPool();
	public static void AddTask(Runnable task)
	{
		mythreadpool.submit(task);
	}
}
