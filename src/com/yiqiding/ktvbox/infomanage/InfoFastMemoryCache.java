package com.yiqiding.ktvbox.infomanage;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.LruCache;

@SuppressLint("NewApi")
public class InfoFastMemoryCache {
    private final int HARD_CACHE_SIZE = 100;
    private final int SOFT_CACHE_SIZE = 100;
    private LruCache<String, Bundle> mLruCache;
    private LinkedHashMap<String, SoftReference<Bundle>> mSoftCache;

	@SuppressLint("NewApi")
	public InfoFastMemoryCache()
    {
    	mLruCache = new LruCache<String, Bundle>(HARD_CACHE_SIZE) {
            @Override
            protected int sizeOf(String key, Bundle value) {
                if (value != null)
                    return 1;
                else
                    return 0;
            }

            @Override
            protected void entryRemoved(boolean evicted, String key, Bundle oldValue, Bundle newValue) {
                if (oldValue != null) {
                	mSoftCache.put(key, new SoftReference<Bundle>(oldValue));
                }
            }
        };

        mSoftCache = new LinkedHashMap<String, SoftReference<Bundle>>(SOFT_CACHE_SIZE, 0.75f, true) {
            private static final long serialVersionUID = 6040103833179403725L;
            @Override
            protected boolean removeEldestEntry(Entry<String, SoftReference<Bundle>> eldest) {
                if (size() > SOFT_CACHE_SIZE) {
                    return true;
                }
                return false;
            }
        };
    }

	public Bundle getEntryFromCache(String key) {
        Bundle info;
        synchronized (mLruCache) {
        	info = mLruCache.get(key);
            if (info != null) {
                mLruCache.remove(key);
                mLruCache.put(key, info);
                return info;
            }
        }

        synchronized (mSoftCache) {
            SoftReference<Bundle> entry = mSoftCache.get(key);
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

    public void addEntryToCache(String key, Bundle info) {
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
}
