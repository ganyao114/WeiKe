package com.just.weike.Service.impl.Thread.Pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WorkThreadPool {
	private final static int POOL_SIZE = 4;
	private final static int MAX_POOL_SIZE = 6;
	private final static int KEEP_ALIVE_TIME = 4;
	private final Executor mExecutor;

	public WorkThreadPool() {
		ThreadFactory factory = new WorkThreadFactory("thread-pool",
				android.os.Process.THREAD_PRIORITY_BACKGROUND);
		BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<Runnable>();
		mExecutor = new ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE,
				KEEP_ALIVE_TIME, TimeUnit.SECONDS, workQueue, factory);
	}

	public void submit(Runnable command) {
		mExecutor.execute(command);
	}

}
