package com.yiqiding.ktvbox.infomanage;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.os.Message;

import com.yiqiding.ktvbox.globalcontrol.ApplicationExtend;
import com.yiqiding.ktvbox.globalcontrol.MainThreadMessageID;
import com.yiqiding.ktvbox.http.Http;
import com.yiqiding.ktvbox.operatemanage.InfoOperateManage;


public class InfoRequestTask {
	public static boolean taskStatus = false;
	private static InfoXmlMemoryCache infoMemoryCache = new InfoXmlMemoryCache();
	private static InfoXmlIO infoXmlIO = new InfoXmlIO();
	private static InfoDataRequest infoDataRequest = new InfoDataRequest();
	private static ExecutorService infoSingleThreadExecutor = Executors.newSingleThreadExecutor();
	
	public static void triggerNewTask()
	{
		infoSingleThreadExecutor.execute(new Runnable() {
			
			@Override
			public void run() {
				InfoRequestTask.taskStatus = true;
				
				while (!InfoOperateManage.infoOperateStack.isEmpty()) {
					//pop an entry from stack
					Bundle entryBundle = InfoOperateManage.infoOperateStack.pop();
					// first search from memory cache, if found, send message to main thread
					// if not found, request from network
					String cacheKey = entryBundle.getString(InfoModuleConstant.infoMemoryCacheKeyKey);
					int functionNum = entryBundle.getInt(InfoModuleConstant.functionNumKey);
					Bundle info = entryBundle.getBundle(InfoModuleConstant.functionRequestInfoKey);
					int reserved = entryBundle.getInt(InfoModuleConstant.functionRequestTimeStampKey);
					String contentXmlRespondData = infoMemoryCache.getEntryFromCache(cacheKey);
					if (contentXmlRespondData==null) {
						//create request contentData with xml
						String contentXmlRequestData = infoXmlIO.writeInfoXml(functionNum, info);
						//request by socket
						contentXmlRespondData = infoDataRequest.Get(InfoFunctionInterface.serveripAddress, InfoFunctionInterface.serverport, functionNum, contentXmlRequestData, reserved);
						
						//request by http
/*						try {
							contentXmlRespondData =  EntityUtils.toString(Http.get(""),"UTF-8");
						} catch (ParseException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}*/
						
						if (contentXmlRespondData!=null)
						{
							if (infoXmlIO.checkIsErrorInfoXml(contentXmlRespondData)) {
								contentXmlRespondData = null;
							}else {
								//put data into memory cache
								infoMemoryCache.addEntryToCache(cacheKey, contentXmlRespondData);
							}
						}
					}
					
					if (reserved==InfoOperateManage.lastInfoOperateTimeStamp) {
						if (contentXmlRespondData!=null) {
							//parse respond contentData with xml
							Bundle result = infoXmlIO.parseInfoXml(functionNum, contentXmlRespondData);

							//send message to main thread
							Bundle bundle = new Bundle();
							bundle.putInt(InfoModuleConstant.functionRequestTimeStampKey, reserved);
							bundle.putInt(InfoModuleConstant.functionNumKey, functionNum);
							bundle.putBundle(InfoModuleConstant.infoResultKey, result);
							Message msg = new Message();
							msg.what = MainThreadMessageID.MESSAGE_INFO_SUCCESS;
							msg.setData(bundle);
							ApplicationExtend.getInstance().getMainThreadhandler().sendMessage(msg);	
						}else {
							String result = "Get info fail!";
							//send error message to main thread
							Bundle bundle = new Bundle();
							bundle.putInt(InfoModuleConstant.functionRequestTimeStampKey, reserved);
							bundle.putInt(InfoModuleConstant.functionNumKey, functionNum);
							bundle.putString(InfoModuleConstant.infoResultKey, result);
							Message msg = new Message();
							msg.what = MainThreadMessageID.MESSAGE_INFO_ERROR;
							msg.setData(bundle);
							ApplicationExtend.getInstance().getMainThreadhandler().sendMessage(msg);	
						}
					}
				}
				
				InfoRequestTask.taskStatus = false;
			}
		});
	}
	
	public static void resetTask()
	{
		infoSingleThreadExecutor.shutdown();
		InfoRequestTask.taskStatus = false;
	}

}
