package com.rpereira.minemod.common.entity;

import com.rpereira.minemod.common.IMineModProxy;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;

public class MineModEntities implements IMineModProxy {

	@Override
	public void init() {
		registerEntity(EntitySummonableZombie.class, "ZombieSummoned");
		registerEntity(EntitySummonableHolyBlaze.class, "HolyBlazeSummoned");
	}

	public static void registerEntity(Class<? extends Entity> entityClass, String string) {
		EntityRegistry.registerGlobalEntityID(entityClass, string, EntityRegistry.findGlobalUniqueEntityId(),
				Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	@Override
	public void preInit() {
		// TODO Auto-generated method stub

	}

}
