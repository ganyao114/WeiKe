package com.just.weike.Service.impl.Thread.Pool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import android.os.Process;

public class WorkThreadFactory implements ThreadFactory {
	private final String mName;
	private final int mPriority;
	private final AtomicInteger mNumber = new AtomicInteger();

	public WorkThreadFactory(String name, int priority) {
		mName = name;
		mPriority = priority;
	}

	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r, mName + "-" + mNumber.getAndIncrement()) {
			@Override
			public void run() {
				Process.setThreadPriority(mPriority);
				super.run();
			}
		};
	}
}
