package com.yiqiding.ktvbox.localvideostorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.yiqiding.ktvbox.util.PackageUtil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

public class LocalVideoStorageDBManager {
	private final int BUFFER_SIZE = 512 * 1024; // 512kb

	private String databaseName = "VideoFiles.db"; // 保存的数据库文件名
	private String internalDatabasePath;

	private SQLiteDatabase database;
	@SuppressWarnings("unused")
	private Context context;

	public LocalVideoStorageDBManager(Context context) {
		this.context = context;
	}

	public void loadDatabase(String externalDatabasePath) {
		// 在手机里存放数据库的位置
		internalDatabasePath = Environment.getDataDirectory().getAbsolutePath()
				+ "/data/" + PackageUtil.getAppPacketName() + "/databases/"
				+ databaseName;
		try {
			if (!(new File(internalDatabasePath).exists())) {
				// 判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
				InputStream is = new FileInputStream(externalDatabasePath); // 欲导入的数据库
				FileOutputStream fos = new FileOutputStream(
						internalDatabasePath);
				byte[] buffer = new byte[BUFFER_SIZE];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.flush();
				fos.close();
				is.close();
			}

		} catch (FileNotFoundException e) {
			Log.e("Database", "File not found");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("Database", "IO exception");
			e.printStackTrace();
		}
	}

	public SQLiteDatabase openReadableDatabase() {
		this.database = SQLiteDatabase.openDatabase(internalDatabasePath, null, SQLiteDatabase.OPEN_READONLY);
		return this.database;
	}
	
	public SQLiteDatabase openWriteableDatabase()
	{
		this.database = SQLiteDatabase.openDatabase(internalDatabasePath, null, SQLiteDatabase.OPEN_READWRITE);
		return this.database;
	}

	public void closeDatabase() {
		if (this.database != null) {
			this.database.close();
		}
	}
}
