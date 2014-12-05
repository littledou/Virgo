package com.cafe.virgo.activity;

import android.os.Bundle;

import com.cafe.virgo.BaseActivity;
import com.cafe.virgo.widget.FancyCoverFlow;
import com.cafe.virgo.widget.FancyCoverFlowSampleAdapter;
public class MainPageActivity extends BaseActivity{

	private FancyCoverFlow fancyCoverFlow;
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fancyCoverFlow = new FancyCoverFlow(this);
		this.fancyCoverFlow.setAdapter(new FancyCoverFlowSampleAdapter());
		this.fancyCoverFlow.setUnselectedAlpha(0.4f);
		this.fancyCoverFlow.setUnselectedSaturation(0.1f);
		this.fancyCoverFlow.setUnselectedScale(0.8f);
		this.fancyCoverFlow.setSpacing(-40);
		this.fancyCoverFlow.setSelection(1000, true);
		this.fancyCoverFlow.setMaxRotation(0);
		this.fancyCoverFlow.setScaleDownGravity(1.2f);
		this.fancyCoverFlow.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
		setContentView(fancyCoverFlow);
	}
}
