package com.cafe.virgo.enity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ItemsGetFromDapei implements Serializable {
	
	private String thing_id;
	private String object_id;
	private String y;
	private String z;
	private String w;
	private String x;
	private String h;
	private String ow;
	private String oh;
	private String mask_spec;
	private String template_spec = "";
	private float[] transform = new float[4];
	private String bkgd;
	private String img_jpg;
	private String img_png;
	private String spec_url;
	private String spec_w;
	private String spec_h;
	
	public String getThing_id() {
		return thing_id;
	}
	public void setThing_id(String thing_id) {
		this.thing_id = thing_id;
	}
	public String getObject_id() {
		return object_id;
	}
	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getZ() {
		return z;
	}
	public void setZ(String z) {
		this.z = z;
	}
	public String getW() {
		return w;
	}
	public void setW(String w) {
		this.w = w;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getH() {
		return h;
	}
	public void setH(String h) {
		this.h = h;
	}
	public String getOw() {
		return ow;
	}
	public void setOw(String ow) {
		this.ow = ow;
	}
	public String getOh() {
		return oh;
	}
	public void setOh(String oh) {
		this.oh = oh;
	}
	public String getMask_spec() {
		return mask_spec;
	}
	public void setMask_spec(String mask_spec) {
		this.mask_spec = mask_spec;
	}
	public String getTemplate_spec() {
		return template_spec;
	}
	public void setTemplate_spec(String template_spec) {
		this.template_spec = template_spec;
	}
	public float[] getTransform() {
		return transform;
	}
	public void setTransform(float[] transform) {
		this.transform = transform;
	}
	public String getBkgd() {
		return bkgd;
	}
	public void setBkgd(String bkgd) {
		this.bkgd = bkgd;
	}
	public String getImg_jpg() {
		return img_jpg;
	}
	public void setImg_jpg(String img_jpg) {
		this.img_jpg = img_jpg;
	}
	public String getImg_png() {
		return img_png;
	}
	public void setImg_png(String img_png) {
		this.img_png = img_png;
	}
	public String getSpec_url() {
		return spec_url;
	}
	public void setSpec_url(String spec_url) {
		this.spec_url = spec_url;
	}
	public String getSpec_w() {
		return spec_w;
	}
	public void setSpec_w(String spec_w) {
		this.spec_w = spec_w;
	}
	public String getSpec_h() {
		return spec_h;
	}
	public void setSpec_h(String spec_h) {
		this.spec_h = spec_h;
	}
}