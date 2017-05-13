package com.rpereira.mineexp.common.packet;

import java.util.HashMap;

import com.rpereira.mineexp.client.MineExpClient;
import com.rpereira.mineexp.common.ExpBarInstance;
import com.rpereira.mineutils.PacketUtils;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;

public class PacketExpBarUpdate implements IMessage {

	private int uuid;
	private HashMap<Object, Object> attributes;

	public PacketExpBarUpdate() {

	}

	public PacketExpBarUpdate(ExpBarInstance expBarInstance) {
		this.uuid = expBarInstance.getUUID();
		this.attributes = expBarInstance.getAttributes();
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.uuid = buf.readInt();
		try {
			this.attributes = PacketUtils.readHashMap(buf);
		} catch (Exception e) {
			e.printStackTrace();
			this.attributes = null;
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.uuid);
		try {
			PacketUtils.writeHashMap(buf, this.attributes);
		} catch (Exception e) {
			buf.writeInt(0);
		}
	}

	@SideOnly(Side.CLIENT)
	public static class Handler implements IMessageHandler<PacketExpBarUpdate, IMessage> {
		@Override
		public IMessage onMessage(PacketExpBarUpdate message, MessageContext ctx) {
			ExpBarInstance expBarInstance = MineExpClient.instance().getExpBarInstance(message.uuid);
			if (expBarInstance != null) {
				// TODO : update it
			}
			return (null);
		}
	}
}