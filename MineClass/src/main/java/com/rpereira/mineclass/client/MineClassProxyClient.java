package com.rpereira.mineclass.client;

import com.rpereira.mineclass.common.MineClassProxy;
import com.rpereira.mineclass.common.classes.EntityClassInstance;
import com.rpereira.mineutils.Logger;

import net.minecraft.client.Minecraft;

public class MineClassProxyClient extends MineClassProxy {

	@Override
	public void preInit() {
		super.preInit();
		Logger.get().log(Logger.Level.FINE, "Init MineClassProxyClient");
	}

	/** return the class instance of the client */
	public EntityClassInstance getEntityClassInstance() {
		return (super.getEntityClassInstance(Minecraft.getMinecraft().thePlayer));
	}
}
