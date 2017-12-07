package com.rpereira.minespells.common;

import com.rpereira.minespells.common.packets.PacketSpell;
import com.rpereira.minespells.common.packets.Packets;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraft.entity.Entity;

public class MineSpellProxyServer extends MineSpellProxy {

	public static final void launchSpell(Entity caster, Entity target, Spell spell) {

		if (!spell.requireNonNullTarget() || target != null) {
			spell.processSpell(caster, target);
		}

		// send response to client
		Entity entity = (target == null) ? caster : target;
		double x = entity.posX;
		double y = entity.posY;
		double z = entity.posZ;
		double range = ((spell.getRange() < 16.0f) ? 16.0f : spell.getRange()) * 2.0f;
		TargetPoint point = new TargetPoint(entity.dimension, x, y, z, range);
		int spellID = spell.getID();
		int casterID = caster.getEntityId();
		int targetID = target == null ? -1 : target.getEntityId();
		Packets.network.sendToAllAround(new PacketSpell(spellID, casterID, targetID), point);
	}
}
