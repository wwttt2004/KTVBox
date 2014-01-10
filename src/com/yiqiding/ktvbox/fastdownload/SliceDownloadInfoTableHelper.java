package com.yiqiding.ktvbox.fastdownload;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SliceDownloadInfoTableHelper {
	private static SliceDownloadInfoTableHelper sliceDownloadInfoTableHelper = null;
	private DownloadDBHelper downloadDBHelper = null;

	private SliceDownloadInfoTableHelper(Context context) {
		this.downloadDBHelper = new DownloadDBHelper(context);
	}
	
	public DownloadDBHelper getDownloadDBHelper()
	{
		return this.downloadDBHelper;
	}

	public static SliceDownloadInfoTableHelper getInstance(Context context) {
		if (sliceDownloadInfoTableHelper == null) {
			sliceDownloadInfoTableHelper = new SliceDownloadInfoTableHelper(context);
		}
		return sliceDownloadInfoTableHelper;
	}

	/**
	 * 查看数据库中是否有数据
	 */
	public synchronized boolean isHasSliceInfors(String videoId) {
		SQLiteDatabase database = this.downloadDBHelper.getReadableDatabase();
		int count = -1;
		Cursor cursor = null;
		try {
			String sql = "select count(*)  from slice_downloadinfo where video_id=?";
			cursor = database.rawQuery(sql, new String[] { videoId });
			if (cursor.moveToFirst()) {
				count = cursor.getInt(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != database) {
				database.close();
			}
			if (null != cursor) {
				cursor.close();
			}
		}
		return count == 0;
	}

	/**
	 * 保存 下载的具体信息
	 */
	public synchronized void saveSliceInfos(List<SliceDownloadInfo> infos) {
		SQLiteDatabase database = this.downloadDBHelper.getWritableDatabase();
		try {
			for (SliceDownloadInfo info : infos) {
				String sql = "insert into slice_downloadinfo(thread_id,start_pos, end_pos,compelete_size,video_id) values (?,?,?,?,?)";
				Object[] bindArgs = { info.getThreadId(), info.getStartPos(),
						info.getEndPos(), info.getCompeleteSize(),
						info.getVideoId() };
				database.execSQL(sql, bindArgs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != database) {
				database.close();
			}
		}
	}

	/**
	 * 得到下载具体信息
	 */
	public synchronized List<SliceDownloadInfo> getSliceInfos(String videoId) {
		List<SliceDownloadInfo> list = new ArrayList<SliceDownloadInfo>();
		SQLiteDatabase database = this.downloadDBHelper.getReadableDatabase();
		Cursor cursor = null;
		try {
			String sql = "select thread_id, start_pos, end_pos,compelete_size,video_id from slice_downloadinfo where video_id=?";
			cursor = database.rawQuery(sql, new String[] { videoId });
			while (cursor.moveToNext()) {
				SliceDownloadInfo info = new SliceDownloadInfo(cursor.getInt(0),
						cursor.getInt(1), cursor.getInt(2), cursor.getInt(3),
						cursor.getString(4));
				list.add(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != database) {
				database.close();
			}
			if (null != cursor) {
				cursor.close();
			}
		}
		return list;
	}

	/**
	 * 更新数据库中的下载信息
	 */
	public synchronized void updateSliceInfos(int threadId, int compeleteSize,
			String videoId) {
		SQLiteDatabase database = this.downloadDBHelper.getWritableDatabase();
		try {
			// update
			String sql = "update slice_downloadinfo set compelete_size=? where thread_id=? and video_id=?";
			Object[] bindArgs = { compeleteSize, threadId, videoId };
			database.execSQL(sql, bindArgs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != database) {
				database.close();
			}
		}
	}

	/**
	 * 下载完成后删除数据库中的数据
	 */
	public synchronized void delete(String videoId) {
		SQLiteDatabase database = this.downloadDBHelper.getWritableDatabase();
		try {
			database.delete("slice_downloadinfo", "video_id=?", new String[] { videoId });
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != database) {
				database.close();
			}
		}
	}
}