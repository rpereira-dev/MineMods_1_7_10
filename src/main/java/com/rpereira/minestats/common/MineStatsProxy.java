package com.rpereira.minestats.common;

import com.rpereira.mineutils.Logger;

public class MineStatsProxy {
	public void preInit() {
		Logger.get().log(Logger.Level.FINE, "PreInit MineStatsProxy");
	}

	public void init() {
		Logger.get().log(Logger.Level.FINE, "Init MineStatsProxy");
	}
}
