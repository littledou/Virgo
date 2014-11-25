package com.cafe.virgo;

import com.cafe.virgo.util.ThreadUtils;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

public class BaseFragmentActivity extends FragmentActivity {
	protected final Handler mHandler = ThreadUtils.getMainThreadHandler();
	public Context mContext;
	public BaseApplication mApplication;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		mContext = this;
		mApplication = BaseApplication.getInstence();
	}
}
