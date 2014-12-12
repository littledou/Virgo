package com.cafe.virgo.widget;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.cafe.virgo.enity.PublishPairs.Items.Mask_spec;
import com.cafe.virgo.enity.PublishPairs.Items.Template_spec;


/**
 * @author 侯银博
 * cllanjim@gmail.com
 */
@SuppressLint("DrawAllocation")
public class TouchView extends ImageView  {

	private static final int PADDING = 3;
    private static final float STROKE_WIDTH = 3;
    private Paint mBorderPaint;
    
    private String thingid;
    private String thingurl;
    private String objectid;
    
    private boolean isMarked;
    
    private float currentRotation = 0; //现在旋转角度
    
    private ArrayList<Mask_spec>  listCropperParam = new ArrayList<Mask_spec>();
    private Template_spec template_spec;
    private Boolean isFlip = false;
    
    
	public TouchView(Context context) {
        this(context, null);
        setPadding(PADDING, PADDING, PADDING, PADDING);
    }

    public TouchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        setPadding(PADDING, PADDING, PADDING, PADDING);
    }


    public TouchView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        super.setScaleType(ScaleType.CENTER);
        mBorderPaint = new Paint();
        setPadding(PADDING, PADDING, PADDING, PADDING);
    }
     
    public void rotate(float angle) {
        Matrix m = new Matrix();
        m.postRotate(angle);
        setImageMatrix(m);
        invalidate();
    }

    public void scale(float scaleFactor) {
        Matrix m = new Matrix();
        m.postScale(scaleFactor, scaleFactor);
        setImageMatrix(m);
        invalidate();
    }

	@Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(isMarked){
        	mBorderPaint.setAntiAlias(true);
            mBorderPaint.setStyle(Paint.Style.STROKE);//设置画出的线的 粗细程度
        	mBorderPaint.setColor(Color.RED);
        	mBorderPaint.setStrokeWidth(STROKE_WIDTH);
            canvas.drawRect(0, 0, getWidth(), getHeight(), mBorderPaint);
        }
    }
	
	public String getObjectid() {
		return objectid;
	}

	public void setObjectid(String thingid) {
		this.objectid = thingid;
	}
	
	public String getThingid() {
		return thingid;
	}

	public void setThingid(String thingid) {
		this.thingid = thingid;
	}	

	public boolean isMarked() {
		return isMarked;
	}

	public void setMarked(boolean isMarked) {
		this.isMarked = isMarked;
	}

	public float getCurrentRotation() {
		return currentRotation;
	}

	public void setCurrentRotation(float currentRotation) {
		this.currentRotation = currentRotation;
	}

	public Boolean getIsFlip() {
		return isFlip;
	}

	public void setIsFlip(Boolean isFlip) {
		this.isFlip = isFlip;
	}

	public ArrayList<Mask_spec> getListMask_spec() {
		return listCropperParam;
	}

	public void setListMask_spec(ArrayList<Mask_spec> listCropperParam) {
		this.listCropperParam = listCropperParam;
	}

	public Template_spec getTemplate_spec() {
		return template_spec;
	}

	public void setTemplate_spec(Template_spec template_spec) {
		this.template_spec = template_spec;
	}

	public String getThingurl() {
		return thingurl;
	}

	public void setThingurl(String thingurl) {
		this.thingurl = thingurl;
	}

}
