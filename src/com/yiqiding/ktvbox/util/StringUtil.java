package com.yiqiding.ktvbox.util;

public class StringUtil {
	public static String imageUrl_2_ImageName(String imageUrl)
	{
		if (imageUrl==null || imageUrl.isEmpty() || !imageUrl.contains("http://")) {
			return null;
		}
		
		int start = imageUrl.lastIndexOf('/');
		if (start == -1) {
			return null;
		}
		
		int end = imageUrl.lastIndexOf('.');
		if (end == -1) {
			return null;
		}
		
		if (end<=start+1) {
			return null;
		}
		
		return imageUrl.substring(start+1, end);
	}
	
	public static String switchServerAddr(String imageUrl, String serverAddr)
	{
		String subString = imageUrl.substring(7);
		int index = subString.indexOf('/');
		
		return "http://" + serverAddr + subString.substring(index);
	}
}
