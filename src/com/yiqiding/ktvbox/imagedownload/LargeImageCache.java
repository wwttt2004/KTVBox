package com.yiqiding.ktvbox.imagedownload;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap;

import com.yiqiding.ktvbox.globalcontrol.ApplicationExtend;

public class LargeImageCache {
	private static final int DISK_CACHE_SIZE = 1024 * 1024 * 20; // 20MB
	private static final String DISK_CACHE_SUBDIR = "LargeImage";
	public DiskLruCache mDiskCache;

	public LargeImageCache() {
		Context context = ApplicationExtend.getInstance()
				.getApplicationContext();
		File cacheDir = DiskLruCache
				.getDiskCacheDir(context, DISK_CACHE_SUBDIR);
		mDiskCache = DiskLruCache.openCache(context, cacheDir, DISK_CACHE_SIZE);
	}

	public void put(String key, Bitmap bitmap) {
		mDiskCache.put(key, bitmap);
	}

	public Bitmap get(String key) {
		return mDiskCache.get(key);
	}
	
	public void clear()
	{
		mDiskCache.clearCache();
	}
}
