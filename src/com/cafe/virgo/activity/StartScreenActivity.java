package com.cafe.virgo.activity;

import android.content.Intent;
import android.os.Bundle;

import com.cafe.virgo.AppPreferences;
import com.cafe.virgo.BaseFragmentActivity;
import com.cafe.virgo.R;
import com.cafe.virgo.fragmentactivity.MainPageActivity;

/**
 * 应用启动屏
 */
public class StartScreenActivity extends BaseFragmentActivity{
	private AppPreferences mPreferences;
	//	private NetworkStatReceiver networkStatReceiver  = new NetworkStatReceiver();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPreferences = AppPreferences.getInstance(mContext);
		mPreferences.readLocalProperties(mContext);
		//		IntentFilter filter = new IntentFilter();
		//		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		//		registerReceiver(networkStatReceiver, filter);
		//		if (StringUtils.isEmpty(mPreferences.isGuide)) {//进入引导页
		//			setContentView(R.layout.app_startup_screen);
		//		}else{
		setContentView(R.layout.activity_startup_screen);
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				startActivity(new Intent(mContext, MainPageActivity.class));
				finish();
			}
		}, 1000);
		//		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		//		if(null != networkStatReceiver){
		//			try {
		//				unregisterReceiver(networkStatReceiver);
		//			} catch (Exception e) {
		//				e.printStackTrace();
		//			}
		//		}
	}
}
