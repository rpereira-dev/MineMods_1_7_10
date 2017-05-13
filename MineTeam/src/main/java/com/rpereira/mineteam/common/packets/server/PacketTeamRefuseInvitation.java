package com.rpereira.mineteam.common.packets.server;

import com.rpereira.mineteam.common.packets.PacketServer;
import com.rpereira.mineutils.PacketUtils;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketTeamRefuseInvitation implements IMessage {

	public String token;

	public PacketTeamRefuseInvitation() {
		this("");
	}

	public PacketTeamRefuseInvitation(String token) {
		this.token = token;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.token = PacketUtils.readString(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		PacketUtils.writeString(buf, this.token);
	}

	public static class Handler implements IMessageHandler<PacketTeamRefuseInvitation, IMessage> {
		@Override
		public IMessage onMessage(PacketTeamRefuseInvitation message, MessageContext ctx) {
			PacketServer.instance().playerRefuseInvitation(message.token);
			return null;
		}
	}
}