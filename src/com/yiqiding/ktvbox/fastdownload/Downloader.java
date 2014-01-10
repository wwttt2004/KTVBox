package com.yiqiding.ktvbox.fastdownload;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.yiqiding.ktvbox.videodownload.VideoDownloadDispatchThread;

import android.content.Context;

public class Downloader {
	private String videoId; // 视频资源唯一标识
	private String urlStr;// 下载的地址
	private String localfile;// 保存路径
	private int threadcount;// 线程数
	// private Handler mHandler;// 消息处理器
	private Context context;

	private int fileSize;// 所要下载的文件的大小
	private List<SliceDownloadInfo> infos;// 存放分片下载信息类的集合
	private boolean isNeedDownload = true;
	private boolean isDownloaded = false;

	public Downloader(String videoId, String urlStr, String localfile,
			int threadcount, Context context) {
		this.videoId = videoId;
		this.urlStr = urlStr;
		this.localfile = localfile;
		this.threadcount = threadcount;
		this.context = context;

		this.init();
	}

	public String getVideoId() {
		return this.videoId;
	}

	public String getLocalFile() {
		return this.localfile;
	}

	public Boolean isDownloaded() {
		return isDownloaded;
	}

	public void switchServer(String urlStr) {
		this.urlStr = urlStr;
		this.init();
		this.download();
	}

	/**
	 * 得到downloader里的信息 首先进行判断是否是第一次下载，如果是第一次就要进行初始化，并将下载器的信息保存到数据库中
	 * 如果不是第一次下载，那就要从数据库中读出之前下载的信息（起始位置，结束为止，文件大小等），并将下载信息返回给下载器
	 */
	private void init() {
		if (isFirst(videoId)) {
			this.initLocalFile();

			int range = fileSize / threadcount;
			infos = new ArrayList<SliceDownloadInfo>();
			for (int i = 0; i < threadcount - 1; i++) {
				SliceDownloadInfo info = new SliceDownloadInfo(i, i * range,
						(i + 1) * range - 1, 0, videoId);
				infos.add(info);
			}
			SliceDownloadInfo info = new SliceDownloadInfo(threadcount - 1,
					(threadcount - 1) * range, fileSize - 1, 0, videoId);
			infos.add(info);
			// 保存infos中的数据到数据库
			SliceDownloadInfoTableHelper.getInstance(context).saveSliceInfos(
					infos);
		} else {
			// 得到数据库中已有的videoId的下载器的具体信息
			infos = SliceDownloadInfoTableHelper.getInstance(context)
					.getSliceInfos(videoId);
			int size = 0;
			int compeleteSize = 0;
			for (SliceDownloadInfo info : infos) {
				compeleteSize += info.getCompeleteSize();
				size += info.getEndPos() - info.getStartPos() + 1;
			}
			this.fileSize = size;
			if (compeleteSize == size) {
				isNeedDownload = false;
			}
		}
	}

	/**
	 * 判断是否是第一次 下载
	 */
	private boolean isFirst(String videoId) {
		return SliceDownloadInfoTableHelper.getInstance(context)
				.isHasSliceInfors(videoId);
	}

	/**
	 * 初始化本地文件
	 */
	private void initLocalFile() {
		try {
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("GET");
			fileSize = connection.getContentLength();

			File file = new File(localfile);
			if (!file.exists()) {
				file.createNewFile();
			}
			// 本地访问文件
			RandomAccessFile accessFile = new RandomAccessFile(file, "rwd");
			accessFile.setLength(fileSize);
			accessFile.close();
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 利用线程开始下载数据
	 */
	public void download() {
		if (!isNeedDownload) {
			SliceDownloadInfoTableHelper.getInstance(context).delete(videoId);
			isDownloaded = true;

		} else {
			if (infos != null) {
				for (SliceDownloadInfo info : infos) {
					if (info.getEndPos() - info.getStartPos() + 1 > info
							.getCompeleteSize()) {
						VideoDownloadDispatchThread.videoDownloadFixedThreadPool
								.execute(new SliceDownloadThread(info
										.getThreadId(), info.getStartPos(),
										info.getEndPos(), info
												.getCompeleteSize(), info
												.getVideoId()));
					}
				}
			}
		}
	}

	public class SliceDownloadThread implements Runnable {
		private int threadId;
		private int startPos;
		private int endPos;
		private int compeleteSize;
		private String videoId;

		public SliceDownloadThread(int threadId, int startPos, int endPos,
				int compeleteSize, String videoId) {
			this.threadId = threadId;
			this.startPos = startPos;
			this.endPos = endPos;
			this.compeleteSize = compeleteSize;
			this.videoId = videoId;
		}

		@Override
		public void run() {
			HttpURLConnection connection = null;
			RandomAccessFile randomAccessFile = null;
			InputStream is = null;
			try {
				URL url = new URL(urlStr);
				connection = (HttpURLConnection) url.openConnection();
				connection.setConnectTimeout(5000);
				connection.setRequestMethod("GET");
				// 设置范围，格式为Range：bytes x-y;
				connection.setRequestProperty("Range", "bytes="
						+ (startPos + compeleteSize) + "-" + endPos);

				randomAccessFile = new RandomAccessFile(localfile, "rwd");
				randomAccessFile.seek(startPos + compeleteSize);
				// 将要下载的文件写到保存在保存路径下的文件中
				is = connection.getInputStream();
				byte[] buffer = new byte[1024*1024*2]; //2M cache
				int length = -1;
				while ((length = is.read(buffer)) != -1) {
					randomAccessFile.write(buffer, 0, length);
					compeleteSize += length;
					// 更新数据库中的下载信息,并返回最新的下载进度
					SliceDownloadInfoTableHelper.getInstance(context)
							.updateSliceInfos(threadId, compeleteSize, videoId);

				}

				List<SliceDownloadInfo> infos = SliceDownloadInfoTableHelper
						.getInstance(context).getSliceInfos(videoId);
				int size = 0;
				int compeleteSize = 0;
				for (SliceDownloadInfo info : infos) {
					compeleteSize += info.getCompeleteSize();
					size += info.getEndPos() - info.getStartPos() + 1;
				}

				if (compeleteSize == size) {
					SliceDownloadInfoTableHelper.getInstance(context).delete(
							videoId);

					isDownloaded = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
