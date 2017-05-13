package com.rpereira.mineclass.client;

import com.rpereira.mineclass.common.MineClassProxy;
import com.rpereira.mineclass.common.classes.EntityClassInstance;
import com.rpereira.mineutils.Logger;

import net.minecraft.entity.player.EntityPlayer;

public class MineClassProxyClient extends MineClassProxy {

	@Override
	public void preInit() {
		super.preInit();
		Logger.get().log(Logger.Level.FINE, "Init MineClassProxyClient");
	}
}
