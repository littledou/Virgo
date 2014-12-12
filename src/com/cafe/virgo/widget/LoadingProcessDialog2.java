package com.cafe.virgo.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.cafe.virgo.R;

public class LoadingProcessDialog2 extends ProgressDialog{

	public LoadingProcessDialog2(Context context) {
		super(context);
		setCanceledOnTouchOutside(false);
	}
	
	public LoadingProcessDialog2(Context context, int theme) {
		super(context, theme);
		setCanceledOnTouchOutside(false);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progressbar);
	}
	
	public static LoadingProcessDialog2 show(Context ctx){
		LoadingProcessDialog2 d = new LoadingProcessDialog2(ctx);
		d.show();
		return d;
	}
}