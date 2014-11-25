package com.cafe.virgo.util;

import android.util.Log;

/**
 *日志管理类 
 */
public class DLogUtils {

	// 日志开关
	private static boolean switches = true;

	public static void syso(Object log) {
		if (switches) {
			System.out.println(log);
		}
	}
	public static void i(String tag,String msg){
		if (switches) {
			Log.i(tag, msg);
		}
	}
	public static void w(String tag,String msg){
		if (switches) {
			Log.w(tag, msg);
		}
	}
	public static void e(String tag,String msg){
		if (switches) {
			Log.e(tag, msg);
		}
	}
}
