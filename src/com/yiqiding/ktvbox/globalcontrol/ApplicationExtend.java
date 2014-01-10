package com.yiqiding.ktvbox.globalcontrol;

import com.yiqiding.ktvbox.imagedownload.ImageDownloadDispatchThread;
import com.yiqiding.ktvbox.imagedownload.ImageDownloadDispatchThreadMessageId;
import com.yiqiding.ktvbox.imagedownload.ImageDownloadModuleConstant;
import com.yiqiding.ktvbox.imagedownload.ImageListener;
import com.yiqiding.ktvbox.infomanage.InfoListener;
import com.yiqiding.ktvbox.infomanage.InfoModuleConstant;
import com.yiqiding.ktvbox.operatemanage.InfoOperateManage;
import com.yiqiding.ktvbox.videodownload.VideoDownloadDispatchThread;
import com.yiqiding.ktvbox.videodownload.VideoDownloadDispatchThreadMessageId;
import com.yiqiding.ktvbox.videodownload.VideoDownloadModuleConstant;
import com.yiqiding.ktvbox.videodownload.VideoFileListener;
import com.yiqiding.ktvbox.videoplay.VideoDisplay;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;

public class ApplicationExtend extends Application{
	private static ApplicationExtend instance;	
	
	private Handler mainThreadHandler = null;
	
	private InfoListener infoListener = null;
	private VideoFileListener videoFileListener = null;
	private ImageListener imageListener = null;
	
	private VideoDisplay videoDisplay;

	public static ApplicationExtend getInstance() {
		return instance;
	}

	@SuppressLint("HandlerLeak")
	private void initMainThreadMessageMechanism()
	{
		mainThreadHandler = new Handler(getMainLooper())
		{
			@Override
			public void handleMessage(Message msg)
			{
				switch (msg.what) {
				case MainThreadMessageID.MESSAGE_INFO_SUCCESS:
					Bundle infoSuccessBundle = msg.getData();
					if (infoSuccessBundle.getInt(InfoModuleConstant.functionRequestTimeStampKey)==InfoOperateManage.lastInfoOperateTimeStamp) {
						if (infoListener!=null) {
							infoListener.onHandleInfo(infoSuccessBundle);
						}
					}

					break;
				case MainThreadMessageID.MESSAGE_INFO_ERROR:
					Bundle infoErrorBundle = msg.getData();
					if (infoErrorBundle.getInt(InfoModuleConstant.functionRequestTimeStampKey)==InfoOperateManage.lastInfoOperateTimeStamp) {
						if (infoListener!=null) {
							infoListener.onHandleErrorInfo(infoErrorBundle);
						}
					}
					break;
				case MainThreadMessageID.MESSAGE_VIDEO_GET_NEW_PLAY_VIDEO:
					if (videoFileListener!=null) {
						Bundle videoFileBundle = msg.getData();
						videoFileListener.onHandleVideo(videoFileBundle.getString(VideoDownloadModuleConstant.videoIdKey), videoFileBundle.getString(VideoDownloadModuleConstant.videoFilePathKey), VideoDisplay.DOWNLOADMODE);
					}
					break;
				case MainThreadMessageID.MESSAGE_VIDEO_NO_VIDEO_FOR_PLAY:
					if (videoFileListener!=null) {
						videoFileListener.onHandleNoVideo();
					}
					break;
				case MainThreadMessageID.MESSAGE_VIDEO_REMOVE_DYNAMICVIEW:
					videoDisplay.removeView(msg.arg1);
					break;
				case MainThreadMessageID.MESSAGE_IMAGE_RECEIVED:
					if (imageListener!=null) {
						imageListener.onHandleImage(msg.getData().getString(ImageDownloadModuleConstant.imageIdKey), (Bitmap)msg.obj);
					}
					break;
				default:
					break;
				}
			}
		};
	}
	
	public Handler getMainThreadhandler()
	{
		return mainThreadHandler;
	}
	
	public void registerInfoListener(InfoListener listener)
	{
		this.infoListener = listener;
	}
	
	public void registerVideoFileListener(VideoFileListener videoFileListener)
	{
		this.videoFileListener = videoFileListener;
	}
	
	public void registerImageListener(ImageListener imageListener)
	{
		this.imageListener = imageListener;
	}
	
	private void createVideoDisplay()
	{
		DisplayManager displayManager = (DisplayManager)getSystemService(Context.DISPLAY_SERVICE);

        Display [] dises = displayManager.getDisplays();

        Display dis = dises[1];

        videoDisplay = new VideoDisplay(this, displayManager.getDisplay(dis.getDisplayId()));
        videoDisplay.show();
	}

	public VideoDisplay getVideoDisplay()
	{
		return this.videoDisplay;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		ApplicationExtend.instance = this;

		this.initMainThreadMessageMechanism();
		
		ImageDownloadDispatchThread.getInstance().startThread();
		this.cleanImageStorage();

		VideoDownloadDispatchThread.start();
		this.cleanVideoStorage();

//		this.createVideoDisplay();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}
	
	public void appExit()
	{
	}
	
	private void cleanVideoStorage()
	{
		Message msg = new Message();
		msg.what = VideoDownloadDispatchThreadMessageId.MESSAGE_ID_APPSTART_CLEAN_VIDEOSTORAGE;
		VideoDownloadDispatchThread.getHandler().sendMessage(msg);
	}
	
	private void cleanImageStorage()
	{
		Message msg = new Message();
		msg.what = ImageDownloadDispatchThreadMessageId.MESSAGE_ID_IMAGEDOWNLOAD_CLEAN_IMAGESTORAGE;
		ImageDownloadDispatchThread.getInstance().getHandler().sendMessage(msg);
	}
}
