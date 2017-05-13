package com.rpereira.minespells.client;

import com.rpereira.minespells.common.MineSpellProxy;
import com.rpereira.mineutils.Logger;

public class MineSpellProxyClient extends MineSpellProxy {

	@Override
	public void preInit() {
		super.preInit();
		Logger.get().log(Logger.Level.FINE, "Init MineSpellProxyClient");
	}
}
