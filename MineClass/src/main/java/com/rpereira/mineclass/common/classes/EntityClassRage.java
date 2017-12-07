package com.rpereira.mineclass.common.classes;

import java.util.Random;

import com.rpereira.mineclass.common.stats.MineClassStats;
import com.rpereira.mineutils.ChatColor;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public abstract class EntityClassRage extends EntityClass {

	/** number of rage point to decrease on each decrease */
	private static final float AMOUNT_TO_DECREASE_PER_SEC = 1;

	/** the number of ms when the rage begin decreasing after a fight */
	private static final float SEC_TO_DECREASE_AFTER_FIGHT = 8.0F;

	public EntityClassRage(String resLocation, String unlocalizedName, ChatColor chatColor) {
		super(resLocation, MineClassStats.STAT_RAGE, unlocalizedName, chatColor);
	}

	@Override
	public void onResourcesUpdated(EntityClassInstance entityClassInstance) {
		super.onResourcesUpdated(entityClassInstance);

		long curr = System.currentTimeMillis();
		long lastAttack = entityClassInstance.getAttribute("lastAttack", 0L);
		float dtcombat = (curr - lastAttack) / 1000.0f;
		float rage = entityClassInstance.getResource();
		if (rage > 0) {
			long lastDecrement = entityClassInstance.getAttribute("lastDecrement", 0L);
			float dt = (curr - lastDecrement) / 1000.0f;
			float rageToDecrement = (float) (AMOUNT_TO_DECREASE_PER_SEC * dt * (1 + Math.atan(dtcombat * 0.2f)));

			entityClassInstance.addResource(-rageToDecrement);
			entityClassInstance.setAttribute("lastDecrement", curr);
		}
	}

	@Override
	public void onAttack(EntityClassInstance entityClassInstance, EntityLivingBase attacked, DamageSource damageSource,
			float amount) {
		super.onAttack(entityClassInstance, attacked, damageSource, amount);
		if (attacked.isEntityAlive()) {
			this.generateRage(entityClassInstance, amount);
		}
	}

	private void generateRage(EntityClassInstance entityClassInstance, float amount) {
		Random rng = entityClassInstance.getEntity().worldObj.rand;

		int rage;
		if (amount < 6) {
			rage = 3;
		} else if (amount > 50) {
			rage = 25;
		} else {
			rage = (int) (amount * 0.5f);
		}
		entityClassInstance.addResource(rng.nextInt(rage) + rage);
	}
}
