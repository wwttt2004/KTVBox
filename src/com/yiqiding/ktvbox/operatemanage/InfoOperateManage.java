package com.yiqiding.ktvbox.operatemanage;

import java.util.Stack;

import android.os.Bundle;

import com.yiqiding.ktvbox.infomanage.InfoXmlMemoryCache;
import com.yiqiding.ktvbox.infomanage.InfoFunctionInterface;
import com.yiqiding.ktvbox.infomanage.InfoModuleConstant;
import com.yiqiding.ktvbox.infomanage.InfoRequestTask;

public class InfoOperateManage {
	public static Stack<Bundle> infoOperateStack = new Stack<Bundle>();
	public static int lastInfoOperateTimeStamp = 0x00000000;
	
	
	public static void requestSystemApkInfo(int boxid)
	{
		Bundle info = InfoFunctionInterface.Create_RequestInfo_For_SystemApkFunction(boxid);

		InfoOperateManage.requestInfo(InfoFunctionInterface.functionnum_system_apk, info, InfoXmlMemoryCache.getCacheKey_For_SystemApkFunction(info));
	}
	
	public static void requestSystemRoomInfo(int boxid)
	{
		Bundle info = InfoFunctionInterface.Create_RequestInfo_For_SystemRoomFunction(boxid);
		
		InfoOperateManage.requestInfo(InfoFunctionInterface.functionnum_system_room, info, InfoXmlMemoryCache.getCacheKey_For_SystemRoomFunction(info));
	}
	
	public static void requestMusicListInfo(int name, int returntype, int number, int page)
	{
		Bundle info = InfoFunctionInterface.Create_RequestInfo_For_MusicListFunction(name, returntype, number, page);
		InfoOperateManage.requestInfo(InfoFunctionInterface.functionnum_music_list, info, InfoXmlMemoryCache.getCacheKey_For_MusicListFunction(info));
	}
	
	public static void requestMusicFilterInfo(int singer, int type, int language, int number, int page)
	{
		Bundle info = InfoFunctionInterface.Create_RequestInfo_For_MusicFilterFunction(singer, type, language, number, page);
		InfoOperateManage.requestInfo(InfoFunctionInterface.functionnum_music_filter, info, InfoXmlMemoryCache.getCacheKey_For_MusicFilterFunction(info));
	}
	
	public static void requestAlbumFilterInfo(int singer, int type, int language, int number, int page)
	{
		Bundle info = InfoFunctionInterface.Create_RequestInfo_For_AlbumFilterFunction(singer, type, language, number, page);
		InfoOperateManage.requestInfo(InfoFunctionInterface.functionnum_album_filter, info, InfoXmlMemoryCache.getCacheKey_For_AlbumFilterFunction(info));
	}
	
	public static void requestActivityFilterInfo(int type, int number, int page)
	{
		Bundle info = InfoFunctionInterface.Create_RequestInfo_For_ActivityFilterFunction(type, number, page);
		InfoOperateManage.requestInfo(InfoFunctionInterface.functionnum_activity_filter, info, InfoXmlMemoryCache.getCacheKey_For_ActivityFilterFunction(info));
	}
	
	public static void requestMusicVideoInfo(int musicid)
	{
		Bundle info = InfoFunctionInterface.Create_RequestInfo_For_MusicVideoFunction(musicid);
		InfoOperateManage.requestInfo(InfoFunctionInterface.functionnum_music_video, info, InfoXmlMemoryCache.getCacheKey_For_MusicVideoFunction(info));
	}
	
	public static void requestMusicInfo(int musicid)
	{
		Bundle info = InfoFunctionInterface.Create_RequestInfo_For_MusicFunction(musicid);
		InfoOperateManage.requestInfo(InfoFunctionInterface.functionnum_music, info, InfoXmlMemoryCache.getCacheKey_For_MusicFunction(info));
	}
	
	public static void requestAlbumInfo(int albumid)
	{
		Bundle info = InfoFunctionInterface.Create_RequestInfo_For_AlbumFunction(albumid);
		InfoOperateManage.requestInfo(InfoFunctionInterface.functionnum_album, info, InfoXmlMemoryCache.getCacheKey_For_AlbumFunction(info));
	}
	
	public static void requestSingerInfo(int singerid)
	{
		Bundle info = InfoFunctionInterface.Create_RequestInfo_For_SingerFunction(singerid);
		InfoOperateManage.requestInfo(InfoFunctionInterface.functionnum_singer, info, InfoXmlMemoryCache.getCacheKey_For_SingerFunction(info));
	}

	public static void requestSingerMusicInfo(int singerid, int number, int page)
	{
		Bundle info = InfoFunctionInterface.Create_RequestInfo_For_SingerMusicFunction(singerid, number, page);
		InfoOperateManage.requestInfo(InfoFunctionInterface.functionnum_singer_music, info, InfoXmlMemoryCache.getCacheKey_For_SingerMusicFunction(info));
	}
	
	public static void requestSingerAlbumInfo(int singerid, int number, int page)
	{
		Bundle info = InfoFunctionInterface.Create_RequestInfo_For_SingerAlbumFunction(singerid, number, page);
		InfoOperateManage.requestInfo(InfoFunctionInterface.functionnum_singer_album, info, InfoXmlMemoryCache.getCacheKey_For_SingerMusicFunction(info));
	}
	
	public static void requestSearchSuggestInfo(String keyword)
	{
		Bundle info = InfoFunctionInterface.Create_RequestInfo_For_SearchSuggestFunction(keyword);
		InfoOperateManage.requestInfo(InfoFunctionInterface.functionnum_search_suggest, info, InfoXmlMemoryCache.getCacheKey_For_SearchSuggestFunction(info));
	}
	
	public static void requestSearchMusicInfo(String keyword, int type, int language, int number, int page)
	{
		Bundle info = InfoFunctionInterface.Create_RequestInfo_For_SearchMusicFunction(keyword, type, language, number, page);
		InfoOperateManage.requestInfo(InfoFunctionInterface.functionnum_search_music, info, InfoXmlMemoryCache.getCacheKey_For_SearchMusicFunction(info));
	}
	
	public static void requestSearchAlbumInfo(String keyword, int type, int language, int number, int page)
	{
		Bundle info = InfoFunctionInterface.Create_RequestInfo_For_SearchAlbumFunction(keyword, type, language, number, page);
		InfoOperateManage.requestInfo(InfoFunctionInterface.functionnum_search_album, info, InfoXmlMemoryCache.getCacheKey_For_SearchAlbumFunction(info));
	}
	
	public static void requestSearchSingerInfo(String keyword, int type, int nation, int number, int page)
	{
		Bundle info = InfoFunctionInterface.Create_RequestInfo_For_SearchSingerFunction(keyword, type, nation, number, page);
		InfoOperateManage.requestInfo(InfoFunctionInterface.functionnum_search_singer, info, InfoXmlMemoryCache.getCacheKey_For_SearchSingerFunction(info));
	}
	
	public static void requestUserInfo()
	{
		InfoOperateManage.requestInfo(InfoFunctionInterface.functionnum_user_info, null, InfoXmlMemoryCache.getCacheKey_For_UserInfoFunction(null));
	}
	
	public static void requestInfo(int infoFunctionNum, Bundle info, String infoCacheKey)
	{
		Bundle functionInfo = new Bundle();
		if (InfoOperateManage.lastInfoOperateTimeStamp>=0xFFFFFFFF) {
			InfoOperateManage.lastInfoOperateTimeStamp = 0x00000000;
		}
		InfoOperateManage.lastInfoOperateTimeStamp++;
		functionInfo.putInt(InfoModuleConstant.functionNumKey, infoFunctionNum);
		functionInfo.putInt(InfoModuleConstant.functionRequestTimeStampKey, InfoOperateManage.lastInfoOperateTimeStamp);
		functionInfo.putBundle(InfoModuleConstant.functionRequestInfoKey, info);
		functionInfo.putString(InfoModuleConstant.infoMemoryCacheKeyKey, infoCacheKey);

		//push functionInfo to stack
		infoOperateStack.push(functionInfo);
		
		if(!InfoRequestTask.taskStatus){
			InfoRequestTask.triggerNewTask();
		}
	}
}

