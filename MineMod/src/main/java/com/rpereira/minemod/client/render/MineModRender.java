package com.rpereira.minemod.client.render;

import com.rpereira.minemod.client.render.entity.RenderSummonedHolyBlaze;
import com.rpereira.minemod.client.render.entity.RenderSummonedZombie;
import com.rpereira.minemod.common.IMineModProxy;
import com.rpereira.minemod.common.entity.EntitySummonableHolyBlaze;
import com.rpereira.minemod.common.entity.EntitySummonableZombie;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class MineModRender implements IMineModProxy {

	@Override
	public void init() {
		RenderingRegistry.registerEntityRenderingHandler(EntitySummonableZombie.class, new RenderSummonedZombie());
		RenderingRegistry.registerEntityRenderingHandler(EntitySummonableHolyBlaze.class,
				new RenderSummonedHolyBlaze());

	}

	@Override
	public void preInit() {
		// TODO Auto-generated method stub

	}

}
