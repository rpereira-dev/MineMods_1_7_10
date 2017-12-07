package com.rpereira.minemod.common.spells.necromancer;

import java.util.List;
import java.util.Random;

import com.rpereira.minemod.common.entity.EntitySummonable;
import com.rpereira.minemod.common.entity.EntitySummonableZombie;
import com.rpereira.minemod.common.spells.SpellSummon;

import net.minecraft.entity.Entity;

public class SpellSummonZombie extends SpellSummon {

	@Override
	public float getR() {
		return (0.5f);
	}

	@Override
	public float getG() {
		return (1.0f);
	}

	@Override
	public float getB() {
		return (0.2f);
	}

	@Override
	public float getScale() {
		return (3.0f);
	}

	@Override
	public float getMagicRatio() {
		return (1 / 256.0f);
	}

	public static final int ZOMBIE_MAX = 3;

	@Override
	public EntitySummonable[] entityToSummon(Entity caster) {
		int count = 0;
		List<?> entities = this.getEntitiesAround(caster, 64.0d, 64.0d, 64.0d);
		for (Object obj : entities) {
			if (obj instanceof EntitySummonableZombie) {
				EntitySummonableZombie entity = (EntitySummonableZombie) obj;
				if (entity.getOwner() == caster) {
					if (++count >= ZOMBIE_MAX) {
						return (null);
					}
				}
			}
		}

		Random rng = caster.worldObj.rand;
		int n = ZOMBIE_MAX - count;
		EntitySummonableZombie[] zombies = new EntitySummonableZombie[n];
		for (int i = 0; i < n; i++) {
			EntitySummonableZombie zombie = new EntitySummonableZombie(caster.worldObj);
			float dx = (rng.nextInt(2) == 0 ? -rng.nextFloat() : rng.nextFloat()) * 4.0f;
			float dy = zombie.getEyeHeight() + 0.5f;
			float dz = (rng.nextInt(2) == 0 ? -rng.nextFloat() : rng.nextFloat()) * 4.0f;
			float x = (float) (caster.posX + dx);
			float y = (float) (caster.posY + dy);
			float z = (float) (caster.posZ + dz);
			zombie.setPosition(x, y, z);
			zombies[i] = zombie;
		}
		return (zombies);
	}

	@Override
	public String getUnlocalizedName() {
		return ("zombie_summon");
	}

	@Override
	public float getEntityDefaultHealth() {
		return (10.0f);
	}

	@Override
	public float getEntityHealthRatio() {
		return (1 / 256.0f);
	}

	@Override
	public float getEntityDefaultDamages() {
		return (2.0f);
	}

	@Override
	public float getEntityDamageRatio() {
		return (1 / 512.0f);
	}

}
