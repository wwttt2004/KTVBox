package com.yiqiding.ktvbox.imagedownload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.yiqiding.ktvbox.globalcontrol.ApplicationExtend;
import com.yiqiding.ktvbox.globalcontrol.MainThreadMessageID;
import com.yiqiding.ktvbox.util.StringUtil;
import com.yiqiding.ktvbox.util.ThreadUtil;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

public class ImageDownloadDispatchThread extends HandlerThread{
	
	private static ImageDownloadDispatchThread instance = null;	
	
	/** default count for preload forward **/
	private static final int DEFAULT_FORWARD_CACHE_NUMBER = 3;
	/** default count for preload backward **/
	private static final int DEFAULT_BACKWARD_CACHE_NUMBER = 1;
	
	private Handler handler;
	
	private LargeImageCache largeImageCache;
	private SmallImageCache smallImageCache;
	
	public static ExecutorService imageDownloadFixedThreadPool = Executors.newFixedThreadPool(ThreadUtil.getDefaultThreadPoolSize(8));

	private HashMap<String, ImageDownloader> imageDownloaderHashMap = new HashMap<String, ImageDownloader>();
	
	private ImageDownloadDispatchThread(String name) {
		super(name);
		largeImageCache = new LargeImageCache();
		smallImageCache = new SmallImageCache();
	}
	
	public static ImageDownloadDispatchThread getInstance()
	{
		if (instance==null) {
			instance = new ImageDownloadDispatchThread("ImageDownloadDispatchThread");
		}
		
		return instance;
	}
	
	public void startThread()
	{
		this.start();
		handler = new Handler(this.getLooper())
		{
			public void handleMessage(Message msg)
			{
				if (msg == null)
					return;
				switch (msg.what) {
				case ImageDownloadDispatchThreadMessageId.MESSAGE_ID_IMAGEDOWNLOAD_ADDONE:
					handleImageDownloadRequest(msg.getData());
					break;
				case ImageDownloadDispatchThreadMessageId.MESSAGE_ID_IMAGEDOWNLOAD_FINISHED:
					if (msg.obj != null && msg.obj instanceof String) {
						handleImageDownloadFinished((String)msg.obj);
					}
					break;
				case ImageDownloadDispatchThreadMessageId.MESSAGE_ID_IMAGEDOWNLOAD_SWITCHSERVER:
					if (msg.obj != null && msg.obj instanceof String)
					{
						switchServer((String)msg.obj);
					}
					break;
				case ImageDownloadDispatchThreadMessageId.MESSAGE_ID_IMAGEDOWNLOAD_CLEAN_IMAGESTORAGE:
					smallImageCache.clear();
					largeImageCache.clear();
					break;
				default:
					break;
				}
			}
		};
	}
	
	public Handler getHandler()
	{
		return handler;
	}
	
	private void handleImageDownloadRequest(Bundle bundle)
	{
		String imageUrl = bundle.getString(ImageDownloadModuleConstant.imageUrlKey);
		ArrayList<String> urlList = bundle.getStringArrayList(ImageDownloadModuleConstant.urlListKey);
		Boolean isLargaeImage = bundle.getBoolean(ImageDownloadModuleConstant.isLargeImageKey);
		
		this.requestImage(imageUrl, isLargaeImage, true);
		
		this.preloadDataForward(imageUrl, urlList, isLargaeImage, DEFAULT_FORWARD_CACHE_NUMBER);
		this.preloadDataBackward(imageUrl, urlList, isLargaeImage, DEFAULT_BACKWARD_CACHE_NUMBER);
		
	}
	
	private void handleImageDownloadFinished(String imageId)
	{
		ImageDownloader imageDownloader = imageDownloaderHashMap.remove(imageId);
		if (imageDownloader.getIsLargeImage()) {
			largeImageCache.put(imageId, imageDownloader.getBitmap());
		}else {
			smallImageCache.addBitmapToCache(imageId, imageDownloader.getBitmap());
		}
		
		if (imageDownloader.getIsNeedUpdateUI()) {
			// send message to main thread
			sendImageToUIThreadUpdate(imageId, imageDownloader.getBitmap());
		}
	}
	
	private void preloadDataForward(String imageUrl, ArrayList<String> urlList, Boolean isLargaeImage, int cacheCount)
	{
        if (imageUrl != null && !urlList.isEmpty() && cacheCount>0) {
            int cachedCount = 0;
            boolean beginCount = false;
            for (int i = 0; i < urlList.size() && cachedCount <= cacheCount; i++) {
                String k = urlList.get(i);
                if (!beginCount) {
                    if (imageUrl.equals(k)) {
                        beginCount = true;
                        continue;
                    }
				}

                if (k != null && beginCount) {
                    cachedCount++;
                    this.requestImage(k, isLargaeImage, false);
                }
            }
        }
	}
	
	private void preloadDataBackward(String imageUrl, ArrayList<String> urlList, Boolean isLargaeImage, int cacheCount)
	{
        if (imageUrl != null && !urlList.isEmpty() && cacheCount>0) {
            int cachedCount = 0;
            boolean beginCount = false;
            for (int i = urlList.size() - 1; i >= 0 && cachedCount <= cacheCount; i--) {
                String k = urlList.get(i);
                if (!beginCount) {
                    if (imageUrl.equals(k)) {
                        beginCount = true;
                        continue;
                    }
				}

                if (k != null && beginCount) {
                    cachedCount++;
                    this.requestImage(k, isLargaeImage, false);
                }
            }
        }
	}

	private void requestImage(String imageUrl, Boolean isLargaeImage, Boolean isNeedUpdateUI)
	{
		if (isLargaeImage) {
			this.requestLargeImage(imageUrl, isNeedUpdateUI);
		}else {
			this.requestSmallImage(imageUrl, isNeedUpdateUI);
		}
	}
	
	private void requestLargeImage(String imageUrl, Boolean isNeedUpdateUI)
	{
		String imageId = StringUtil.imageUrl_2_ImageName(imageUrl);
		Bitmap bitmap = null;
		bitmap = largeImageCache.get(imageId);
		if (bitmap!=null) {
			if (isNeedUpdateUI) {
				// send message to main thread
				sendImageToUIThreadUpdate(imageId, bitmap);
			}
		}else {
			if (imageDownloaderHashMap.containsKey(imageId)) {
				ImageDownloader imageDownloader = imageDownloaderHashMap.get(imageId);
				if (isNeedUpdateUI) {
					imageDownloader.setIsNeedUpdateUI(isNeedUpdateUI);
				}
				
			}else {
				ImageDownloader imageDownloader = new ImageDownloader(imageId, imageUrl, true, isNeedUpdateUI);
				imageDownloaderHashMap.put(imageId, imageDownloader);
				imageDownloader.startDownload();
			}
		}
	}
	
	private void requestSmallImage(String imageUrl, Boolean isNeedUpdateUI)
	{
		String imageId = StringUtil.imageUrl_2_ImageName(imageUrl);
		Bitmap bitmap = null;
		bitmap = smallImageCache.getBitmapFromCache(imageId);
		if (bitmap!=null) {
			if (isNeedUpdateUI) {
				//send message to main thread
				sendImageToUIThreadUpdate(imageId, bitmap);
			}
		}else {
			if (imageDownloaderHashMap.containsKey(imageId)) {
				ImageDownloader imageDownloader = imageDownloaderHashMap.get(imageId);
				if (isNeedUpdateUI) {
					imageDownloader.setIsNeedUpdateUI(isNeedUpdateUI);
				}
				
			}else {
				ImageDownloader imageDownloader = new ImageDownloader(imageId, imageUrl, false, isNeedUpdateUI);
				imageDownloaderHashMap.put(imageId, imageDownloader);
				imageDownloader.startDownload();
			}
		}
	}
	
	private void sendImageToUIThreadUpdate(String imageId, Bitmap bitmap)
	{
		Bundle bundle = new Bundle();
		bundle.putString(ImageDownloadModuleConstant.imageIdKey, imageId);
		
		Message msg = new Message();
		msg.what = MainThreadMessageID.MESSAGE_IMAGE_RECEIVED;
		msg.obj = bitmap;
		msg.setData(bundle);
		
		ApplicationExtend.getInstance().getMainThreadhandler().sendMessage(msg);
	}
	
	private void switchServer(String newServerAddr)
	{
		imageDownloadFixedThreadPool.shutdown();
		
		Iterator<Entry<String, ImageDownloader>> it = imageDownloaderHashMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, ImageDownloader> entry = (Entry<String, ImageDownloader>) it.next();
			
			ImageDownloader imageDownloader = entry.getValue();
			imageDownloader.switchServer(newServerAddr);
			imageDownloader.startDownload();
		}
	}
}
