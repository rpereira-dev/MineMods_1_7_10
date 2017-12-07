package com.rpereira.minespells.common;

import com.rpereira.minespells.common.packets.Packets;
import com.rpereira.mineutils.Logger;

import net.minecraft.entity.Entity;

public abstract class MineSpellProxy {

	public void preInit() {
		Logger.get().log(Logger.Level.FINE, "PreInit MineSpellProxy");
		Packets.preInit();
	}

	public void init() {
		Logger.get().log(Logger.Level.FINE, "Init MineSpellProxy");
	}
}
