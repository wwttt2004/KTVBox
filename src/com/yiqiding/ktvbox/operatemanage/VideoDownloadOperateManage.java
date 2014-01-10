package com.yiqiding.ktvbox.operatemanage;

import java.util.ArrayList;

import com.yiqiding.ktvbox.videodownload.VideoDownloadDispatchThread;
import com.yiqiding.ktvbox.videodownload.VideoDownloadDispatchThreadMessageId;
import com.yiqiding.ktvbox.videodownload.VideoDownloadModuleConstant;

import android.os.Bundle;
import android.os.Message;

public class VideoDownloadOperateManage {
	private static int lastVideoOperateTimeStamp = 0x00000000;

	public static int requestVideo(String videoUrl, String videoId, ArrayList<String> backupVideoUrl) {
		if (VideoDownloadOperateManage.lastVideoOperateTimeStamp >= 0xFFFFFFFF) {
			VideoDownloadOperateManage.lastVideoOperateTimeStamp = 0x00000000;
		}

		VideoDownloadOperateManage.lastVideoOperateTimeStamp++;

		Bundle bundle = new Bundle();
		bundle.putInt(VideoDownloadModuleConstant.videoRequestTimeStampKey,
				VideoDownloadOperateManage.lastVideoOperateTimeStamp);
		bundle.putString(VideoDownloadModuleConstant.videoIdKey, videoId);
		bundle.putString(VideoDownloadModuleConstant.videoUrlKey, videoUrl);
		bundle.putStringArrayList(VideoDownloadModuleConstant.backupVideoUrlKey, backupVideoUrl);

		Message msg = new Message();
		msg.what = VideoDownloadDispatchThreadMessageId.MESSAGE_ID_VIDEODOWNLOAD_ADDONE;
		msg.setData(bundle);
		VideoDownloadDispatchThread.getHandler().sendMessage(msg);

		return VideoDownloadOperateManage.lastVideoOperateTimeStamp;
	}
	
	public static void requestNextPlayVideo()
	{
		Message msg = new Message();
		msg.what = VideoDownloadDispatchThreadMessageId.MESSAGE_ID_NEXT_PLAY_VIDEO;
		VideoDownloadDispatchThread.getHandler().sendMessage(msg);
	}
	
	public static void finishPlayOneVideo(String videoId)
	{
		Message msg = new Message();
		msg.what = VideoDownloadDispatchThreadMessageId.MESSAGE_ID_FINSIH_PLAY_ONEVIDEO;
		msg.obj = videoId;
		VideoDownloadDispatchThread.getHandler().sendMessage(msg);
	}
}
