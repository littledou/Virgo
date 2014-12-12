package com.cafe.virgo.widget;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.FROYO;
import static android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cafe.virgo.R;

/**
 * 优美Progress dialog
 * @author 侯银博
 * mailto:cllanjim@gmail.com
 */
public class LightProgressDialog extends ProgressDialog {

    /**
     * @param context
     * @param resId
     * @return dialog
     */
    public static AlertDialog create(Context context, int resId) {
        return create(context, context.getResources().getString(resId));
    }

    /**
     * @param context
     * @param message
     * @return dialog
     */
    public static AlertDialog create(Context context, CharSequence message) {
        if (SDK_INT > FROYO) {
            ProgressDialog dialog;
            if (SDK_INT >= ICE_CREAM_SANDWICH)
                dialog = new LightProgressDialog(context, message);
            else {
                dialog = new ProgressDialog(context);
            }
            dialog.setMessage(message);
            dialog.setInverseBackgroundForced(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setIndeterminate(true);
            dialog.setProgressStyle(STYLE_SPINNER);
            dialog.setIndeterminateDrawable(context.getResources().getDrawable(
                    R.drawable.lightprogressdialog_spinner));
            return dialog;
        } else {
        	AlertDialog dialog = LightAlertDialog.create(context);
            dialog.setInverseBackgroundForced(false);
            View view = LayoutInflater.from(context).inflate(
                    R.layout.progress_dialog, null);
            ((TextView) view.findViewById(R.id.tv_loading)).setText(message);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setView(view);
            return dialog;
        }
    }
   
    private LightProgressDialog(Context context, CharSequence message) {
        super(context, THEME_HOLO_LIGHT);
    }
}
