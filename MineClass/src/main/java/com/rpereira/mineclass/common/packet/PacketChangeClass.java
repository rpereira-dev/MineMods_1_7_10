package com.rpereira.mineclass.common.packet;

import com.rpereira.mineclass.MineClass;
import com.rpereira.mineclass.common.classes.EntityClass;
import com.rpereira.mineclass.common.classes.EntityClassInstance;
import com.rpereira.mineutils.Logger;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

/** a packet to be sent when the client ask for a class */
public class PacketChangeClass implements IMessage {

	/** the class id */
	public int classID;

	public PacketChangeClass() {
		this(0);
	}

	/**
	 * 
	 * @param classID
	 *            : the class id
	 */
	public PacketChangeClass(int classID) {
		this.classID = classID;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.classID = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.classID);
	}

	public static class HandlerServer implements IMessageHandler<PacketChangeClass, IMessage> {
		@Override
		public IMessage onMessage(PacketChangeClass message, MessageContext ctx) {

			boolean acceptClassReset = true;

			if (acceptClassReset) {
				EntityClass entityClass = MineClass.proxy().getEntityClassForID(message.classID);
				if (entityClass != null) {
					EntityPlayerMP player = ctx.getServerHandler().playerEntity;
					EntityClassInstance entityClassInstance = MineClass.proxy().getEntityClassInstance(player);
					if (entityClassInstance == null) {
						Logger.get().log(Logger.Level.WARNING, "A null EntityClassInstance was find for", player);
					} else {
						entityClassInstance.setClass(entityClass);
						Packets.network.sendTo(message, player);
					}
				}
			}
			return (null);
		}
	}

	public static class HandlerClient implements IMessageHandler<PacketChangeClass, IMessage> {
		@Override
		public IMessage onMessage(PacketChangeClass message, MessageContext ctx) {

			EntityClass entityClass = MineClass.proxy().getEntityClassForID(message.classID);
			EntityClassInstance entityClassInstance = MineClass.proxyClient().getEntityClassInstance();
			entityClassInstance.setClass(entityClass);
			return (null);
		}
	}
}
