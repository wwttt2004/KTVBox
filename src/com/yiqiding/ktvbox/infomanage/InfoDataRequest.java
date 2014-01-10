package com.yiqiding.ktvbox.infomanage;

import com.yiqiding.ktvbox.socket.Packet;
import com.yiqiding.ktvbox.socket.PacketHeadDefaultParam;
import com.yiqiding.ktvbox.socket.SocketClient;

public class InfoDataRequest extends SocketClient{
	private Packet packet;
	private int requestfunctionNum;
	private String respond;

	public InfoDataRequest()
	{
		packet = new Packet();
	}
	
	public String Get(String ipAddress, int port, int functionNum, String requestData, int reserved)
	{
		if (!this.connect(ipAddress, port)) {
			return null;
		}
		
		this.requestfunctionNum = functionNum;

		packet.setPacketFunctionNum(functionNum);
		packet.setPacketReserved(reserved);
		packet.setPacketContentData(requestData);
		packet.setPacketRetStatus(PacketHeadDefaultParam.defaultRetStatus);

		this.sendOnePacket(packet);
		
		Packet revPacket = this.receiveOnePacket();
		
		if (revPacket==null || revPacket.getPacketFunctionNum()!=this.requestfunctionNum) {
			packet.setPacketFunctionNum(this.requestfunctionNum);
			packet.setPacketReserved(reserved);
			packet.setPacketContentData(null);
			packet.setPacketRetStatus(PacketHeadDefaultParam.failRetStatus);
			
			this.sendOnePacket(packet);
			
			return null;
		}
		
		respond = revPacket.getPacketContentDataString();
		
		packet.setPacketFunctionNum(this.requestfunctionNum);
		packet.setPacketReserved(reserved);
		packet.setPacketContentData(null);
		packet.setPacketRetStatus(PacketHeadDefaultParam.successRetStatus);
		
		this.sendOnePacket(packet);
		
		this.disConnect();
		
		return respond;
	}
}
