package com.yiqiding.ktvbox.infomanage;

import android.os.Parcel;
import android.os.Parcelable;

public class SingerInfoStructure implements Parcelable{
	private int singerid;
	private String name;
	private int type;
	private int nation;
	private String image;

	public SingerInfoStructure()
	{
		
	}
	
	public SingerInfoStructure(int singerid, String name, int type, int nation, String image)
	{
		this.singerid = singerid;
		this.name = name;
		this.type = type;
		this.nation = nation;
		this.image = image;
	}
	
	public int getSingerId()
	{
		return this.singerid;
	}
	
	public void setSingerId(int singerid)
	{
		this.singerid = singerid;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getType()
	{
		return this.type;
	}
	
	public void setType(int type)
	{
		this.type = type;
	}
	
	public int getNation()
	{
		return this.nation;
	}
	
	public void setNation(int nation)
	{
		this.nation = nation;
	}
	
	public String getImage()
	{
		return this.image;
	}
	
	public void setImage(String image)
	{
		this.image = image;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(singerid);
		dest.writeString(name);
		dest.writeInt(type);
		dest.writeInt(nation);
		dest.writeString(image);
	}

	public static final Parcelable.Creator<SingerInfoStructure> CREATOR = new Parcelable.Creator<SingerInfoStructure>() {

		@Override
		public SingerInfoStructure createFromParcel(Parcel source) {
			return new SingerInfoStructure(source.readInt(), source.readString(), source.readInt(), source.readInt(), source.readString());
		}

		@Override
		public SingerInfoStructure[] newArray(int size) {
			return new SingerInfoStructure[size];
		}
	};
}
