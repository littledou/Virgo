package com.cafe.virgo.corethread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;

/**
 * 线程管理
 */
public class AsyncTaskExecutor {

	private static ThreadPoolExecutor threadPoolExecutor ;
	static{
		threadPoolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 2, Runtime
				.getRuntime().availableProcessors() * 3, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
	}
	@SuppressLint("NewApi") 
	public static <Params, Progress, Result> AsyncTask<Params, Progress, Result> 
	executeConcurrently(AsyncTask<Params, Progress, Result> task,
			Params... params) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			try {
				task.executeOnExecutor(threadPoolExecutor, params);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			task.execute(params);
		}
		return task;
	}
	/**
	 * 关闭线程池
	 */
	public static void shutdown() {
		if (threadPoolExecutor == null || threadPoolExecutor.isShutdown()) {
			return;
		}
		threadPoolExecutor.shutdown();
	}
}
