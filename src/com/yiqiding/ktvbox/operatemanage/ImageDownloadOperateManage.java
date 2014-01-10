package com.yiqiding.ktvbox.operatemanage;

import java.util.ArrayList;

import com.yiqiding.ktvbox.imagedownload.ImageDownloadDispatchThread;
import com.yiqiding.ktvbox.imagedownload.ImageDownloadDispatchThreadMessageId;
import com.yiqiding.ktvbox.imagedownload.ImageDownloadModuleConstant;

import android.os.Bundle;
import android.os.Message;

public class ImageDownloadOperateManage {

	public static void requestImage(String imageUrl, ArrayList<String> urlList,
			Boolean isLargeImage) {
		Bundle bundle = new Bundle();
		bundle.putString(ImageDownloadModuleConstant.imageUrlKey, imageUrl);
		bundle.putStringArrayList(ImageDownloadModuleConstant.urlListKey, urlList);
		bundle.putBoolean(ImageDownloadModuleConstant.isLargeImageKey, isLargeImage);
		
		Message msg = new Message();
		msg.what = ImageDownloadDispatchThreadMessageId.MESSAGE_ID_IMAGEDOWNLOAD_ADDONE;
		msg.setData(bundle);
		ImageDownloadDispatchThread.getInstance().getHandler().sendMessage(msg);
	}
}