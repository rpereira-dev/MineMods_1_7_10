package com.rpereira.minespells.common;

import com.rpereira.minespells.common.packets.Packets;
import com.rpereira.mineutils.Logger;

public class MineSpellProxy {

	public void preInit() {
		Logger.get().log(Logger.Level.FINE, "PreInit MineSpellProxy");
		Packets.preInit();
	}

	public void init() {
		Logger.get().log(Logger.Level.FINE, "Init MineSpellProxy");
	}
}
