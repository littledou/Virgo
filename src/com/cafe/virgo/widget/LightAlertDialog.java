package com.cafe.virgo.widget;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class LightAlertDialog extends AlertDialog {

	/**
	 * 创建
	 * @param context
	 * @return dialog
	 */
	public static AlertDialog create(final Context context) {
		if (SDK_INT >= ICE_CREAM_SANDWICH)
			return new LightAlertDialog(context, THEME_HOLO_LIGHT);
		else
			return new LightAlertDialog(context);
	}

	private LightAlertDialog(final Context context, final int theme) {
		super(context, theme);
	}

	private LightAlertDialog(final Context context) {
		super(context);
	}

	public static class Builder extends AlertDialog.Builder {
		public static LightAlertDialog.Builder create(final Context context) {
			if (SDK_INT >= ICE_CREAM_SANDWICH)
				return new LightAlertDialog.Builder(context, THEME_HOLO_LIGHT);
			else
				return new LightAlertDialog.Builder(context);
		}

		private Builder(Context context) {
			super(context);
		}

		private Builder(Context context, int theme) {
			super(context, theme);
		}
	}
}
