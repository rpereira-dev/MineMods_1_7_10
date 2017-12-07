package com.rpereira.mineclass.common.classes;

import com.rpereira.mineclass.common.stats.MineClassStats;
import com.rpereira.minestats.common.Stats;
import com.rpereira.mineutils.ChatColor;

public abstract class EntityClassMana extends EntityClass {

	/** ms required to tick */
	private static final long MS_TO_INCREMENT = 1000;

	public EntityClassMana(String resLocation, String unlocalizedName, ChatColor chatColor) {
		super(resLocation, MineClassStats.STAT_MANA, unlocalizedName, chatColor);
	}

	@Override
	public void onResourcesUpdated(EntityClassInstance entityClassInstance) {
		super.onResourcesUpdated(entityClassInstance);
		// updata mana
		Stats stats = entityClassInstance.getStats();
		long curr = System.currentTimeMillis();
		float mana = entityClassInstance.getResource();
		if (mana < entityClassInstance.getMaxResource()) {
			long lastIncrement = entityClassInstance.getAttribute("lastIncrement", 0L);
			float dt = (curr - lastIncrement) / 1000.0f;
			float manaPerSec = stats.get(MineClassStats.STAT_MANA_PER_SEC, 0.0f);

			// depending on last combat
			long lastAttack = entityClassInstance.getAttribute("lastAttack", 0L);
			float dtcombat = (curr - lastAttack) / 1000.0f;
			manaPerSec *= (1 + Math.atan(dtcombat * 0.2f) * 4.0F);

			float manaToAdd = manaPerSec * dt;

			entityClassInstance.addResource(manaToAdd);
			entityClassInstance.setAttribute("lastIncrement", curr);
		}
	}
}
