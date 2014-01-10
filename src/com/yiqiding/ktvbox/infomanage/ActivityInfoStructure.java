package com.yiqiding.ktvbox.infomanage;

import android.os.Parcel;
import android.os.Parcelable;

public class ActivityInfoStructure implements Parcelable{
	private int activityid;
	private String image;
	private String title;
	private String description;
	private int type;

	public int getActivityid()
	{
		return this.activityid;
	}
	
	public void setActivityid(int activityid)
	{
		this.activityid = activityid;
	}
	
	public String getImage()
	{
		return this.image;
	}
	
	public void setImage(String image)
	{
		this.image = image;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public int getType()
	{
		return this.type;
	}
	
	public void setType(int type)
	{
		this.type = type;
	}
	
	public ActivityInfoStructure()
	{
		
	}
	
	public ActivityInfoStructure(int activityid, String image, String title, String description, int type)
	{
		this.activityid = activityid;
		this.image = image;
		this.title = title;
		this.description = description;
		this.type = type;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(activityid);
		dest.writeString(image);
		dest.writeString(title);
		dest.writeString(description);
		dest.writeInt(type);
	}

	public static final Parcelable.Creator<ActivityInfoStructure> CREATOR = new Parcelable.Creator<ActivityInfoStructure>() {

		@Override
		public ActivityInfoStructure createFromParcel(Parcel source) {
			return new ActivityInfoStructure(source.readInt(),source.readString(),source.readString(),source.readString(),source.readInt());
		}

		@Override
		public ActivityInfoStructure[] newArray(int size) {
			return new ActivityInfoStructure[size];
		}
	};
}
