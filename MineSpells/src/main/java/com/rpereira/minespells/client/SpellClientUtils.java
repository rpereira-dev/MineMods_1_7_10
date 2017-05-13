package com.rpereira.minespells.client;

import com.rpereira.minespells.common.Spell;
import com.rpereira.minespells.common.packets.PacketSpellToServer;
import com.rpereira.minespells.common.packets.Packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class SpellClientUtils {

	/** spawn a particle */
	public static void spawnParticle(EntityFX fx) {
		Minecraft.getMinecraft().effectRenderer.addEffect(fx);
	}

	/** try to launch the spell to the server */
	public static void clientLaunchSpell(Entity caster, Entity target, Spell spell) {
		if (spell == null) {
			return;
		}
		IMessage packet = new PacketSpellToServer(spell.getID(), caster == null ? -1 : caster.getEntityId(),
				target == null ? -1 : target.getEntityId());
		Packets.network.sendToServer(packet);
	}
}
