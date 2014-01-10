package com.yiqiding.ktvbox.fastdownload;

import java.io.File;

import com.yiqiding.ktvbox.globalcontrol.ApplicationExtend;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 建立一个数据库帮助类
 */
public class DownloadDBHelper extends SQLiteOpenHelper {
	// fastdownload.db-->数据库名

	private final static String DATABASE_NAME = "fastdownload.db";
	private final static int DATABASE_VERSION = 1;

	public DownloadDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * 在fastdownload.db数据库下创建一个slice_downloadinfo表存储下载分片信息
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table slice_downloadinfo(_id integer PRIMARY KEY AUTOINCREMENT, thread_id integer, "
				+ "start_pos integer, end_pos integer, compelete_size integer,video_id char)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS slice_downloadinfo");
		onCreate(db);
	}
	
	public void deleteDB()
	{
		File file = ApplicationExtend.getInstance().getApplicationContext().getDatabasePath("fastdownload.db");
		if (file.exists()) {
			file.delete();
		}

		file = ApplicationExtend.getInstance().getApplicationContext().getDatabasePath("fastdownload.db-journal");
		if (file.exists()) {
			file.delete();
		}
	}
}
