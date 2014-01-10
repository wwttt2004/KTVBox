package com.yiqiding.ktvbox.videodownload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.yiqiding.ktvbox.fastdownload.SliceDownloadInfoTableHelper;
import com.yiqiding.ktvbox.globalcontrol.ApplicationExtend;
import com.yiqiding.ktvbox.globalcontrol.MainThreadMessageID;
import com.yiqiding.ktvbox.util.FileSystemUtil;
import com.yiqiding.ktvbox.util.ThreadUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

public class VideoDownloadDispatchThread {
	
	private static final int VideoDownloadTasks_MaxActiveSize = 10;
	
	private static Queue<Bundle> videoOperateQueue = new Queue<Bundle>() {

		private LinkedList<Bundle> linkedList = new LinkedList<Bundle>();

		@Override
		public boolean addAll(Collection<? extends Bundle> arg0) {
			return false;
		}

		@Override
		public void clear() {
			synchronized (linkedList) {
				linkedList.clear();
			}
		}

		@Override
		public boolean contains(Object object) {

			synchronized (linkedList) {
				return linkedList.contains(object);
			}
		}

		@Override
		public boolean containsAll(Collection<?> arg0) {
			return false;
		}

		@Override
		public boolean isEmpty() {
			synchronized (linkedList) {
				return linkedList.isEmpty();
			}
		}

		@Override
		public Iterator<Bundle> iterator() {
			synchronized (linkedList) {
				return linkedList.iterator();
			}
		}

		@Override
		public boolean remove(Object object) {
			synchronized (linkedList) {
				return linkedList.remove(object);
			}
		}

		@Override
		public boolean removeAll(Collection<?> arg0) {
			return false;
		}

		@Override
		public boolean retainAll(Collection<?> arg0) {
			return false;
		}

		@Override
		public int size() {
			synchronized (linkedList) {
				return linkedList.size();
			}
		}

		@Override
		public Object[] toArray() {
			return null;
		}

		@Override
		public <T> T[] toArray(T[] array) {
			return null;
		}

		@Override
		public boolean add(Bundle e) {
			return linkedList.add(e);
		}

		@Override
		public Bundle element() {
			return null;
		}

		@Override
		public boolean offer(Bundle e) {
			return false;
		}

		@Override
		public Bundle peek() {
			return null;
		}

		@Override
		public Bundle poll() {
			synchronized (linkedList) {
				return linkedList.poll();
			}
		}

		@Override
		public Bundle remove() {
			return linkedList.remove();
		}
	};
	private static Queue<Bundle> waittingVideoDownloadQueue = new Queue<Bundle>() {

		private LinkedList<Bundle> linkedList = new LinkedList<Bundle>();

		@Override
		public boolean addAll(Collection<? extends Bundle> arg0) {
			return false;
		}

		@Override
		public void clear() {
			synchronized (linkedList) {
				linkedList.clear();
			}
		}

		@Override
		public boolean contains(Object object) {

			synchronized (linkedList) {
				return linkedList.contains(object);
			}
		}

		@Override
		public boolean containsAll(Collection<?> arg0) {
			return false;
		}

		@Override
		public boolean isEmpty() {
			synchronized (linkedList) {
				return linkedList.isEmpty();
			}
		}

		@Override
		public Iterator<Bundle> iterator() {
			synchronized (linkedList) {
				return linkedList.iterator();
			}
		}

		@Override
		public boolean remove(Object object) {
			synchronized (linkedList) {
				return linkedList.remove(object);
			}
		}

		@Override
		public boolean removeAll(Collection<?> arg0) {
			return false;
		}

		@Override
		public boolean retainAll(Collection<?> arg0) {
			return false;
		}

		@Override
		public int size() {
			synchronized (linkedList) {
				return linkedList.size();
			}
		}

		@Override
		public Object[] toArray() {
			return null;
		}

		@Override
		public <T> T[] toArray(T[] array) {
			return null;
		}

		@Override
		public boolean add(Bundle e) {
			return linkedList.add(e);
		}

		@Override
		public Bundle element() {
			return null;
		}

		@Override
		public boolean offer(Bundle e) {
			return false;
		}

		@Override
		public Bundle peek() {
			return null;
		}

		@Override
		public Bundle poll() {
			synchronized (linkedList) {
				return linkedList.poll();
			}
		}

		@Override
		public Bundle remove() {
			return null;
		}
	};
	
	private static ArrayList<VideoDownloadTask> videoDownloadTasks = new ArrayList<VideoDownloadTask>();

	private static HandlerThread handlerThread;
	public static ExecutorService videoDownloadFixedThreadPool = Executors.newFixedThreadPool(ThreadUtil.getDefaultThreadPoolSize(8));

	private static VideoDownloadTask videoDownloadTask_ForPlay;
	private static String currentAvailableVideoDownloadStorageDir;
	
	private static Handler handler;
	public static Handler getHandler() {
		return handler;
	}

	public static String getCurrentAvailableVideoDownloadStorageDir()
	{
		return currentAvailableVideoDownloadStorageDir;
	}
	
	public static void start() {
		
		try {
			currentAvailableVideoDownloadStorageDir = FileSystemUtil.getCurrentAvailableStorageDir()+"/VideoDownload";
		} catch (IOException e) {
			e.printStackTrace();
			currentAvailableVideoDownloadStorageDir = "/data/VideoDownload";
		}
		
		handlerThread = new HandlerThread("VideoDownloadDispatchThread");
		handlerThread.start();
		
		handler = new Handler(handlerThread.getLooper()) {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                case VideoDownloadDispatchThreadMessageId.MESSAGE_ID_APPSTART_CLEAN_VIDEOSTORAGE:
                	cleanVideoStorage();
                	break;
				case VideoDownloadDispatchThreadMessageId.MESSAGE_ID_VIDEODOWNLOAD_ADDONE:
					if (addOperateToVideoOperateQueue(msg.getData())) {
						addNewVideoDownloadToWaittingVideoDownloadQueue(msg.getData());
						triggerNewVideoDownloadTask();
					}
					break;
				case VideoDownloadDispatchThreadMessageId.MESSAGE_ID_NEXT_PLAY_VIDEO:
					respondNextPlayVideo();
					break;
				case VideoDownloadDispatchThreadMessageId.MESSAGE_ID_FINSIH_PLAY_ONEVIDEO:
					finishPlayOneVideo((String)msg.obj);
					break;
				case VideoDownloadDispatchThreadMessageId.MESSAGE_ID_VIDEODOWNLOAD_SWITCHSERVER:
					switchServer(msg.getData().getString(VideoDownloadModuleConstant.serverAddrKey));
					break;

				default:
					break;
				}
            }
        };
	}
	
	private static Boolean addOperateToVideoOperateQueue(Bundle operate)
	{
		Boolean isFound = false;
		Iterator<Bundle> it = videoOperateQueue.iterator();
		while (it.hasNext()) {
			Bundle bundle = (Bundle) it.next();
			if (bundle.getString(VideoDownloadModuleConstant.videoIdKey).equals(operate.getString(VideoDownloadModuleConstant.videoIdKey))) {
				isFound = true;
				break;
			}
		}
		
		if (!isFound) {
			videoOperateQueue.add(operate);
			return true;
		}else {
			return false;
		}
	}

	private static void addNewVideoDownloadToWaittingVideoDownloadQueue(Bundle videoInfo)
	{
		waittingVideoDownloadQueue.add(videoInfo);
	}
	
	private static void triggerNewVideoDownloadTask()
	{
		if (videoDownloadTasks.size()<=VideoDownloadTasks_MaxActiveSize) {
			
			Boolean isFinishTrigger = false;
			
			while (!isFinishTrigger) {
				Bundle bundle = waittingVideoDownloadQueue.poll();
				if (bundle==null) {
					return;
				}
				
				String videoId = bundle.getString(VideoDownloadModuleConstant.videoIdKey);
				
				Iterator<VideoDownloadTask> it = videoDownloadTasks.iterator();
				boolean isFound = false;
				while (it.hasNext()) {
					VideoDownloadTask videoDownloadTask = (VideoDownloadTask) it.next();
					if (videoDownloadTask.getVideoId().equals(videoId)) {
						isFound = true;
						break;
					}
				}

				if (!isFound) {
					VideoDownloadTask videoDownloadTask = new VideoDownloadTask(videoId, bundle.getString(VideoDownloadModuleConstant.videoUrlKey));
					videoDownloadTask.setBundle(bundle);
					videoDownloadTask.executeTask();

					videoDownloadTasks.add(videoDownloadTask);
					
					isFinishTrigger = true;
				}
			}
		}
	}

	
	private static void respondNextPlayVideo()
	{
		boolean isFound = false;
		while (!isFound) {
			Bundle bundle = videoOperateQueue.poll();
			if (bundle==null) {
				// send message to main thread (has no video request operate)
				Message msg = new Message();
				msg.what = MainThreadMessageID.MESSAGE_VIDEO_NO_VIDEO_FOR_PLAY;
				ApplicationExtend.getInstance().getMainThreadhandler().sendMessage(msg);
				return;
			}

			Iterator<VideoDownloadTask> it = videoDownloadTasks.iterator();
			while (it.hasNext()) {
				VideoDownloadTask videoDownloadTask = (VideoDownloadTask) it.next();
				if (videoDownloadTask.getVideoId().equals(bundle.getString(VideoDownloadModuleConstant.videoIdKey))) {
					isFound = true;
					videoDownloadTask_ForPlay = videoDownloadTask;
					break;
				}
			}
		}
		
		if (isFound) {
			if (videoDownloadTask_ForPlay.isDownloaded()) {
				// send message to main thread (for play)
				Message msg = new Message();
				msg.what = MainThreadMessageID.MESSAGE_VIDEO_GET_NEW_PLAY_VIDEO;
				Bundle bundle = new Bundle();
				bundle.putString(VideoDownloadModuleConstant.videoFilePathKey, videoDownloadTask_ForPlay.getLocalFile());
				bundle.putString(VideoDownloadModuleConstant.videoIdKey, videoDownloadTask_ForPlay.getVideoId());
				msg.setData(bundle);
				ApplicationExtend.getInstance().getMainThreadhandler().sendMessage(msg);
				
			}else {
				new Runnable() {
					public void run() {
						while (!videoDownloadTask_ForPlay.isDownloaded()) {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						
						// send message to main thread (for play)
						Message msg = new Message();
						msg.what = MainThreadMessageID.MESSAGE_VIDEO_GET_NEW_PLAY_VIDEO;
						Bundle bundle = new Bundle();
						bundle.putString(VideoDownloadModuleConstant.videoFilePathKey, videoDownloadTask_ForPlay.getLocalFile());
						bundle.putString(VideoDownloadModuleConstant.videoIdKey, videoDownloadTask_ForPlay.getVideoId());
						msg.setData(bundle);
						ApplicationExtend.getInstance().getMainThreadhandler().sendMessage(msg);
					}
				};
			}
		}
	}
	
	private static void finishPlayOneVideo(String videoId)
	{
		Iterator<VideoDownloadTask> vit = videoDownloadTasks.iterator();
		while (vit.hasNext()) {
			VideoDownloadTask videoDownloadTask = (VideoDownloadTask) vit.next();
			if (videoDownloadTask.isDownloaded() && videoDownloadTask.getVideoId().equals(videoId)) {
				videoDownloadTask.deleteLocalFile();
				videoDownloadTasks.remove(videoDownloadTask);
				videoDownloadTask = null;
				break;
			}
		}
		
		triggerNewVideoDownloadTask();
	}
	
	private static void switchServer(String newServerAddr)
	{
		videoDownloadFixedThreadPool.shutdown();

		Iterator<VideoDownloadTask> it = videoDownloadTasks.iterator();
		while (it.hasNext()) {
			VideoDownloadTask videoDownloadTask = (VideoDownloadTask) it.next();
			if (!videoDownloadTask.isDownloaded()) {
				videoDownloadTask.switchServer(newServerAddr);
			}
		}
	}

	private static void cleanVideoStorage()
	{
		FileSystemUtil.deleteDir(new File(currentAvailableVideoDownloadStorageDir));
		File file = new File(currentAvailableVideoDownloadStorageDir);
		if(!file.exists())
		{
			file.mkdir();
		}
		
		SliceDownloadInfoTableHelper.getInstance(ApplicationExtend.getInstance().getApplicationContext()).getDownloadDBHelper().deleteDB();
	}
}
