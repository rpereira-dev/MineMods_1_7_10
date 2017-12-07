package com.rpereira.minemod.common.spells.priest;

import java.util.List;
import java.util.Random;

import com.rpereira.minemod.common.entity.EntitySummonable;
import com.rpereira.minemod.common.entity.EntitySummonableHolyBlaze;
import com.rpereira.minemod.common.spells.SpellSummon;

import net.minecraft.entity.Entity;

public class SpellSummonHolyBlaze extends SpellSummon {

	@Override
	public float getR() {
		return (1.0f);
	}

	@Override
	public float getG() {
		return (1.0f);
	}

	@Override
	public float getB() {
		return (1.0f);
	}

	@Override
	public float getScale() {
		return (3.0f);
	}

	@Override
	public float getMagicRatio() {
		return (1 / 256.0f);
	}

	public static final int BLAZE_MAX = 3;

	@Override
	public EntitySummonable[] entityToSummon(Entity caster) {
		int count = 0;
		List<?> entities = this.getEntitiesAround(caster, 64.0d, 64.0d, 64.0d);
		for (Object obj : entities) {
			if (obj instanceof EntitySummonableHolyBlaze) {
				EntitySummonableHolyBlaze entity = (EntitySummonableHolyBlaze) obj;
				if (entity.getOwner() == caster) {
					if (++count >= BLAZE_MAX) {
						return (null);
					}
				}
			}
		}

		Random rng = caster.worldObj.rand;
		EntitySummonableHolyBlaze blaze = new EntitySummonableHolyBlaze(caster.worldObj);
		float dx = (rng.nextInt(2) == 0 ? -rng.nextFloat() : rng.nextFloat()) * 4.0f;
		float dy = blaze.getEyeHeight() + 0.5f;
		float dz = (rng.nextInt(2) == 0 ? -rng.nextFloat() : rng.nextFloat()) * 4.0f;
		float x = (float) (caster.posX + dx);
		float y = (float) (caster.posY + dy);
		float z = (float) (caster.posZ + dz);
		blaze.setPosition(x, y, z);
		return (new EntitySummonableHolyBlaze[] { blaze });
	}

	@Override
	public String getUnlocalizedName() {
		return ("holy_blaze_summon");
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
