package com.cafe.virgo;

import java.lang.reflect.Field;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Share Preferences
 */
public class AppPreferences {

	public String isGuide;

	public AppPreferences(Context ctx) {
		readLocalProperties(ctx); 
	}
	private static AppPreferences instance;
	public final static class Constant {
		public final static String COMMON_PREFS_NAME = "virgo_user"; // 存储文件名称
	}
	public final static AppPreferences getInstance(Context ctx) {
		return instance == null ? instance = new AppPreferences(ctx) : instance;
	}
	/** 属性值本地数据持久化 */
	public void saveInstance(Context context) {
		Editor editor = context.getSharedPreferences(
				Constant.COMMON_PREFS_NAME, Context.MODE_PRIVATE).edit();
		try {
			for (Field field : AppPreferences.class.getDeclaredFields()) {

				if (field.getType() == String.class) {
					editor.putString(field.getName(),
							String.valueOf(field.get(this)));
				} else if (field.getType() == Integer.TYPE) {
					editor.putInt(field.getName(),
							Integer.valueOf(field.get(this).toString()));
				} else if (field.getType() == Float.TYPE) {
					editor.putFloat(field.getName(),
							Float.valueOf(field.get(this).toString()));
				} else if (field.getType() == Long.TYPE) {
					editor.putLong(field.getName(),
							Long.valueOf(field.get(this).toString()));
				} else if (field.getType() == Boolean.TYPE) {
					editor.putBoolean(field.getName(),
							Boolean.valueOf(field.get(this).toString()));
				} else {
					// NOTHING TO HERE. 仅仅保存，String, int, float, long, boolean
					// 类型的数据
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		editor.commit();// 提交更新
	}

	public void readLocalProperties(Context context) {
		SharedPreferences prefs = context.getSharedPreferences(
				Constant.COMMON_PREFS_NAME, Context.MODE_PRIVATE);
		try {
			for (Field field : AppPreferences.class.getDeclaredFields()) {
				if (field.getType() == String.class) {
					field.set(this, prefs.getString(field.getName(),""));
				} else if (field.getType() == Integer.TYPE) {
					field.set(this, prefs.getInt(field.getName(), -1));
				} else if (field.getType() == Float.TYPE) {
					field.set(this, prefs.getFloat(field.getName(), -1));
				} else if (field.getType() == Long.TYPE) {
					field.set(this, prefs.getLong(field.getName(), -1));
				} else if (field.getType() == Boolean.TYPE) {
					field.set(this, prefs.getBoolean(field.getName(), false));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
