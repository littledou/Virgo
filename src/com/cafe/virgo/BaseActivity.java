package com.cafe.virgo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.cafe.virgo.util.ThreadUtils;

public class BaseActivity extends Activity {
	protected final Handler mHandler = ThreadUtils.getMainThreadHandler();
	public Context mContext;
	public BaseApplication mApplication;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		mApplication = BaseApplication.getInstence();
	}
}
