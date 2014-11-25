package com.cafe.virgo;

import com.cafe.virgo.util.ThreadUtils;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {
	protected final Handler mHandler = ThreadUtils.getMainThreadHandler();
	public Context mContext;
	public BaseApplication mApplication;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getActivity();
		mApplication = BaseApplication.getInstence();
	}
}
