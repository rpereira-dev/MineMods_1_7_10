package com.rpereira.minemod.common.spells.mage;

import com.rpereira.minemod.common.spells.SpellMineMod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SpellFreezeCube extends SpellMineMod {

	@Override
	public void playAnimation(Entity caster, Entity target) {
		World world = target.worldObj;
		double x = target.posX;
		double y = target.posY;
		double z = target.posZ;
		for (float i = 0; i < 8; i += 0.25f) {
			for (float j = 0; j < 8; j += 0.25f) {
				world.spawnParticle("snowballpoof", x + i - 4, y, z + j - 4, 0, 0, 0);
				world.spawnParticle("snowballpoof", x + i - 4, y + 8, z + j - 4, 0, 0, 0);

				world.spawnParticle("snowballpoof", x + i - 4, y + j, z - 4, 0, 0, 0);
				world.spawnParticle("snowballpoof", x + i - 4, y + j, z + 4, 0, 0, 0);

				world.spawnParticle("snowballpoof", x - 4, y + j, z - 4 + i, 0, 0, 0);
				world.spawnParticle("snowballpoof", x + 4, y + j, z - 4 + i, 0, 0, 0);
			}
		}
	}

	@Override
	public void processSpell(Entity caster, Entity target) {
		if (target instanceof EntityLivingBase) {
			super.dealDamages(caster, target, this.getBaseDamages());
			((EntityLivingBase) target).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120, 1));
		}
	}

	@Override
	public float getBaseDamages() {
		return (4.0F);
	}

	@Override
	public Entity getTarget(Entity caster) {
		Entity target = super.getLookingEntity((EntityLivingBase) caster, this.getRange());
		return (target);
	}

	@Override
	public String getUnlocalizedName() {
		return ("freezecube");
	}

	public int getCost() {
		return (200);
	}

	public int getRequiredLevel() {
		return (1);
	}

	@Override
	public float getRange() {
		return (32.0f);
	}

	@Override
	public boolean requireNonNullTarget() {
		return (true);
	}

}
