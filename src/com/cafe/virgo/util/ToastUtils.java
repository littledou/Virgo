package com.cafe.virgo.util;

import android.widget.Toast;

import com.cafe.virgo.BaseApplication;
import com.cafe.virgo.custontoast.CustomToast;

/**
 *	Toast 工具类
 */
public class ToastUtils {

	protected static Toast mCurrentToast; 
	public static void showShortToast(String string){
		showToast(string, Toast.LENGTH_SHORT);
	}
	public static void showShortToast(int id){
		showToast(BaseApplication.getInstence().getString(id), Toast.LENGTH_SHORT);
	}
	public static void showLongToast(String string){
		showToast(string, Toast.LENGTH_LONG);
	}
	private static void showToast(String string,int toast){
		if(null != mCurrentToast){
			mCurrentToast.cancel();
		}
		try {
			mCurrentToast = CustomToast.makeText(BaseApplication.getInstence(), string, toast);
			mCurrentToast.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
