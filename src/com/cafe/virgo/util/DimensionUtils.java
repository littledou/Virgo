package com.cafe.virgo.util;

import java.lang.reflect.Field;

import com.cafe.virgo.BaseApplication;

import android.content.Context;

/**
 * 尺寸适配工具类
 */
public class DimensionUtils {


	public static int getDimensionSizeW(int target){
		return target*(BaseApplication.getInstence().scrn.ScrnW_px)/2048;
	}
	public static int getDimensionSizeH(int target){
		return target*(BaseApplication.getInstence().scrn.ScrnH_px)/1496;
	}

	//获取通知栏高度
	public static int getStatusBarHeight(Context context){
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0,statusBarHeight = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarHeight =  context.getResources().getDimensionPixelSize(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusBarHeight;
	}
}
