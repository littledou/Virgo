package com.cafe.virgo.util;

import android.os.Handler;
import android.os.Looper;

/**
 * new线程管理类
 */
public final class ThreadUtils {
	
	private static Handler mHandler;
	
    public static Handler getMainThreadHandler() {
        if (mHandler == null) {
        	mHandler = new Handler(Looper.getMainLooper());
        }
        return mHandler;
    }
}
