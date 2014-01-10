package com.yiqiding.ktvbox.videodownload;

public interface VideoFileListener {
	public void onHandleVideo(String videoId, String videoPath, int mode);
	public void onHandleNoVideo();
}
