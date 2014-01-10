package com.yiqiding.ktvbox.util;

import java.io.File;
import java.io.IOException;

import android.os.Environment;
import android.os.StatFs;

public class FileSystemUtil {
	private static Boolean isExternalStorageWriteable() {
		boolean mExternalStorageWriteable = false;
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)
				&& Environment.getExternalStorageDirectory().canWrite()) {// 已经插入了sd卡，并且可以读写
			mExternalStorageWriteable = true;
		}

		return mExternalStorageWriteable;
	}

	public static String getCurrentAvailableStorageDir() throws IOException {
		if (isExternalStorageWriteable()) {
			return Environment.getExternalStorageDirectory().getCanonicalPath();
		} else {
			return Environment.getDataDirectory().getCanonicalPath();
		}
	}

	public static long getAvailableStore() {

		String filePath = "/data";

		try {
			filePath = getCurrentAvailableStorageDir();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 取得sdcard文件路径
		StatFs statFs = new StatFs(filePath);

		// 获取block的SIZE
		long blocSize = statFs.getBlockSize();

		// 获取BLOCK数量
		// long totalBlocks = statFs.getBlockCount();

		// 可使用的Block的数量
		long availaBlock = statFs.getAvailableBlocks();

		// long total = totalBlocks * blocSize;
		long availableSpare = availaBlock * blocSize;

		return availableSpare;
	}

	/**
	 * 删除文件夹所有内容
	 * 
	 */
	public static void deleteDir(File dir) {
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (File f : files) {
				deleteDir(f);
			}
			dir.delete();
		} else
			dir.delete();
	}

}
