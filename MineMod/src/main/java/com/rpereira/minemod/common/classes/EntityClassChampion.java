package com.rpereira.minemod.common.classes;

import com.rpereira.mineclass.common.classes.EntityClassInstance;
import com.rpereira.mineclass.common.classes.EntityClassRage;
import com.rpereira.mineclass.common.stats.MineClassStats;
import com.rpereira.minemod.common.spells.MineModSpells;
import com.rpereira.minemod.common.stats.MineModStats;
import com.rpereira.minestats.common.Stats;
import com.rpereira.mineutils.ChatColor;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public class EntityClassChampion extends EntityClassRage {

	/** ms required to decrease the rage */
	private static final long MS_TO_DECREASE = 200;

	/** number of rage point to decrease on each decrease */
	private static final long AMOUNT_TO_DECREASE = 1;

	/** the number of ms when the rage begin decreasing after a fight */
	private static final long MS_DECREASE_AFTER_FIGHT = 8000;

	/** number of power to gain by having 1 strength point */
	private static final float POWER_PER_STRENGTH = 5.0f;

	/** number of additional damages per power points */
	private static final float DAMAGES_PER_POWER_POINT = 1 / 20.0f;

	public EntityClassChampion() {
		super("minemod:textures/gui/classes/champion.png", "champion", ChatColor.RED);
		super.addSpell(MineModSpells.CHARGE);
		super.addSpell(MineModSpells.SHOCKWAVE);
		super.addSpell(MineModSpells.CLEAVE);

		// add stats
		super.setDefaultStat(MineModStats.STAT_STRENGTH, MineModStats.STAT_STRENGTH.getDefaultValue());
		super.setDefaultStat(MineModStats.STAT_AGILITY, MineModStats.STAT_AGILITY.getDefaultValue());

		// stats per level
		super.setStatPerLevel(MineModStats.STAT_STRENGTH, 1.5f);
		super.setStatPerLevel(MineModStats.STAT_AGILITY, 0.5f);
		super.setStatPerLevel(MineModStats.STAT_STAMINA, 1);
	}

	@Override
	public void onResourcesUpdated(EntityClassInstance entityClassInstance) {
		super.onResourcesUpdated(entityClassInstance);

		Stats stats = entityClassInstance.getStats();
		stats.addStat(MineModStats.STAT_POWER, stats.get(MineModStats.STAT_STRENGTH) * POWER_PER_STRENGTH);
	}

	@Override
	public void onAttack(EntityClassInstance entityClassInstance, EntityLivingBase attacked, DamageSource damageSource,
			float amount) {

		super.onAttack(entityClassInstance, attacked, damageSource, amount);
		Stats stats = entityClassInstance.getStats();
		float power = stats.get(MineModStats.STAT_POWER, 0.0f);
		float damages = power * DAMAGES_PER_POWER_POINT;

		if (damages > 0) {
			attacked.attackEntityFrom(DamageSource.causeMobDamage(null), damages);
		}
	}

	@Override
	public int getRGBColor() {
		return (0x88FF0000);
	}

	@Override
	public String[] getAdvices() {
		return (new String[] { ChatColor.GREEN + "+ " + MineModStats.STAT_STAMINA.getName() + ChatColor.RESET,
				ChatColor.GREEN + "+ " + MineModStats.STAT_STRENGTH.getName() + ChatColor.RESET,
				ChatColor.GREEN + "+ " + MineModStats.STAT_POWER.getName() + ChatColor.RESET,
				ChatColor.RED + "- " + MineModStats.STAT_CLARITY.getName() + ChatColor.RESET, });
	}
}