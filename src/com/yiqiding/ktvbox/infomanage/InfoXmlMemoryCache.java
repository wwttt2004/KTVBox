package com.yiqiding.ktvbox.infomanage;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

import android.os.Bundle;
import android.support.v4.util.LruCache;

public class InfoXmlMemoryCache {
    private final int HARD_CACHE_SIZE = 4*1024*1024; //4M
    private final int SOFT_CACHE_SIZE = 100;
    private LruCache<String, String> mLruCache;
    private LinkedHashMap<String, SoftReference<String>> mSoftCache;

	public InfoXmlMemoryCache()
    {
    	mLruCache = new LruCache<String, String>(HARD_CACHE_SIZE) {
            @Override
            protected int sizeOf(String key, String value) {
                if (value != null)
                    return value.length();
                else
                    return 0;
            }

            @Override
            protected void entryRemoved(boolean evicted, String key, String oldValue, String newValue) {
                if (oldValue != null) {
                	mSoftCache.put(key, new SoftReference<String>(oldValue));
                }
            }
        };

        mSoftCache = new LinkedHashMap<String, SoftReference<String>>(SOFT_CACHE_SIZE, 0.75f, true) {
            private static final long serialVersionUID = 6040103833179403725L;
            @Override
            protected boolean removeEldestEntry(Entry<String, SoftReference<String>> eldest) {
                if (size() > SOFT_CACHE_SIZE) {
                    return true;
                }
                return false;
            }
        };
    }
	
    public String getEntryFromCache(String key) {
        String info;
        synchronized (mLruCache) {
        	info = mLruCache.get(key);
            if (info != null) {
                mLruCache.remove(key);
                mLruCache.put(key, info);
                return info;
            }
        }

        synchronized (mSoftCache) {
            SoftReference<String> entry = mSoftCache.get(key);
            if (entry != null) {
                info = entry.get();
                if (info != null) {
                    mLruCache.put(key, info);
                    mSoftCache.remove(key);
                    return info;
                } else {
                    mSoftCache.remove(key);
                }
            }
        }
        return null;
    }

    public void addEntryToCache(String key, String info) {
        if (info != null) {
            synchronized (mLruCache) {
                mLruCache.put(key, info);
            }
        }
    }
                                                                                  
    public void clearCache() {
    	synchronized (mSoftCache) {
    		mSoftCache.clear();
		}
    }
    
    public static String getCacheKey_For_SystemApkFunction(Bundle requestInfo)
    {
		StringBuilder infoCacheKeyBuilder = new StringBuilder();
		infoCacheKeyBuilder.append(InfoFunctionInterface.functionnum_system_apk);
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.boxid));
    	
    	return infoCacheKeyBuilder.toString();
    }
    
    public static String getCacheKey_For_SystemRoomFunction(Bundle requestInfo)
    {
		StringBuilder infoCacheKeyBuilder = new StringBuilder();
		infoCacheKeyBuilder.append(InfoFunctionInterface.functionnum_system_room);
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.boxid));
    	
    	return infoCacheKeyBuilder.toString();
    }
    
    public static String getCacheKey_For_MusicListFunction(Bundle requestInfo)
    {
		StringBuilder infoCacheKeyBuilder = new StringBuilder();
		infoCacheKeyBuilder.append(InfoFunctionInterface.functionnum_music_list);
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.name));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.returntype));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.number));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.page));
    	
    	return infoCacheKeyBuilder.toString();
    }
    
    public static String getCacheKey_For_MusicFilterFunction(Bundle requestInfo)
    {
		StringBuilder infoCacheKeyBuilder = new StringBuilder();
		infoCacheKeyBuilder.append(InfoFunctionInterface.functionnum_music_filter);
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.singer));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.type));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.language));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.number));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.page));
    	
    	return infoCacheKeyBuilder.toString();
    }
    
    public static String getCacheKey_For_AlbumFilterFunction(Bundle requestInfo)
    {
		StringBuilder infoCacheKeyBuilder = new StringBuilder();
		infoCacheKeyBuilder.append(InfoFunctionInterface.functionnum_album_filter);
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.singer));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.type));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.language));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.number));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.page));
    	
    	return infoCacheKeyBuilder.toString();
    }
    
    public static String getCacheKey_For_ActivityFilterFunction(Bundle requestInfo)
    {
		StringBuilder infoCacheKeyBuilder = new StringBuilder();
		infoCacheKeyBuilder.append(InfoFunctionInterface.functionnum_activity_filter);
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.type));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.number));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.page));
    	
    	return infoCacheKeyBuilder.toString();
    }
    
    public static String getCacheKey_For_MusicVideoFunction(Bundle requestInfo)
    {
		StringBuilder infoCacheKeyBuilder = new StringBuilder();
		infoCacheKeyBuilder.append(InfoFunctionInterface.functionnum_music_video);
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.musicid));
    	
    	return infoCacheKeyBuilder.toString();
    }
    
    public static String getCacheKey_For_MusicFunction(Bundle requestInfo)
    {
		StringBuilder infoCacheKeyBuilder = new StringBuilder();
		infoCacheKeyBuilder.append(InfoFunctionInterface.functionnum_music);
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.musicid));
    	
    	return infoCacheKeyBuilder.toString();
    }
    
    public static String getCacheKey_For_AlbumFunction(Bundle requestInfo)
    {
		StringBuilder infoCacheKeyBuilder = new StringBuilder();
		infoCacheKeyBuilder.append(InfoFunctionInterface.functionnum_album);
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.albumid));
    	
    	return infoCacheKeyBuilder.toString();
    }
    
    public static String getCacheKey_For_SingerFunction(Bundle requestInfo)
    {
		StringBuilder infoCacheKeyBuilder = new StringBuilder();
		infoCacheKeyBuilder.append(InfoFunctionInterface.functionnum_singer);
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.singerid));
    	
    	return infoCacheKeyBuilder.toString();
    }
    
    public static String getCacheKey_For_SingerMusicFunction(Bundle requestInfo)
    {
		StringBuilder infoCacheKeyBuilder = new StringBuilder();
		infoCacheKeyBuilder.append(InfoFunctionInterface.functionnum_singer_music);
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.singerid));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.number));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.page));
    	
    	return infoCacheKeyBuilder.toString();
    }
    
    public static String getCacheKey_For_SingerAlbumFunction(Bundle requestInfo)
    {
		StringBuilder infoCacheKeyBuilder = new StringBuilder();
		infoCacheKeyBuilder.append(InfoFunctionInterface.functionnum_singer_album);
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.singerid));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.number));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.page));
    	
    	return infoCacheKeyBuilder.toString();
    }
    
    public static String getCacheKey_For_SearchSuggestFunction(Bundle requestInfo)
    {
		StringBuilder infoCacheKeyBuilder = new StringBuilder();
		infoCacheKeyBuilder.append(InfoFunctionInterface.functionnum_search_suggest);
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getString(InfoFunctionInterface.keyword));
    	
    	return infoCacheKeyBuilder.toString();
    }
    
    public static String getCacheKey_For_SearchMusicFunction(Bundle requestInfo)
    {
		StringBuilder infoCacheKeyBuilder = new StringBuilder();
		infoCacheKeyBuilder.append(InfoFunctionInterface.functionnum_search_music);
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getString(InfoFunctionInterface.keyword));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.type));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.language));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.number));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.page));
    	
    	return infoCacheKeyBuilder.toString();
    }
    
    public static String getCacheKey_For_SearchAlbumFunction(Bundle requestInfo)
    {
		StringBuilder infoCacheKeyBuilder = new StringBuilder();
		infoCacheKeyBuilder.append(InfoFunctionInterface.functionnum_search_album);
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getString(InfoFunctionInterface.keyword));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.type));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.language));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.number));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.page));
    	
    	return infoCacheKeyBuilder.toString();
    }
    
    public static String getCacheKey_For_SearchSingerFunction(Bundle requestInfo)
    {
		StringBuilder infoCacheKeyBuilder = new StringBuilder();
		infoCacheKeyBuilder.append(InfoFunctionInterface.functionnum_search_singer);
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getString(InfoFunctionInterface.keyword));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.type));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.nation));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.number));
		infoCacheKeyBuilder.append('_');
		infoCacheKeyBuilder.append(requestInfo.getInt(InfoFunctionInterface.page));
    	
    	return infoCacheKeyBuilder.toString();
    }
    
    public static String getCacheKey_For_UserInfoFunction(Bundle requestInfo)
    {
		StringBuilder infoCacheKeyBuilder = new StringBuilder();
		infoCacheKeyBuilder.append(InfoFunctionInterface.functionnum_user_info);
    	
    	return infoCacheKeyBuilder.toString();
    }
}
