package com.yiqiding.ktvbox.fastdownload;

/**
 * 创建一个分片下载信息的实体类
 */
public class SliceDownloadInfo {
	private int threadId;// 分片下载线程id
	private int startPos;// 开始点
	private int endPos;// 结束点
	private int compeleteSize;// 完成度
	private String videoId;// 下载器标识

	public SliceDownloadInfo(int threadId, int startPos, int endPos,
			int compeleteSize, String videoId) {
		this.threadId = threadId;
		this.startPos = startPos;
		this.endPos = endPos;
		this.compeleteSize = compeleteSize;
		this.videoId = videoId;
	}

	public SliceDownloadInfo() {
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public int getThreadId() {
		return threadId;
	}

	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}

	public int getStartPos() {
		return startPos;
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}

	public int getEndPos() {
		return endPos;
	}

	public void setEndPos(int endPos) {
		this.endPos = endPos;
	}

	public int getCompeleteSize() {
		return compeleteSize;
	}

	public void setCompeleteSize(int compeleteSize) {
		this.compeleteSize = compeleteSize;
	}
}
