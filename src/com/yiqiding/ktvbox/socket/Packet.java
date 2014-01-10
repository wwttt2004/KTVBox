package com.yiqiding.ktvbox.socket;

public class Packet {
	private PacketHead packetHead;
	private String contentData;
	
	public Packet()
	{
		packetHead = new PacketHead();
	}
	
	public void setPacketContentData(String data)
	{
		this.contentData = data;
		if (this.contentData==null) {
			packetHead.setPacketSize(0);
		}else {
			packetHead.setPacketSize(this.contentData.length());
		}
	}
	
	public void setPacketFunctionNum(int functionNum)
	{
		this.packetHead.setFunctionNum(functionNum);
	}
	
	public void setPacketRetStatus(int retStatus)
	{
		this.packetHead.setRetStatus(retStatus);
	}
	
	public void setPacketReserved(int reserved)
	{
		this.packetHead.setReserved(reserved);
	}

	public void setPacketHeadParam(byte[] byteStream)
	{
		this.packetHead.setPacketHeadParam(byteStream);
	}

	public int getPacketSignature()
	{
		return this.packetHead.getSignature();
	}
	
	public int getPacketProtocol()
	{
		return this.packetHead.getProtocol();
	}
	
	public int getPacketFunctionNum()
	{
		return this.packetHead.getFunctionNum();
	}
	
	public int getPacketReserved()
	{
		return this.packetHead.getReserved();
	}
	
	public int getPacketSize()
	{
		return this.packetHead.getPacketSize();
	}
	
	public int getPacketRetStatus()
	{
		return this.packetHead.getRetStatus();
	}
	
	public byte[] getPacketHeadByteStream()
	{
		return this.packetHead.getByteStream();
	}
	
	public byte[] getPacketContentDataByteStream()
	{
		if (this.contentData==null) {
			return null;
		}else {
			return this.contentData.getBytes();
		}
	}
	
	public String getPacketContentDataString()
	{
		return this.contentData;
	}
}