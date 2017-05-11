package com.rpereira.mineclass.common.classes;

import com.rpereira.mineclass.common.stats.MineClassStats;
import com.rpereira.minestats.common.Stats;
import com.rpereira.mineutils.ChatColor;

public class EntityClassChampion extends EntityClass {

	public EntityClassChampion() {
		super(MineClassStats.STAT_RAGE, "champion", ChatColor.RED);
	}

	@Override
	public void updateClassInstance(EntityClassInstance entityClassInstance) {
		super.updateClassInstance(entityClassInstance);
		Stats stats = entityClassInstance.getStats();
		float rage = stats.get(this.getResourceStat());
		rage = rage > 0 ? rage - 1 : rage > 100 ? 100 : rage;
	}

}