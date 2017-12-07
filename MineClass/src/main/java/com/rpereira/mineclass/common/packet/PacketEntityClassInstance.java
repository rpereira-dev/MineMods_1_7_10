package com.rpereira.mineclass.common.packet;

import java.util.HashMap;

import com.rpereira.mineclass.MineClass;
import com.rpereira.mineclass.common.classes.EntityClassInstance;
import com.rpereira.minestats.common.Stat;
import com.rpereira.mineutils.PacketUtils;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

/** a packet to be sent when the client ask for a class */
public class PacketEntityClassInstance implements IMessage {

	/** the entity id */
	public int entityID;
	public int classID;
	public HashMap<Object, Object> attributes;
	public HashMap<Stat, Float> stats;

	public PacketEntityClassInstance() {
	}

	/**
	 * 
	 * @param classID
	 *            : the class id
	 */
	public PacketEntityClassInstance(EntityClassInstance entityClassInstance) {
		this.entityID = entityClassInstance.getEntity().getEntityId();
		this.classID = entityClassInstance.getEntityClass().getID();
		this.attributes = entityClassInstance.getAttributes();
		this.stats = entityClassInstance.getStats().getStats();
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.entityID = buf.readInt();
		this.classID = buf.readInt();
		try {
			this.attributes = (HashMap<Object, Object>) PacketUtils.readHashMap(buf);
			this.stats = (HashMap<Stat, Float>) PacketUtils.readHashMap(buf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.entityID);
		buf.writeInt(this.classID);
		try {
			PacketUtils.writeHashMap(buf, this.attributes);
			PacketUtils.writeHashMap(buf, this.stats);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static class HandlerClient implements IMessageHandler<PacketEntityClassInstance, IMessage> {
		@Override
		public IMessage onMessage(PacketEntityClassInstance message, MessageContext ctx) {
			Entity entity = Minecraft.getMinecraft().theWorld.getEntityByID(message.entityID);
			if (entity == null || !(entity instanceof EntityLivingBase)) {
				return (null);
			}
			EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
			EntityClassInstance entityClassInstance = MineClass.proxyClient().getEntityClassInstance(entityLivingBase);
			if (entityClassInstance == null) {
				entityClassInstance = new EntityClassInstance(entityLivingBase);
				MineClass.proxyClient().spawnEntityClassInstance(entityClassInstance);
			}
			entityClassInstance.setClass(MineClass.proxy().getEntityClassForID(message.classID));
			entityClassInstance.setStats(message.stats);
			entityClassInstance.setAttributes(message.attributes);
			return (null);
		}
	}
}
