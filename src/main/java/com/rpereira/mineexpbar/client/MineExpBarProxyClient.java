package com.rpereira.mineexpbar.client;

import com.rpereira.mineexpbar.common.MineExpBarProxy;
import com.rpereira.mineutils.Logger;

public class MineExpBarProxyClient extends MineExpBarProxy {

	@Override
	public void preInit() {
		super.preInit();
		Logger.get().log(Logger.Level.FINE, "Init MineExpBarProxyClient");
	}
}
