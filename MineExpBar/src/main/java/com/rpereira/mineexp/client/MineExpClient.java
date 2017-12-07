package com.rpereira.mineexp.client;

import com.rpereira.mineexp.common.ExpBarInstance;
import com.rpereira.mineexp.common.MineExpProxy;
import com.rpereira.mineutils.Logger;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MineExpClient extends MineExpProxy {

	private static MineExpClient MINE_EXP_CLIENT;

	public MineExpClient() {
		MINE_EXP_CLIENT = this;
	}

	@Override
	public void preInit() {
		super.preInit();
		Logger.get().log(Logger.Level.FINE, "Init MineExpClient");
	}

	public static final MineExpClient instance() {
		return (MINE_EXP_CLIENT);
	}

	@SubscribeEvent
	public void onServerTick(TickEvent.ClientTickEvent event) {
		for (ExpBarInstance expBarInstance : this.expBarInstances.values()) {
			expBarInstance.update();
		}
	}

}
