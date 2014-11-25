package com.cafe.virgo.enity;

/**
 * 屏幕尺寸
 * @author cafe_dou
 */
public class ScrnSize {
	public int ScrnW_px,ScrnH_px;
	public float dpi;
	public int ScrnW_dip,ScrnH_dip;
	public ScrnSize(int ScrnW,int ScrnH,float dpi){
		this.dpi = dpi;
		this.ScrnW_px = ScrnW;
		this.ScrnH_px = ScrnH;
		ScrnW_dip = (int) (ScrnW_px/dpi+0.5f);
		ScrnH_dip = (int) (ScrnH_px/dpi+0.5f);
	}
}
