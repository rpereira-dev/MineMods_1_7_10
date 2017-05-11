package com.rpereira.mineclass.client;

import com.rpereira.mineclass.common.MineClassProxy;
import com.rpereira.mineutils.Logger;

public class MineClassProxyClient extends MineClassProxy {

	@Override
	public void preInit() {
		super.preInit();
		Logger.get().log(Logger.Level.FINE, "Init MineClassProxyClient");
	}
}
