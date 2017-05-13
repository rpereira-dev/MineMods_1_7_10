package com.rpereira.mineexp.client;

import com.rpereira.mineexp.common.ExpBar;
import com.rpereira.mineexp.common.ExpBarInstance;
import com.rpereira.mineexp.common.MineExpProxy;
import com.rpereira.mineutils.Logger;

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

	@Override
	public ExpBarInstance createExpBarInstance(ExpBar expBar, int uuid, Object... attributes) {
		ExpBarInstance expBarInstance = new ExpBarInstance(expBar, uuid, attributes);
		this.expBarInstances.put(uuid, expBarInstance);
		return (expBarInstance);
	}
}
