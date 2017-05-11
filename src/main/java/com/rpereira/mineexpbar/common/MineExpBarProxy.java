package com.rpereira.mineexpbar.common;

import com.rpereira.mineutils.Logger;

public class MineExpBarProxy {

	public void preInit() {
		Logger.get().log(Logger.Level.FINE, "PreInit MineExpBarProxy");
	}

	public void init() {
		Logger.get().log(Logger.Level.FINE, "Init MineExpBarProxy");
	}
}
