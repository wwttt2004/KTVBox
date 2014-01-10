package com.yiqiding.ktvbox.videodownload;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
import android.os.Bundle;

import com.yiqiding.ktvbox.fastdownload.Downloader;
import com.yiqiding.ktvbox.globalcontrol.ApplicationExtend;
import com.yiqiding.ktvbox.util.ThreadUtil;

public class VideoDownloadTask{
	
	private Downloader downloader;
	private Bundle bundle;
	
	public VideoDownloadTask(String videoId, String urlStr)
	{
		String localfile = VideoDownloadDispatchThread.getCurrentAvailableVideoDownloadStorageDir()+"/"+videoId+".mp4";
		int threadcount = ThreadUtil.getDefaultThreadPoolSize(8);
		Context context = ApplicationExtend.getInstance().getApplicationContext();

		downloader = new Downloader(videoId, urlStr, localfile, threadcount, context);
	}
	
	public void setBundle(Bundle bundle)
	{
		this.bundle = bundle;
	}
	
	public Bundle getBundle()
	{
		return this.bundle;
	}
	
	public String getLocalFile()
	{
		return downloader.getLocalFile();
	}
	
	public String getVideoId()
	{
		return downloader.getVideoId();
	}

	public void executeTask()
	{
		downloader.download();
	}
	
	public Boolean isDownloaded()
	{
		return downloader.isDownloaded();
	}
	
	public void switchServer(String newServerAddr)
	{
		ArrayList<String> videoUrls = bundle.getStringArrayList(VideoDownloadModuleConstant.backupVideoUrlKey);
		
		Iterator<String> it = videoUrls.iterator();
		while (it.hasNext()) {
			String videoUrl = (String) it.next();
			if (videoUrl.contains(newServerAddr)) {
				downloader.switchServer(videoUrl);
				break;
			}
		}
	}
	
	public void deleteLocalFile()
	{
		new File(getLocalFile()).delete();
	}
}
