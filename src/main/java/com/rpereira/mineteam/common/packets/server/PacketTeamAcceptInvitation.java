package com.rpereira.mineteam.common.packets.server;

import com.rpereira.mineteam.common.packets.PacketServer;
import com.rpereira.mineteam.common.packets.PacketUtils;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketTeamAcceptInvitation implements IMessage {

	public String token;

	public PacketTeamAcceptInvitation() {
		this("");
	}

	public PacketTeamAcceptInvitation(String token) {
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

	public static class Handler implements IMessageHandler<PacketTeamAcceptInvitation, IMessage> {
		@Override
		public IMessage onMessage(PacketTeamAcceptInvitation message, MessageContext ctx) {

			PacketServer.instance().playerAcceptInvitation(ctx.getServerHandler().playerEntity, message.token);

			return null;

		}
	}
}