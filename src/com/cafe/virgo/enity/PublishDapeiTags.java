package com.cafe.virgo.enity;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author 侯银博 cllanjim@gmail.com
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublishDapeiTags {

	// private Tid[] tid;

	private String name;

	private Tags[] tags;

	@SuppressLint("ParcelCreator")
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Tags implements Parcelable {

		private String id;
		private String is_hot;
		private String name;
		private String tag_type;

		public Tags() {
		}

		private Tags(Parcel in) {
			id = in.readString();
			is_hot = in.readString();
			name = in.readString();
			tag_type = in.readString();
		}

		public String getId() {
			return id;
		}

		public String getIs_hot() {
			return is_hot;
		}

		public String getName() {
			return name;
		}

		public String getTag_type() {
			return tag_type;
		}

		public void setId(String id) {
			this.id = id;
		}

		public void setIs_hot(String is_hot) {
			this.is_hot = is_hot;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setTag_type(String tag_type) {
			this.tag_type = tag_type;
		}

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeString(id);
			dest.writeString(is_hot);
			dest.writeString(name);
			dest.writeString(tag_type);
		}

		public static final Parcelable.Creator<Tags> CREATOR = new Parcelable.Creator<Tags>() {

			public Tags createFromParcel(Parcel in) {
				return new Tags(in);
			}

			public Tags[] newArray(int size) {
				return new Tags[size];
			}

		};

	}

	public Tags[] getTags() {
		return tags;
	}

	public void setTags(Tags[] tags) {
		this.tags = tags;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
