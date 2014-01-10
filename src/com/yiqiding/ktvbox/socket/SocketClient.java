package com.yiqiding.ktvbox.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {
	private Socket socket;
	private OutputStream ops;
	private InputStream ips;
	private byte[] packetHeadBytes;
	private Packet packet;
	
	public Boolean connect(String ipAddress, int port)
	{
		try {
			socket = new Socket(ipAddress, port);
			socket.setSoTimeout(5000);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		try {
			ops = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
			try {
				socket.close();
				socket = null;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return false;
		}

		try {
			ips = socket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
			try {
				socket.close();
				socket = null;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		
		packetHeadBytes = new byte[6*4];
		
		return true;
	}
	
	public Boolean sendOnePacket(Packet packet)
	{
		try {
			ops.write(packet.getPacketHeadByteStream());
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		try {
			byte[] bytes = packet.getPacketContentDataByteStream();
			if (bytes!=null) {
				ops.write(bytes);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		try {
			ops.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		this.packet = packet;
		
		return true;
	}
	
	public Packet receiveOnePacket()
	{
		try {
			if(ips.read(packetHeadBytes)!=6*4)
				return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		packet.setPacketHeadParam(packetHeadBytes);
		
		int signature = packet.getPacketSignature();
		if (signature!=PacketHeadDefaultParam.defaultSignature) {
			return null;
		}
		
		int contentDataSize = packet.getPacketSize();
		if (contentDataSize == 0xffffffff) {
			return null;
		}
		
		byte[] contentData = new byte[contentDataSize];
		
		try {
			if(ips.read(contentData)!=contentDataSize)
			{
				contentData = null;
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			contentData = null;
			return null;
		}

		packet.setPacketContentData(new String(contentData));
		
		return packet;
	}
	
	public Boolean disConnect()
	{
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			packetHeadBytes = null;
			return false;
		}
		
		packetHeadBytes = null;
		return true;
	}
}