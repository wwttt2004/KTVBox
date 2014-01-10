package com.yiqiding.ktvbox.operatemanage;

import com.yiqiding.ktvbox.globalcontrol.ApplicationExtend;
import com.yiqiding.ktvbox.videoplay.DynamicViewParamStructure;

public class VideoPlayOperateManage {
	
	private static int lastVideoPlayOperateTimeStamp = 0x00000000;
	
	public static void start()
	{
		ApplicationExtend.getInstance().getVideoDisplay().start();
	}
	
	public static void pause()
	{
		ApplicationExtend.getInstance().getVideoDisplay().pause();
	}
	
	public static void stop()
	{
		ApplicationExtend.getInstance().getVideoDisplay().stop();
	}
	
	public static void seekTo(float percentage)
	{
		ApplicationExtend.getInstance().getVideoDisplay().seekTo(percentage);
	}
	
	public static void next()
	{
		ApplicationExtend.getInstance().getVideoDisplay().next();
	}
	
	public static void switchTrack()
	{
		ApplicationExtend.getInstance().getVideoDisplay().switchTrack();
	}
	
	public static void adjustVolume(float volume)
	{
		ApplicationExtend.getInstance().getVideoDisplay().adjustVolume(volume);
	}
	
	public static void enableMute(Boolean isMute)
	{
		ApplicationExtend.getInstance().getVideoDisplay().enableMute(isMute);
	}
	
	public static void sendView(DynamicViewParamStructure dynamicViewParamStructure)
	{
		if (VideoPlayOperateManage.lastVideoPlayOperateTimeStamp >= 0xFFFFFFFF) {
			VideoPlayOperateManage.lastVideoPlayOperateTimeStamp = 0x00000000;
		}

		VideoPlayOperateManage.lastVideoPlayOperateTimeStamp++;

		dynamicViewParamStructure.setId(lastVideoPlayOperateTimeStamp);

		ApplicationExtend.getInstance().getVideoDisplay().addView(dynamicViewParamStructure);
	}
}
