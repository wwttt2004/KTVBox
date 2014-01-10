package com.yiqiding.ktvbox.imagedownload;

import java.io.File;

import com.yiqiding.ktvbox.globalcontrol.ApplicationExtend;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class SmallImageCache {

    private final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    // Use 1/8th of the available memory for this memory cache.
    private final int cacheSize = maxMemory / 8;
	private static LruCache<String, Bitmap> mLruCache; // 硬引用缓存

	private static final int DISK_CACHE_SIZE = 1024 * 1024 * 6; // 6MB
	private static final String DISK_CACHE_SUBDIR = "SmallImage";
	private DiskLruCache mDiskCache = null;

	public SmallImageCache() {
		mLruCache = new LruCache<String, Bitmap>(cacheSize) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				if (value != null)
					return value.getRowBytes() * value.getHeight() / 1024;
				else
					return 0;
			}

			@Override
			protected void entryRemoved(boolean evicted, String key,
					Bitmap oldValue, Bitmap newValue) {
				if (oldValue != null)
					// 硬引用缓存容量满的时候，会根据LRU算法把最近没有被使用的图片转入硬盘缓存
					mDiskCache.put(key, oldValue);
			}
		};

		Context context = ApplicationExtend.getInstance()
				.getApplicationContext();
		File cacheDir = DiskLruCache
				.getDiskCacheDir(context, DISK_CACHE_SUBDIR);
		mDiskCache = DiskLruCache.openCache(context, cacheDir, DISK_CACHE_SIZE);
	}

	/**
	 * 从缓存中获取图片
	 */
	public Bitmap getBitmapFromCache(String key) {
		Bitmap bitmap;
		// 先从硬引用缓存中获取
		synchronized (mLruCache) {
			bitmap = mLruCache.get(key);
			if (bitmap != null) {
				// 如果找到的话，把元素移到LinkedHashMap的最前面，从而保证在LRU算法中是最后被删除
				mLruCache.remove(key);
				mLruCache.put(key, bitmap);
				return bitmap;
			}
		}
		// 如果硬引用缓存中找不到，到硬盘缓存中找
		synchronized (mDiskCache) {
			bitmap = mDiskCache.get(key);
			if (bitmap != null) {
				// 将图片移回硬缓存
				mLruCache.put(key, bitmap);
				mDiskCache.remove(key);
				return bitmap;
			}
		}
		return null;
	}

	/**
	 * 添加图片到缓存
	 */
	public void addBitmapToCache(String key, Bitmap bitmap) {
		if (bitmap != null) {
			synchronized (mLruCache) {
				mLruCache.put(key, bitmap);
			}
		}
	}
	
	public void clear()
	{
		mDiskCache.clearCache();
	}
}
