package com.yiqiding.ktvbox.socket;

import com.yiqiding.ktvbox.socket.PacketHeadDefaultParam;
import com.yiqiding.ktvbox.util.DataTypeConversion;

public class PacketHead {
	private int signature = PacketHeadDefaultParam.defaultSignature;
	private int protocol = PacketHeadDefaultParam.defaultProtocol;
	private int functionNum = -1;
	private int reserved = -1;
	private int packetSize = -1;
	private int retStatus = PacketHeadDefaultParam.defaultRetStatus;

	private byte[] bytes;
	
	public PacketHead()
	{
		bytes = new byte[6*4];
	}
	
	public void setSignature(int _signature)
	{
		signature = _signature;
	}
	
	public int getSignature()
	{
		return signature;
	}
	
	public void setProtocol(int _protocol)
	{
		protocol = _protocol;
	}
	
	public int getProtocol()
	{
		return protocol;
	}

	public void setFunctionNum(int _functionNum)
	{
		functionNum = _functionNum;
	}

	public int getFunctionNum()
	{
		return functionNum;
	}
	
	public void setReserved(int _reserved)
	{
		reserved = _reserved;
	}
	
	public int getReserved()
	{
		return reserved;
	}
	
	public void setPacketSize(int _packetSize)
	{
		packetSize = _packetSize;
	}
	
	public int getPacketSize()
	{
		return packetSize;
	}
	
	public void setRetStatus(int _retStatus)
	{
		retStatus = _retStatus;
	}
	
	public int getRetStatus()
	{
		return retStatus;
	}
	
	public byte[] getByteStream()
	{
		bytes[3] = (byte) (0xff&this.signature);  
		bytes[2] = (byte) ((0xff00&this.signature) >> 8);  
		bytes[1] = (byte) ((0xff0000&this.signature) >> 16);  
		bytes[0] = (byte) ((0xff000000&this.signature) >> 24);
		
		bytes[7] = (byte) (0xff&this.protocol);  
		bytes[6] = (byte) ((0xff00&this.protocol) >> 8);  
		bytes[5] = (byte) ((0xff0000&this.protocol) >> 16);  
		bytes[4] = (byte) ((0xff000000&this.protocol) >> 24);
		
		bytes[11] = (byte) (0xff&this.functionNum);  
		bytes[10] = (byte) ((0xff00&this.functionNum) >> 8);  
		bytes[9] = (byte) ((0xff0000&this.functionNum) >> 16);  
		bytes[8] = (byte) ((0xff000000&this.functionNum) >> 24);

		bytes[15] = (byte) (0xff&this.reserved);  
		bytes[14] = (byte) ((0xff00&this.reserved) >> 8);  
		bytes[13] = (byte) ((0xff0000&this.reserved) >> 16);  
		bytes[12] = (byte) ((0xff000000&this.reserved) >> 24);
		
		bytes[19] = (byte) (0xff&this.packetSize);  
		bytes[18] = (byte) ((0xff00&this.packetSize) >> 8);  
		bytes[17] = (byte) ((0xff0000&this.packetSize) >> 16);  
		bytes[16] = (byte) ((0xff000000&this.packetSize) >> 24);
		
		bytes[23] = (byte) (0xff&this.retStatus);  
		bytes[22] = (byte) ((0xff00&this.retStatus) >> 8);  
		bytes[21] = (byte) ((0xff0000&this.retStatus) >> 16);  
		bytes[20] = (byte) ((0xff000000&this.retStatus) >> 24);

		return bytes;
	}
	
	public void setPacketHeadParam(byte[] byteStream)
	{
		this.setSignature(DataTypeConversion.bytes2int(byteStream, 0));
		this.setProtocol(DataTypeConversion.bytes2int(byteStream, 4));
		this.setFunctionNum(DataTypeConversion.bytes2int(byteStream, 8));
		this.setReserved(DataTypeConversion.bytes2int(byteStream, 12));
		this.setPacketSize(DataTypeConversion.bytes2int(byteStream, 16));
		this.setRetStatus(DataTypeConversion.bytes2int(byteStream, 20));
	}
}