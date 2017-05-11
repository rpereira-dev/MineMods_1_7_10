package com.rpereira.minespells.common.packets;

import com.rpereira.minespells.MineSpells;
import com.rpereira.minespells.common.ASpell;
import com.rpereira.mineutils.Logger;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class PacketSpellToClient implements IMessage {

	/** the spell id */
	public int id;

	/** the entity caster id */
	public int caster;

	/** the entity target id */
	public int target;

	public PacketSpellToClient() {
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
	public PacketSpellToClient(int id, int caster, int target) {
		this.id = id;
		this.caster = caster;
		this.target = target;
	}

	public PacketSpellToClient(PacketSpellToServer message) {
		this(message.id, message.caster, message.target);
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

	public static class Handler implements IMessageHandler<PacketSpellToClient, IMessage> {
		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(PacketSpellToClient message, MessageContext ctx) {

			ASpell spell = MineSpells.getSpellByID(message.id);
			if (spell == null) {
				return (null);
			}

			World world = Minecraft.getMinecraft().theWorld;
			Entity caster = world.getEntityByID(message.caster);
			Entity target = world.getEntityByID(message.target);
			spell.playAnimation(caster, target);

			return (null);
		}
	}
}
