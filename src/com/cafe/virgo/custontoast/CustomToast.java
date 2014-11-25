package com.cafe.virgo.custontoast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cafe.virgo.R;


/**
 * 自定义Toast
 */
public class CustomToast extends Toast {

	@SuppressLint("InflateParams")
	public CustomToast(Context context,CharSequence text, int duration) {
		super(context);
		try {
			LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = inflate.inflate(R.layout.custom_toast, null);
			//			view.findViewById(R.id.toast_dialogroot).getBackground().setAlpha(180);
			TextView tvMessage = (TextView) view.findViewById(R.id.custom_toast_text);
			tvMessage.setText(text);
			this.setView(view);
			this.setDuration(duration);
			this.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, (int)convertDpToPixel(60, context));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Toast makeText(Context context, CharSequence text, int duration) {
		return new CustomToast(context, text, duration);
	}

	@Override
	public void show() {
		super.show();
	}

	private static float convertDpToPixel(float dp, Context context){
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return px;
	}
}
