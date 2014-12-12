package com.cafe.virgo.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;

import com.cafe.virgo.R;
import com.cafe.virgo.config.VirgoConstant;
import com.cafe.virgo.util.ToastUtils;

public class NetworkStatReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		State wifiState = null;  
		State mobileState = null;  
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);  
		wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();  
		mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();  
		if (wifiState != null && mobileState != null
				&& State.CONNECTED != wifiState  
				&& State.CONNECTED == mobileState) {
			// 手机网络连接成功  
			if(VirgoConstant.NOWIFI_ENIABLE){
				VirgoConstant.IS_ONLINE = true;//手机网络状态
				ToastUtils.showShortToast(R.string.app_conn_phone_on);
			}else {
				VirgoConstant.IS_ONLINE = false;
				ToastUtils.showShortToast(R.string.app_conn_phone_close);
			}
		} else if (wifiState != null && mobileState != null  
				&& State.CONNECTED != wifiState  
				&& State.CONNECTED != mobileState) {  
			// 手机没有任何的网络  
			VirgoConstant.IS_ONLINE = false;
			ToastUtils.showShortToast(R.string.app_conn_close);
		} else if (wifiState != null && State.CONNECTED == wifiState) {  
			// 无线网络连接成功  
			VirgoConstant.IS_ONLINE = true;//wifi状态
			ToastUtils.showShortToast(R.string.app_conn_wifi_on);
		}  
	}

}
