package com.rpereira.mineexp.common;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class MineExpServer extends MineExpProxy {

	private static MineExpServer MINE_EXP_SERVER;

	private int nextExpBarInstanceUUID = 0;

	public MineExpServer() {
		super();
		MINE_EXP_SERVER = this;
	}

	@SubscribeEvent
	public void onServerTick(TickEvent.ServerTickEvent event) {
		for (ExpBarInstance expBarInstance : this.expBarInstances.values()) {
			expBarInstance.update();
		}
	}

	public final static MineExpServer instance() {
		return (MINE_EXP_SERVER);
	}

	public ExpBarInstance createExpBarInstance(ExpBar expBar, Object... attributes) {
		return (super.createExpBarInstance(expBar, this.nextExpBarInstanceUUID++, attributes));
	}
}