package com.yiqiding.ktvbox.imagedownload;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Message;
import android.util.Log;

import com.yiqiding.ktvbox.http.Http;
import com.yiqiding.ktvbox.util.StringUtil;

public class ImageDownloader {

	/**
	 * Used for DDMS logging
	 */
	private static final String TAG = "ImageDownloader";

	private String imageId;
	private String imageUrl;
	private Boolean isLargeImage;
	private Boolean isNeedUpdateUI;

	private Bitmap bitmap;

	private static final int IMAGE_MAX_WIDTH = 1024;
	private static final int IMAGE_MAX_HEIGHT = 768;

	public ImageDownloader() {

	}

	public ImageDownloader(String imageId, String imageUrl,
			Boolean isLargeImage, Boolean isNeedUpdateUI) {
		this.imageId = imageId;
		this.imageUrl = imageUrl;
		this.isLargeImage = isLargeImage;
		this.isNeedUpdateUI = isNeedUpdateUI;
	}

	public void setIsNeedUpdateUI(Boolean isNeedUpdateUI) {
		this.isNeedUpdateUI = isNeedUpdateUI;
	}

	public Boolean getIsNeedUpdateUI() {
		return this.isNeedUpdateUI;
	}

	public Boolean getIsLargeImage() {
		return this.isLargeImage;
	}

	public Bitmap getBitmap() {
		return this.bitmap;
	}

	public void switchServer(String newServerAddr) {
		this.imageUrl = StringUtil.switchServerAddr(imageUrl, newServerAddr);
	}

	public void startDownload() {
		ImageDownloadDispatchThread.imageDownloadFixedThreadPool
				.execute(new Runnable() {

					@Override
					public void run() {
						HttpEntity httpEntity = Http.get(imageUrl);
						if (httpEntity==null) {
							return;
						}

						try {
							InputStream inputStream = httpEntity.getContent();

							bitmap = decodeInputStream(inputStream);

							Message msg = new Message();
							msg.what = ImageDownloadDispatchThreadMessageId.MESSAGE_ID_IMAGEDOWNLOAD_FINISHED;
							msg.obj = imageId;
							ImageDownloadDispatchThread.getInstance()
									.getHandler().sendMessage(msg);

						} catch (IllegalStateException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
	}

	private Bitmap decodeInputStream(InputStream inputStream) {
//		BitmapFactory.Options option = new BitmapFactory.Options();
//		option.inJustDecodeBounds = false;
//		option.inSampleSize = getImageScale(inputStream);
//		return BitmapFactory.decodeStream(inputStream, null, option);
		return BitmapFactory.decodeStream(inputStream);
	}

	private static int getImageScale(InputStream inputStream) {
		BitmapFactory.Options option = new BitmapFactory.Options();
		// set inJustDecodeBounds to true, allowing the caller to query the
		// bitmap info without having to allocate the
		// memory for its pixels.
		option.inJustDecodeBounds = true;
		option.inSampleSize = 1;
		BitmapFactory.decodeStream(inputStream, null, option);
		
		int scale = 1;
		while (option.outWidth / scale >= IMAGE_MAX_WIDTH
				|| option.outHeight / scale >= IMAGE_MAX_HEIGHT) {
			scale *= 2;
		}
		return scale;
	}
}
