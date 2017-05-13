package com.rpereira.mineexp.common.packet;

import com.rpereira.mineexp.MineExp;
import com.rpereira.mineexp.client.MineExpClient;
import com.rpereira.mineexp.common.ExpBar;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;

public class PacketExpBarInstanceRemove implements IMessage {

	public int uuid;

	public PacketExpBarInstanceRemove() {
		this(0);
	}

	public PacketExpBarInstanceRemove(int uuid) {
		this.uuid = uuid;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.uuid = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.uuid);
	}

	@SideOnly(Side.CLIENT)
	public static class Handler implements IMessageHandler<PacketExpBarInstanceRemove, IMessage> {
		@Override
		public IMessage onMessage(PacketExpBarInstanceRemove message, MessageContext ctx) {
			MineExpClient client = MineExpClient.instance();
			client.removeExpBarInstance(message.uuid);
			return (null);
		}
	}
}