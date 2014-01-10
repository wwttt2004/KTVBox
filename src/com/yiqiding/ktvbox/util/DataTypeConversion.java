package com.yiqiding.ktvbox.util;

public class DataTypeConversion {
	
	//网络序
	public static byte[] int2bytes(int i)
	{
		byte[] b = new byte[4];
		b[3] = (byte) (0xff&i);
		b[2] = (byte) ((0xff00&i) >> 8);
		b[1] = (byte) ((0xff0000&i) >> 16);
		b[0] = (byte) ((0xff000000&i) >> 24);
		return b;
	}
	
	//网络序
	public static int bytes2int(byte[] bytes, int offset)
	{
		int result = 0; 
		int n1, n2, n3, n4; 

		n4 = bytes[offset + 3] & 0xFF; 
		n3 = bytes[offset + 2] & 0xFF; 
		n2 = bytes[offset + 1] & 0xFF; 
		n1 = bytes[offset] & 0xFF; 

		result = n1 << 24  | n2 << 16 | n3 << 8 | n4; 

		return result;  
	}
}
