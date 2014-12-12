package com.cafe.virgo.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.widget.ImageView;

/*
 * 翻转矩阵
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MatrixUtils {

    public static Bitmap getTextureFromBitmapResource(Context context, Bitmap bitmap,Matrix mMatrix) {
    	try {
    		mMatrix.postScale(-1, 1);
			return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
			                bitmap.getHeight(), mMatrix, false);
		} finally {
	        if (bitmap != null) {
	                //bitmap.recycle();
	        } 
        }
    }
    
    public static ImageState getCenterViewPointF(ImageView mImageView){
    	Matrix mMatrix = mImageView.getMatrix(); 
    	Rect rect = mImageView.getDrawable().getBounds(); 
    	float[] values = new float[9]; 
    	mMatrix.getValues(values); 
    	ImageState mapState = new ImageState(); 
    	mapState.setLeft(values[2]); 
    	mapState.setTop(values[5]); 
    	mapState.setRight(mapState.getLeft() + rect.width() * values[0]); 
    	mapState.setBottom(mapState.getTop() + rect.height() * values[0]);
    	return mapState;
    }
}
