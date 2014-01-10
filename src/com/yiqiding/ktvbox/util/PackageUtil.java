package com.yiqiding.ktvbox.util;

import com.yiqiding.ktvbox.globalcontrol.ApplicationExtend;

public class PackageUtil {
	/**
	 * @brief Get this application packet name
	 * @return
	 */
	public static String getAppPacketName()
	{
		return ApplicationExtend.getInstance().getApplicationContext().getPackageName();
	}
}
