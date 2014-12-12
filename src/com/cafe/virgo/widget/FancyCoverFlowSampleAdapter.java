/*
 * Copyright 2013 David Schreiber
 *           2013 John Paul Nalog
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.cafe.virgo.widget;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cafe.virgo.R;
import com.cafe.virgo.util.BitmapUtils;
import com.cafe.virgo.util.DLogUtils;

public class FancyCoverFlowSampleAdapter extends FancyCoverFlowAdapter {

	// =============================================================================
	// Private members
	// =============================================================================

	private int[] images = {
			R.drawable.y1,
			R.drawable.y2,
			R.drawable.y3,
			R.drawable.y4,
			R.drawable.y5,
			R.drawable.y4,
			R.drawable.y5,
			R.drawable.y4,
			R.drawable.y5,
	};

	// =============================================================================
	// Supertype overrides
	// =============================================================================

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public Integer getItem(int i) {
		return images[i%images.length];
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getCoverFlowItem(int i, View reuseableView, ViewGroup viewGroup) {
		ImageView imageView = new ImageView(viewGroup.getContext());
		imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		imageView.setLayoutParams(new FancyCoverFlow.LayoutParams(800, 1000));
		if(!shadeMap.containsKey(i%images.length)){
			shadeMap.put(i%images.length, BitmapUtils.createReflectedBitmap(viewGroup.getContext(), getItem(i)));
		}
		imageView.setImageBitmap(shadeMap.get(i%images.length));
		return imageView;
	}
	@SuppressLint("UseSparseArrays")
	private Map<Integer, Bitmap> shadeMap = new HashMap<Integer, Bitmap>();
}
