package com.cafe.virgo.fragmentactivity;

import android.annotation.SuppressLint;
import android.app.Fragment.SavedState;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.cafe.virgo.BaseFragmentActivity;
import com.cafe.virgo.R;
import com.cafe.virgo.enity.ScrnSize;
import com.cafe.virgo.fragment.SlideViewFragment;
import com.cafe.virgo.util.DLogUtils;
import com.cafe.virgo.util.DisplayUtil;
import com.cafe.virgo.widget.FancyCoverFlow;
import com.cafe.virgo.widget.FancyCoverFlowSampleAdapter;
public class MainPageActivity extends BaseFragmentActivity implements OnTouchListener{

	private FancyCoverFlow fancyCoverFlow;
	private FrameLayout frame;

	private int slide_count = 0;
	private int tiem_count = 0;
	private Fragment currentFragment = null;
	private Fragment[] fragment = new Fragment[5];
	private int[] images = {
			R.drawable.y1,
			R.drawable.y2,
			R.drawable.y3,
			R.drawable.y4,
			R.drawable.y5
	};
	private int[] images_slide = {
			R.drawable.slide_image1,
			R.drawable.slide_image2,
	};
	private boolean isSlide = false;
	private boolean isTime = true;
	private boolean save_Time = true;
	private boolean save_Slide = false;
	@SuppressLint("ClickableViewAccessibility")
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DLogUtils.syso("onCreate");
		setContentView(R.layout.activity_mainpage);
		fancyCoverFlow = (FancyCoverFlow) findViewById(R.id.fancyCoverFlow);
		frame = (FrameLayout) findViewById(R.id.frame);
		this.fancyCoverFlow.setAdapter(new FancyCoverFlowSampleAdapter());
		this.fancyCoverFlow.setUnselectedAlpha(0.4f);
		this.fancyCoverFlow.setUnselectedSaturation(0.1f);                                    
		this.fancyCoverFlow.setUnselectedScale(0.8f);
		this.fancyCoverFlow.setSpacing(-40);
		this.fancyCoverFlow.setSelection(1000, true);
		this.fancyCoverFlow.setMaxRotation(0);
		this.fancyCoverFlow.setScaleDownGravity(1.2f);
		this.fancyCoverFlow.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
		this.fancyCoverFlow.setAnimationDuration(200);
		this.fancyCoverFlow.setOnTouchListener(this);
		this.frame.setOnTouchListener(this);
		fancyCoverFlow.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(position==fancyCoverFlow.getCurrentPosition()){
					//TODO 在此图片点击事件
					startActivity(new Intent(mContext,MainPageItemActivity.class));
				}
			}
		});

		for (int i = 0; i<images_slide.length;i++){
			ImageView localImageView = new ImageView(this);
			localImageView.setScaleType(ScaleType.CENTER_INSIDE);
			localImageView.setImageResource(images_slide[i]);
			fragment[i] = SlideViewFragment.getIntence(localImageView);
		}
		mHandler.postDelayed(mRunnableTime, 1000);
		mHandler.postDelayed(mRunnableSlide, 3000);
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		//点击为fancyCoverFlow-更新计时器，计时器定时显示滑动页面
		if(v.getId()==R.id.fancyCoverFlow&&event.getAction()==MotionEvent.ACTION_UP){
			tiem_count = 0;
		}
		if(v.getId()==R.id.frame&&event.getAction()==MotionEvent.ACTION_DOWN){
			tiem_count = 0;
			frame.setVisibility(View.GONE);
			fancyCoverFlow.setVisibility(View.VISIBLE);
			isTime = true;
			isSlide = false;
			return true;
		}
		return false;
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		DLogUtils.syso("onDestroy");
		mHandler.removeCallbacks(mRunnableSlide);
		mHandler.removeCallbacks(mRunnableTime);
	}
	@Override
	protected void onPause() {
		super.onPause();
		saveState();
	}

	@Override
	public void onBackPressed() {
		if(isSlide){
			isSlide = false;
			isTime = true;
			tiem_count = 0;
			frame.setVisibility(View.GONE);
			fancyCoverFlow.setVisibility(View.VISIBLE);
		}else {
			super.onBackPressed();
		}
	}
	private void saveState(){
		save_Time = isTime;
		save_Slide = isSlide;
		isTime = false;
		isSlide = false;
	}
	private void getState(){
		isTime = save_Time;
		isSlide = save_Slide;
	}
	@Override
	protected void onResume() {
		super.onResume();
		getState();
	}
	private Runnable mRunnableTime = new Runnable(){

		@Override
		public void run() {
			if(isTime){
				tiem_count =  (1 + tiem_count);
				if(tiem_count==5){
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							fancyCoverFlow.setVisibility(View.GONE);
							frame.setVisibility(View.VISIBLE);
						}
					});
					isTime = false;
					isSlide = true;
				}
			}
			mHandler.postDelayed(mRunnableTime, 1000);
		}
	};
	private Runnable mRunnableSlide = new Runnable(){

		@Override
		public void run() {
			if(isSlide){
				slide_count = (1 + slide_count);
				addFragmentToStack(slide_count % images_slide.length);
			}
			mHandler.postDelayed(mRunnableSlide, 3000);
		}
	};

	protected void addFragmentToStack(int paramInt){
		try
		{
			if (currentFragment!=null&&currentFragment == fragment[paramInt])
				return;
			FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
			localFragmentTransaction.setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left);
			if (!(fragment[paramInt].isAdded())){
				if(currentFragment==null){
					localFragmentTransaction.replace(R.id.frame, this.fragment[paramInt]);
					localFragmentTransaction.commit();
				}else{
					localFragmentTransaction.hide(currentFragment).add(R.id.frame, fragment[paramInt]).commit();
				}
				currentFragment = fragment[paramInt];
				return;
			}
			localFragmentTransaction.hide(currentFragment).show(fragment[paramInt]).commit();
			currentFragment = fragment[paramInt];
		}
		catch (Exception localException)
		{
			localException.printStackTrace();
		}
	}
}
