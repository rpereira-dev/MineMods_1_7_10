package com.rpereira.mineclass.common.classes;

import com.rpereira.mineclass.common.stats.MineClassStats;
import com.rpereira.mineutils.ChatColor;

public abstract class EntityClassEnergy extends EntityClass {

	/** the number of energy point regen per sec */
	private static final float POINT_PER_SEC = 10.0F;

	public EntityClassEnergy(String resLocation, String unlocalizedName, ChatColor chatColor) {
		super(resLocation, MineClassStats.STAT_ENERGY, unlocalizedName, chatColor);
	}

	@Override
	public void updateClassInstance(EntityClassInstance entityClassInstance) {
		super.updateClassInstance(entityClassInstance);

		long curr = System.currentTimeMillis();
		float energy = entityClassInstance.getResource();
		if (energy < entityClassInstance.getMaxResource()) {
			long lastDecrement = entityClassInstance.getAttribute("lastIncrement", 0L);
			float dt = (curr - lastDecrement) / 1000.0f;
			float energyToAdd = (float) (POINT_PER_SEC * dt);

			entityClassInstance.addResource(energyToAdd);
			entityClassInstance.setAttribute("lastIncrement", curr);
		}
	}
}
