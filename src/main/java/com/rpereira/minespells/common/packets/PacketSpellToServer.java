package com.rpereira.minespells.common.packets;

import com.rpereira.minespells.MineSpells;
import com.rpereira.minespells.common.ASpell;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class PacketSpellToServer implements IMessage {

	/** the spell id */
	public int id;

	/** the entity caster id */
	public int caster;

	/** the entity target id */
	public int target;

	public PacketSpellToServer() {
		this(-1, -1, -1);
	}

	/**
	 * 
	 * @param id
	 *            : the spell id
	 * @param caster
	 *            : the caster entity id
	 * @param target
	 *            : the target entity id
	 */
	public PacketSpellToServer(int id, int caster, int target) {
		this.id = id;
		this.caster = caster;
		this.target = target;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.id = buf.readInt();
		this.caster = buf.readInt();
		this.target = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.id);
		buf.writeInt(this.caster);
		buf.writeInt(this.target);
	}

	public static class Handler implements IMessageHandler<PacketSpellToServer, IMessage> {

		/** the max distance the spell animation should be play */
		public static final double ANIMATION_DISTANCE = 64.0d;

		@Override
		public IMessage onMessage(PacketSpellToServer message, MessageContext ctx) {
			ASpell spell = MineSpells.getSpellByID(message.id);

			if (spell == null) {
				return (null);
			}

			// do the spell
			World world = ctx.getServerHandler().playerEntity.worldObj;
			Entity caster = world.getEntityByID(message.caster);
			Entity target = world.getEntityByID(message.target);

			spell.processSpell(caster, target);

			// send response to client
			Entity entity = (target == null) ? ((caster == null) ? ctx.getServerHandler().playerEntity : caster)
					: target;
			TargetPoint point = new TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ,
					ANIMATION_DISTANCE);
			Packets.network.sendToAllAround(new PacketSpellToClient(message), point);
			return (null);
		}
	}
}
