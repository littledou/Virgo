package com.cafe.virgo.util;

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

/**
 * 图片操作工具类
 */
public final class BitmapUtils{

	/**
	 * 资源文件id获取bitmap
	 */
	public static Bitmap getBitmapFromResouce(Context context,int id){
		return BitmapFactory.decodeResource(context.getResources(), id);
	}

	/**
	 * 图片反转
	 */
	public static Bitmap getRotateBitmap(Bitmap b, float rotateDegree){
		Matrix matrix = new Matrix();
		matrix.postRotate((float)rotateDegree);
		Bitmap rotaBitmap = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), matrix, false);
		return rotaBitmap;
	}

	/**
	 * data to bitmap
	 */
	public static Bitmap dataToBitmap(byte[] data){
		return BitmapFactory.decodeByteArray(data, 0, data.length);
	}
	/** 
	 *  处理图片  
	 */ 
	public static Bitmap zoomImg(Bitmap bm, int newWidth ,int newHeight){   
		// 获得图片的宽高   
		int width = bm.getWidth();   
		int height = bm.getHeight();   
		// 计算缩放比例   
		float scaleWidth = ((float) newWidth) / width;   
		float scaleHeight = ((float) newHeight) / height;   
		// 取得想要缩放的matrix参数   
		Matrix matrix = new Matrix();   
		matrix.postScale(scaleWidth, scaleHeight);   
		Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);   
		return newbm;   
	}  
	/**
	 * @param srcBitmap
	 * @return
	 */
	public static Bitmap createReflectedBitmap(Context context,int id){    	
		final int padding = 150;
		Bitmap srcBitmap = getBitmapFromResouce(context, id);
		if (null == srcBitmap){
			return null;
		}

		// The gap between the reflection bitmap and original bitmap. 
		final int REFLECTION_GAP = 4;

		int srcWidth  = srcBitmap.getWidth();
		int srcHeight = srcBitmap.getHeight();
		int reflectionWidth  = srcBitmap.getWidth();
		int reflectionHeight = srcBitmap.getHeight() / 2;

		if (0 == srcWidth || srcHeight == 0){
			return null;
		}

		// The matrix
		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);
		try{
			// The reflection bitmap, width is same with original's, height is half of original's.
			Bitmap reflectionBitmap = Bitmap.createBitmap(
					srcBitmap,
					0, 
					srcHeight / 2,
					srcWidth, 
					srcHeight / 2,
					matrix,
					false);

			if (null == reflectionBitmap)
			{
				return null;
			}

			// Create the bitmap which contains original and reflection bitmap.
			Bitmap bitmapWithReflection = Bitmap.createBitmap(
					reflectionWidth,
					srcHeight + reflectionHeight + REFLECTION_GAP, 
					Config.ARGB_8888);

			if (null == bitmapWithReflection){
				return null;
			}

			// Prepare the canvas to draw stuff.
			Canvas canvas = new Canvas(bitmapWithReflection);

			// Draw the original bitmap.
			canvas.drawBitmap(srcBitmap, 0, padding, null);

			// Draw the reflection bitmap.
			canvas.drawBitmap(reflectionBitmap, 0, padding+srcHeight + REFLECTION_GAP, null);

			Paint paint = new Paint();
			paint.setAntiAlias(true);
			LinearGradient shader = new LinearGradient(
					0, 
					srcHeight, 
					0, 
					bitmapWithReflection.getHeight() + REFLECTION_GAP, 
					0x70FFFFFF, 
					0x00FFFFFF,
					TileMode.MIRROR);
			paint.setShader(shader);
			paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.DST_IN));

			// Draw the linear shader.
			canvas.drawRect(
					0, 
					padding+srcHeight, 
					srcWidth, 
					padding+bitmapWithReflection.getHeight() + REFLECTION_GAP, 
					paint);

			reflectionBitmap.recycle();
			return bitmapWithReflection;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}
}
