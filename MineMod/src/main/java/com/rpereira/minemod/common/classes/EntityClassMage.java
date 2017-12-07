package com.rpereira.minemod.common.classes;

import com.rpereira.mineclass.common.classes.EntityClassInstance;
import com.rpereira.mineclass.common.classes.EntityClassMana;
import com.rpereira.mineclass.common.stats.MineClassStats;
import com.rpereira.minemod.common.spells.MineModSpells;
import com.rpereira.minemod.common.stats.MineModStats;
import com.rpereira.minestats.common.Stats;
import com.rpereira.mineutils.ChatColor;

public class EntityClassMage extends EntityClassMana {

	/** the number of clarity points for a mana per sec point */
	private static final float MANA_PER_SEC_PER_SPIRIT = 1 / 10.0f;

	/** number of extra mana point per clarity points */
	private static final float MANA_PER_CLARITY = 5.0f;

	public EntityClassMage() {
		super("minemod:textures/gui/classes/mage.png", "mage", ChatColor.BLUE);

		// add spells
		super.addSpell(MineModSpells.FIRECONE);
		super.addSpell(MineModSpells.FREEZECUBE);
		super.addSpell(MineModSpells.TRANSFERT);
		super.addSpell(MineModSpells.FIElD_DESTRUCTION);

		// add stats
		super.setDefaultStat(MineModStats.STAT_CLARITY, MineModStats.STAT_CLARITY.getDefaultValue());
		super.setDefaultStat(MineModStats.STAT_SPIRIT, MineModStats.STAT_SPIRIT.getDefaultValue());
		super.setDefaultStat(MineClassStats.STAT_MANA_PER_SEC, MineClassStats.STAT_MANA_PER_SEC.getDefaultValue());

		// set stats per level
		super.setStatPerLevel(MineClassStats.STAT_MAX_MANA, 10);
		super.setStatPerLevel(MineModStats.STAT_CLARITY, 1);
		super.setStatPerLevel(MineModStats.STAT_SPIRIT, 1);
		super.setStatPerLevel(MineModStats.STAT_STAMINA, 1);
	}

	@Override
	public void onResourcesUpdated(EntityClassInstance entityClassInstance) {
		super.onResourcesUpdated(entityClassInstance);

		// set stats properly
		Stats stats = entityClassInstance.getStats();
		stats.addStat(MineClassStats.STAT_MAX_MANA, stats.get(MineModStats.STAT_CLARITY) * MANA_PER_CLARITY);
		stats.addStat(MineClassStats.STAT_MANA_PER_SEC, stats.get(MineModStats.STAT_CLARITY) * MANA_PER_SEC_PER_SPIRIT);
		stats.addStat(MineClassStats.STAT_MAX_MANA, stats.get(MineModStats.STAT_CLARITY) * MANA_PER_CLARITY);
	}

	@Override
	public int getRGBColor() {
		return (0xFF2266AA);
	}

	@Override
	public String[] getAdvices() {
		return (new String[] { ChatColor.GREEN + "+ " + MineModStats.STAT_CLARITY.getName() + ChatColor.RESET,
				ChatColor.GREEN + "+ " + MineClassStats.STAT_MANA.getName() + ChatColor.RESET,
				ChatColor.GREEN + "+ " + MineModStats.STAT_MAGIC.getName() + ChatColor.RESET,
				ChatColor.RED + "- " + MineModStats.STAT_STRENGTH.getName() + ChatColor.RESET,
				ChatColor.RED + "- " + MineModStats.STAT_AGILITY.getName() + ChatColor.RESET, });
	}
}