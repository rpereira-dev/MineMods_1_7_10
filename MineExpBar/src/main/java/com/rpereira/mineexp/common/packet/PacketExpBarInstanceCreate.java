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

public class PacketExpBarInstanceCreate implements IMessage {

	public int expbarid;
	public int uuid;

	public PacketExpBarInstanceCreate() {
		this(0, 0);
	}

	public PacketExpBarInstanceCreate(int expbarid, int uuid) {
		this.expbarid = expbarid;
		this.uuid = uuid;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.expbarid = buf.readInt();
		this.uuid = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.expbarid);
		buf.writeInt(this.uuid);
	}

	@SideOnly(Side.CLIENT)
	public static class Handler implements IMessageHandler<PacketExpBarInstanceCreate, IMessage> {
		@Override
		public IMessage onMessage(PacketExpBarInstanceCreate message, MessageContext ctx) {
			ExpBar expBar = MineExp.getExpBar(message.expbarid);
			MineExpClient client = MineExpClient.instance();
			client.createExpBarInstance(expBar, message.uuid);
			return (null);
		}
	}
}