package com.rpereira.minespells.client;

import com.rpereira.minespells.common.MineSpellProxy;
import com.rpereira.minespells.common.Spell;
import com.rpereira.minespells.common.packets.PacketSpell;
import com.rpereira.minespells.common.packets.Packets;
import com.rpereira.mineutils.Logger;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.entity.Entity;

public class MineSpellProxyClient extends MineSpellProxy {

	private static MineSpellProxyClient instance;

	public MineSpellProxyClient() {
		super();
		instance = this;
	}

	public static MineSpellProxyClient instance() {
		return (instance);
	}

	@Override
	public void preInit() {
		super.preInit();
		Logger.get().log(Logger.Level.FINE, "Init MineSpellProxyClient");
	}

	/** try to launch the spell to the server */
	public static void launchSpell(Entity caster, Entity target, Spell spell) {
		if (spell == null) {
			return;
		}
		int spellID = spell.getID();
		int casterID = caster == null ? -1 : caster.getEntityId();
		int targetID = target == null ? -1 : target.getEntityId();
		IMessage packet = new PacketSpell(spellID, casterID, targetID);
		Packets.network.sendToServer(packet);

	}
}
