package com.yiqiding.ktvbox.infomanage;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class MusicInfoStructure implements Parcelable{
	private int musicid;
	private String name;
	private List<SingerInfoStructure> singers;
	private int country;
	private int language;
	private int type;
	private int length;
	private String image;
	private int stars;
	private int count;
	private String albuminfo;

	public MusicInfoStructure()
	{
		singers = new ArrayList<SingerInfoStructure>();
	}
	
	public int getMusicId()
	{
		return this.musicid;
	}
	
	public void setMusicId(int musicid)
	{
		this.musicid = musicid;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public List<SingerInfoStructure> getSingers()
	{
		return this.singers;
	}
	
//	public void setSingers(List<SingerInfoStructure> singers)
//	{
//		this.singers = singers;
//	}
	
	public int getCountry()
	{
		return this.country;
	}
	
	public void setCountry(int country)
	{
		this.country = country;
	}
	
	public int getLanguage()
	{
		return this.language;
	}
	
	public void setLanguage(int language)
	{
		this.language = language;
	}
	
	public int getType()
	{
		return this.type;
	}
	
	public void setType(int type)
	{
		this.type = type;
	}
	
	public int getLength()
	{
		return this.length;
	}
	
	public void setLength(int length)
	{
		this.length = length;
	}
	
	public String getImage()
	{
		return this.image;
	}
	
	public void setImage(String image)
	{
		this.image = image;
	}
	
	public int getStars()
	{
		return this.stars;
	}
	
	public void setStars(int stars)
	{
		this.stars = stars;
	}
	
	public int getCount()
	{
		return this.count;
	}
	
	public void setCount(int count)
	{
		this.count = count;
	}
	
	public String getAlbumInfo()
	{
		return this.albuminfo;
	}
	
	public void setAlbumInfo(String albumInfo)
	{
		this.albuminfo = albumInfo;
	}
	
	public MusicInfoStructure(Parcel in)
	{
		this.musicid = in.readInt();
		this.name = in.readString();
		in.readTypedList(this.singers, SingerInfoStructure.CREATOR);
		this.country = in.readInt();
		this.language = in.readInt();
		this.type = in.readInt();
		this.length = in.readInt();
		this.image = in.readString();
		this.stars = in.readInt();
		this.count = in.readInt();
		this.albuminfo = in.readString();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(musicid);
		dest.writeString(name);
		dest.writeTypedList(singers);
		dest.writeInt(country);
		dest.writeInt(language);
		dest.writeInt(type);
		dest.writeInt(length);
		dest.writeString(image);
		dest.writeInt(stars);
		dest.writeInt(count);
		dest.writeString(albuminfo);
	}

	public static final Parcelable.Creator<MusicInfoStructure> CREATOR = new Parcelable.Creator<MusicInfoStructure>() {

		@Override
		public MusicInfoStructure createFromParcel(Parcel source) {
			return new MusicInfoStructure(source);
		}

		@Override
		public MusicInfoStructure[] newArray(int size) {
			return new MusicInfoStructure[size];
		}
	};
}
