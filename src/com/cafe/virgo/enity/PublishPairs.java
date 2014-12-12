package com.cafe.virgo.enity;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.cafe.virgo.enity.PublishDapeiTags.Tags;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 发布搭配信息
 */
@SuppressLint("ParcelCreator")
@JsonIgnoreProperties(ignoreUnknown=true) 
public class PublishPairs implements Parcelable {
	
	public PublishPairs(){
		
	}
	
	private PublishPairs(Parcel in){
		deviceOsystem = in.readString();
		dirty = in.readString();
		id = in.readString();
		basedon_tid = in.readString();
		category =in.readString();
		description = in.readString();
		tags = in.createTypedArray(Tags.CREATOR);
		items = in.createTypedArray(Items.CREATOR);
		title = in.readString();
		
	}
	
	private String deviceOsystem;
	
	private String dirty;
	private String id;
	
	private String basedon_tid;
	
	private String category;
	
	private String description;
	
	private Tags[] tags;
	
	private Items[] items;
	
	private String title;
	
	@SuppressLint("ParcelCreator")
	@JsonIgnoreProperties(ignoreUnknown=true) 
	public static class Items implements Parcelable {
		
		public Items(){
			
		}
		
		private Items(Parcel in){
			this.x = in.readDouble();
			this.y = in.readDouble();
			this.w = in.readDouble();
			this.h = in.readDouble();
			this.z = in.readInt();
			this.type = in.readString();
			this.thing_id = in.readString();
			this.image_dat = in.readString();
			this.transform = in.createDoubleArray();
			this.mask_spec = in.createTypedArray(Mask_spec.CREATOR);
			this.template_spec = in.readParcelable(Template_spec.class.getClassLoader());
		}
		
		private double x;
		private double y;
		private double w;
		private double h;
		private int z;
		private String type;
		private String thing_id;
		private String image_dat;
		
		private double[] transform = new double[4];
		
		private Mask_spec[] mask_spec;
		private Template_spec template_spec;
		
		@SuppressLint("ParcelCreator")
		@JsonIgnoreProperties(ignoreUnknown=true) 
		public static class Mask_spec implements Parcelable {
			
			public Mask_spec(){
				
			}

			private Mask_spec(Parcel in){
				x = in.readFloat();
				y = in.readFloat();
			}
			
			private	float x;
			private float y;
			public float getX() {
				return x;
			}
			public void setX(float x) {
				this.x = x;
			}
			public float getY() {
				return y;
			}
			public void setY(float y) {
				this.y = y;
			}
			@Override
			public int describeContents() {
				return 0;
			}
			@Override
			public void writeToParcel(Parcel dest, int flags) {
				dest.writeFloat(x);
				dest.writeFloat(y);
			}
			
			public static final Parcelable.Creator<Mask_spec> CREATOR = new Parcelable.Creator<Mask_spec>() {

		         public Mask_spec createFromParcel(Parcel in) {
		             return new Mask_spec(in);
		         }

		         public Mask_spec[] newArray(int size) {
		             return new Mask_spec[size];
		         }
		     
			};
     
		}
		
		@SuppressLint("ParcelCreator")
		@JsonIgnoreProperties(ignoreUnknown=true) 
		public static class Template_spec implements Parcelable {
			
			public Template_spec(){
				
			}

			private Template_spec(Parcel in){
				scale = in.readFloat();
				template_id = in.readString();
				x = in.readFloat();
				y = in.readFloat();
			}
			
			private float scale;
			private String template_id;
			private	float x;
			private float y;
			
			public float getScale() {
				return scale;
			}
			public void setScale(float scale) {
				this.scale = scale;
			}
			public String getTemplate_id() {
				return template_id;
			}
			public void setTemplate_id(String template_id) {
				this.template_id = template_id;
			}
			public float getX() {
				return x;
			}
			public void setX(float x) {
				this.x = x;
			}
			public float getY() {
				return y;
			}
			public void setY(float y) {
				this.y = y;
			}
			@Override
			public int describeContents() {
				return 0;
			}
			@Override
			public void writeToParcel(Parcel dest, int flags) {
				dest.writeFloat(scale);
				dest.writeString(template_id);
				dest.writeFloat(x);
				dest.writeFloat(y);
			}
			
			public static final Parcelable.Creator<Template_spec> CREATOR = new Parcelable.Creator<Template_spec>() {

		         public Template_spec createFromParcel(Parcel in) {
		             return new Template_spec(in);
		         }

		         public Template_spec[] newArray(int size) {
		             return new Template_spec[size];
		         }
		     
			};
     
		}
		
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getThing_id() {
			return thing_id;
		}
		public void setThing_id(String thing_id) {
			this.thing_id = thing_id;
		}
		public String getImage_dat() {
			return image_dat;
		}
		public void setImage_dat(String image_dat) {
			this.image_dat = image_dat;
		}

		public double getX() {
			return x;
		}


		public double getY() {
			return y;
		}


		public double getW() {
			return w;
		}


		public double getH() {
			return h;
		}


		public int getZ() {
			return z;
		}


		public void setX(double x) {
			this.x = x;
		}


		public void setY(double y) {
			this.y = y;
		}


		public void setW(double w) {
			this.w = w;
		}


		public void setH(double h) {
			this.h = h;
		}


		public void setZ(int z) {
			this.z = z;
		}
		
		public Mask_spec[] getMask_spec() {
			return mask_spec;
		}
		public void setMask_spec(Mask_spec[] mask_spec) {
			this.mask_spec = mask_spec;
		}
		public Template_spec getTemplate_spec() {
			return template_spec;
		}
		public void setTemplate_spec(Template_spec template_spec) {
			this.template_spec = template_spec;
		}
		public double[] getTransform() {
			return transform;
		}
		public void setTransform(double[] transform) {
			this.transform = transform;
		}
		@Override
		public int describeContents() {
			return 0;
		}
		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeDouble(x);
			dest.writeDouble(y);
			dest.writeDouble(w);
			dest.writeDouble(h);
			dest.writeInt(z);
			dest.writeString(type);
			dest.writeString(thing_id);
			dest.writeString(image_dat);
			dest.writeDoubleArray(transform);
			dest.writeTypedArray(mask_spec, flags);
			dest.writeParcelable(template_spec, flags);
		}
		
		public static final Parcelable.Creator<Items> CREATOR = new Parcelable.Creator<Items>() {

	         public Items createFromParcel(Parcel in) {
	             return new Items(in);
	         }

	         public Items[] newArray(int size) {
	             return new Items[size];
	         }
	     
		};
	}

	public String getDirty() {
		return dirty;
	}

	public void setDirty(String dirty) {
		this.dirty = dirty;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Items[] getItems() {
		return items;
	}

	public void setItems(Items[] items) {
		this.items = items;
	}	

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBasedon_tid() {
		return basedon_tid;
	}

	public void setBasedon_tid(String basedon_tid) {
		this.basedon_tid = basedon_tid;
	}

	public Tags[] getTags() {
		return tags;
	}

	public void setTags(Tags[] tags) {
		this.tags = tags;
	}

	public String getDeviceOsystem() {
		return deviceOsystem;
	}

	public void setDeviceOsystem(String deviceOsystem) {
		this.deviceOsystem = deviceOsystem;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(deviceOsystem);
		dest.writeString(dirty);
		dest.writeString(id);
		dest.writeString(basedon_tid);
		dest.writeString(category);
		dest.writeString(description);
		dest.writeTypedArray(tags, flags);
		dest.writeTypedArray(items, flags);
		dest.writeString(title);
	}
	
	public static final Parcelable.Creator<PublishPairs> CREATOR = new Parcelable.Creator<PublishPairs>() {

        public PublishPairs createFromParcel(Parcel in) {
            return new PublishPairs(in);
        }

        public PublishPairs[] newArray(int size) {
            return new PublishPairs[size];
        }
    
	};
}
