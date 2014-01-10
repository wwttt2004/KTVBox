package com.yiqiding.ktvbox.fastdownload;

/**
 * 自定义的一个记载下载器详细信息的类
 */
public class DownloaderLoadInfo {
	public int fileSize;// 文件大小
	private int complete;// 完成度
	private String videoId;// 下载器标识

	public DownloaderLoadInfo(int fileSize, int complete, String videoId) {
		this.fileSize = fileSize;
		this.complete = complete;
		this.videoId = videoId;
	}

	public DownloaderLoadInfo() {
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public int getComplete() {
		return complete;
	}

	public void setComplete(int complete) {
		this.complete = complete;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
}
