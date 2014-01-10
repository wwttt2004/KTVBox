package com.yiqiding.ktvbox.infomanage;

import android.os.Parcel;
import android.os.Parcelable;

public class UserInfoStructure implements Parcelable{
	private int uid;
	private String name;
	private String dob;
	private int gender;
	private String reg;

	public UserInfoStructure()
	{
		
	}
	
	public int getUid()
	{
		return this.uid;
	}
	
	public void setUid(int uid)
	{
		this.uid = uid;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getDob()
	{
		return this.dob;
	}
	
	public void setDob(String dob)
	{
		this.dob = dob;
	}
	
	public int getGender()
	{
		return this.gender;
	}
	
	public void setGender(int gender)
	{
		this.gender = gender;
	}
	
	public String getReg()
	{
		return this.reg;
	}
	
	public void setReg(String reg)
	{
		this.reg = reg;
	}
	
	public UserInfoStructure(int uid, String name, String dob, int gender, String reg)
	{
		this.uid = uid;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.reg = reg;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(uid);
		dest.writeString(name);
		dest.writeString(dob);
		dest.writeInt(gender);
		dest.writeString(reg);
	}

	public static final Parcelable.Creator<UserInfoStructure> CREATOR = new Parcelable.Creator<UserInfoStructure>() {

		@Override
		public UserInfoStructure createFromParcel(Parcel source) {
			return new UserInfoStructure(source.readInt(), source.readString(), source.readString(), source.readInt(), source.readString());
		}

		@Override
		public UserInfoStructure[] newArray(int size) {
			return new UserInfoStructure[size];
		}
	};
}
