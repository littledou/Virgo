package com.cafe.virgo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SlideViewFragment extends Fragment{
	private View iView;

	public static SlideViewFragment getIntence(View paramView)
	{
		SlideViewFragment localPageFragment = new SlideViewFragment();
		localPageFragment.iView = paramView;
		return localPageFragment;
	}

	public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
	{
		if (this.iView == null)
			this.iView = new View(getActivity());
		return this.iView;
	}
}
