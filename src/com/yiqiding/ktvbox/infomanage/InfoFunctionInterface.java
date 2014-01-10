package com.yiqiding.ktvbox.infomanage;

import android.os.Bundle;

public class InfoFunctionInterface {
	// info server ip address and port
	public static String serveripAddress = "192.168.1.1";
	public static int serverport = 58849;
	
	// function number
	public static final int functionnum_system_apk = 10001;
	public static final int functionnum_system_room = 10002;
	public static final int functionnum_music_list = 10003;
	public static final int functionnum_music_filter = 10004;
	public static final int functionnum_album_filter = 10005;
	public static final int functionnum_activity_filter = 10006;
	public static final int functionnum_music_video = 10007;
	public static final int functionnum_music = 10008;
	public static final int functionnum_album = 10009;
	public static final int functionnum_singer = 10010;
	public static final int functionnum_singer_music = 10011;
	public static final int functionnum_singer_album = 10012;
	public static final int functionnum_search_suggest = 10013;
	public static final int functionnum_search_music = 10014;
	public static final int functionnum_search_album = 10015;
	public static final int functionnum_search_singer = 10016;
	public static final int functionnum_user_info = 10017;
	
	// variable key
	public static String boxid = "boxid";
	public static String path = "path";
	public static String room = "room";
	public static String name = "name";
	public static String type = "type";
	public static String returntype = "returntype";
	public static String number = "number";
	public static String page = "page";
	public static String list = "list";
	public static String total = "total";
	public static String singer = "singer";
	public static String language = "language";
	public static String musicid = "musicid";
	public static String orginal = "orginal";
	public static String accompaniment = "accompaniment";
	public static String videotype = "videotype";
	public static String audiotype = "audiotype";
	public static String backup = "backup";
	public static String music = "music";
	public static String albumid = "albumid";
	public static String album = "album";
	public static String singerid = "singerid";
	public static String musics = "musics";
	public static String albums = "albums";
	public static String keyword = "keyword";
	public static String words = "words";
	public static String word = "word";
	public static String nation = "nation"; 
	public static String user = "user";
	public static String release = "release";
	public static String detail = "detail";
	public static String stars = "stars";
	public static String count = "count";
	public static String albuminfo = "albuminfo";
	public static String singers = "singers";
	public static String country = "country";
	public static String length = "length";
	public static String image = "image";
	public static String activitys = "activitys";
	public static String activity = "activity";
	public static String activityid = "activityid";
	public static String title = "title";
	public static String description = "description";
	public static String uid = "uid";
	public static String dob = "dob";
	public static String gender = "gender";
	public static String reg = "reg";
	
	//error key
	public static String status = "status";
	public static String error = "error";
	
	//enum (return type)
	public static final int enum_returntype_music = 0;
	public static final int enum_returntype_album = 1;
	
	//enum (singer type)
	public static final int enum_singer_type_girlgroup = 0;
	public static final int enum_singer_type_boygroup = 1;
	public static final int enum_singer_type_group = 2;
	public static final int enum_singer_type_female = 3;
	public static final int enum_singer_type_male = 4;
	
	//enum (nation)
	public static final int enum_nation_taiwan = 0;
	public static final int enum_nation_china = 1;
	public static final int enum_nation_japan = 2;
	public static final int enum_nation_korea = 3;
	public static final int enum_nation_singapore = 4;
	public static final int enum_nation_Malaysia = 5;
	public static final int enum_nation_America = 6;
	public static final int enum_nation_Europe = 7;
	
	//enum (country)
	public static final int enum_country_taiwan = 0;
	public static final int enum_country_china = 1;
	public static final int enum_country_japan = 2;
	public static final int enum_country_korea = 3;
	public static final int enum_country_singapore = 4;
	public static final int enum_country_Malaysia = 5;
	public static final int enum_country_America = 6;
	public static final int enum_country_Europe = 7;
	
	//enum (language)
	public static final int enum_language_simplifiedchinese = 0;
	public static final int enum_language_traditionalchinese = 1;
	public static final int enum_language_japanese = 2;
	public static final int enum_language_korean = 3;
	public static final int enum_language_engish = 4;
	public static final int enum_language_cantonese = 5;
	public static final int enum_language_hokkien = 6;
	public static final int enum_language_other = 7;
	
	//enum (music type)
	public static final int enum_music_type_web = 0;
	public static final int enum_music_type_couple = 1;
	public static final int enum_music_type_child = 2;
	public static final int enum_music_type_opera = 3;
	public static final int enum_music_type_beloved = 4;
	public static final int enum_music_type_gcd = 5;
	public static final int enum_music_type_birthday = 6;
	public static final int enum_music_type_friend = 7;
	public static final int enum_music_type_party = 8;
	
	// enum (activity type)
	public static final int enum_activity_type_ktv = 0;
	public static final int enum_activity_type_yqc = 1;
	
	public static final int enum_user_gender_male = 0;
	public static final int enum_user_gender_female = 1;
	
	//create request info
	public static Bundle Create_RequestInfo_For_SystemApkFunction(int boxid)
	{
		Bundle bundle = new Bundle();
		bundle.putInt(InfoFunctionInterface.boxid, boxid);
		return bundle;
	}

	public static Bundle Create_RequestInfo_For_SystemRoomFunction(int boxid)
	{
		Bundle bundle = new Bundle();
		bundle.putInt(InfoFunctionInterface.boxid, boxid);
		return bundle;
	}
	
	public static Bundle Create_RequestInfo_For_MusicListFunction(int name, int returntype, int number, int page)
	{
		Bundle bundle = new Bundle();
		bundle.putInt(InfoFunctionInterface.name, name);
		bundle.putInt(InfoFunctionInterface.returntype, returntype);
		bundle.putInt(InfoFunctionInterface.number, number);
		bundle.putInt(InfoFunctionInterface.page, page);
		
		return bundle;
	}
	
	public static Bundle Create_RequestInfo_For_MusicFilterFunction(int singer, int type, int language, int number, int page)
	{
		Bundle bundle = new Bundle();
		bundle.putInt(InfoFunctionInterface.singer, singer);
		bundle.putInt(InfoFunctionInterface.type, type);
		bundle.putInt(InfoFunctionInterface.language, language);
		bundle.putInt(InfoFunctionInterface.number, number);
		bundle.putInt(InfoFunctionInterface.page, page);
		
		return bundle;
	}
	
	public static Bundle Create_RequestInfo_For_AlbumFilterFunction(int singer, int type, int language, int number, int page)
	{
		Bundle bundle = new Bundle();
		bundle.putInt(InfoFunctionInterface.singer, singer);
		bundle.putInt(InfoFunctionInterface.type, type);
		bundle.putInt(InfoFunctionInterface.language, language);
		bundle.putInt(InfoFunctionInterface.number, number);
		bundle.putInt(InfoFunctionInterface.page, page);
		
		return bundle;
	}
	
	public static Bundle Create_RequestInfo_For_ActivityFilterFunction(int type, int number, int page)
	{
		Bundle bundle = new Bundle();
		bundle.putInt(InfoFunctionInterface.type, type);
		bundle.putInt(InfoFunctionInterface.number, number);
		bundle.putInt(InfoFunctionInterface.page, page);
		
		return bundle;
	}
	
	public static Bundle Create_RequestInfo_For_MusicVideoFunction(int musicid)
	{
		Bundle bundle = new Bundle();
		bundle.putInt(InfoFunctionInterface.musicid, musicid);
		return bundle;
	}
	
	public static Bundle Create_RequestInfo_For_MusicFunction(int musicid)
	{
		Bundle bundle = new Bundle();
		bundle.putInt(InfoFunctionInterface.musicid, musicid);
		return bundle;
	}
	
	public static Bundle Create_RequestInfo_For_AlbumFunction(int albumid)
	{
		Bundle bundle = new Bundle();
		bundle.putInt(InfoFunctionInterface.albumid, albumid);
		return bundle;
	}
	
	public static Bundle Create_RequestInfo_For_SingerFunction(int singerid)
	{
		Bundle bundle = new Bundle();
		bundle.putInt(InfoFunctionInterface.singerid, singerid);
		return bundle;
	}
	
	public static Bundle Create_RequestInfo_For_SingerMusicFunction(int singerid, int number, int page)
	{
		Bundle bundle = new Bundle();
		bundle.putInt(InfoFunctionInterface.singerid, singerid);
		bundle.putInt(InfoFunctionInterface.number, number);
		bundle.putInt(InfoFunctionInterface.page, page);
		
		return bundle;
	}
	
	public static Bundle Create_RequestInfo_For_SingerAlbumFunction(int singerid, int number, int page)
	{
		Bundle bundle = new Bundle();
		bundle.putInt(InfoFunctionInterface.singerid, singerid);
		bundle.putInt(InfoFunctionInterface.number, number);
		bundle.putInt(InfoFunctionInterface.page, page);
		
		return bundle;
	}
	
	public static Bundle Create_RequestInfo_For_SearchSuggestFunction(String keyword)
	{
		Bundle bundle = new Bundle();
		bundle.putString(InfoFunctionInterface.keyword, keyword);
		
		return bundle;
	}
	
	public static Bundle Create_RequestInfo_For_SearchMusicFunction(String keyword, int type, int language, int number, int page)
	{
		Bundle bundle = new Bundle();
		bundle.putString(InfoFunctionInterface.keyword, keyword);
		bundle.putInt(InfoFunctionInterface.type, type);
		bundle.putInt(InfoFunctionInterface.language, language);
		bundle.putInt(InfoFunctionInterface.number, number);
		bundle.putInt(InfoFunctionInterface.page, page);
		
		return bundle;
	}
	
	public static Bundle Create_RequestInfo_For_SearchAlbumFunction(String keyword, int type, int language, int number, int page)
	{
		Bundle bundle = new Bundle();
		bundle.putString(InfoFunctionInterface.keyword, keyword);
		bundle.putInt(InfoFunctionInterface.type, type);
		bundle.putInt(InfoFunctionInterface.language, language);
		bundle.putInt(InfoFunctionInterface.number, number);
		bundle.putInt(InfoFunctionInterface.page, page);
		
		return bundle;
	}
	
	public static Bundle Create_RequestInfo_For_SearchSingerFunction(String keyword, int type, int nation, int number, int page)
	{
		Bundle bundle = new Bundle();
		bundle.putString(InfoFunctionInterface.keyword, keyword);
		bundle.putInt(InfoFunctionInterface.type, type);
		bundle.putInt(InfoFunctionInterface.nation, nation);
		bundle.putInt(InfoFunctionInterface.number, number);
		bundle.putInt(InfoFunctionInterface.page, page);
		
		return bundle;
	}
	
	public static Bundle Create_RequestInfo_For_UserInfoFunction()
	{
		return null;
	}
}