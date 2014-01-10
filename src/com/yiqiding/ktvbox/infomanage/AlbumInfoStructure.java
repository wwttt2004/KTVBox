package com.yiqiding.ktvbox.infomanage;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class AlbumInfoStructure implements Parcelable{
	private int albumid;
	private String name;
	private String release;
	private String detail;
	private String image;
	private List<MusicInfoStructure> musics;
	private List<SingerInfoStructure> singers;

	public AlbumInfoStructure()
	{
		musics = new ArrayList<MusicInfoStructure>();
		singers = new ArrayList<SingerInfoStructure>();
	}
	
	public int getAlbumId()
	{
		return this.albumid;
	}

	public void setAlbumId(int albumid)
	{
		this.albumid = albumid;
	}

	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getRelease()
	{
		return this.release;
	}
	
	public void setRelease(String release)
	{
		this.release = release;
	}
	
	public String getDetail()
	{
		return this.detail;
	}
	
	public void setDetail(String detail)
	{
		this.detail = detail;
	}
	
	public String getImage()
	{
		return this.image;
	}
	
	public void setImage(String image)
	{
		this.image = image;
	}
	
	public List<MusicInfoStructure> getMusics()
	{
		return this.musics;
	}
	
	public List<SingerInfoStructure> getSingers()
	{
		return this.singers;
	}
	
	public AlbumInfoStructure(Parcel in)
	{
		this.albumid = in.readInt();
		this.name = in.readString();
		this.release = in.readString();
		this.detail = in.readString();
		this.image = in.readString();
		in.readTypedList(musics, MusicInfoStructure.CREATOR);
		in.readTypedList(singers, SingerInfoStructure.CREATOR);
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(albumid);
		dest.writeString(name);
		dest.writeString(release);
		dest.writeString(detail);
		dest.writeString(image);
		dest.writeTypedList(musics);
		dest.writeTypedList(singers);
	}
	
	public static final Parcelable.Creator<AlbumInfoStructure> CREATOR = new Parcelable.Creator<AlbumInfoStructure>() {

		@Override
		public AlbumInfoStructure createFromParcel(Parcel source) {
			return new AlbumInfoStructure(source);
		}

		@Override
		public AlbumInfoStructure[] newArray(int size) {
			return new AlbumInfoStructure[size];
		}
	};
}
