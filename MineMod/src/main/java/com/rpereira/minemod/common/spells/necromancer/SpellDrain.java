package com.rpereira.minemod.common.spells.necromancer;

import com.rpereira.minemod.common.spells.SpellMineMod;
import com.rpereira.minespells.client.EntityFXSpell;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SpellDrain extends SpellMineMod {

	@Override
	public void playAnimation(Entity caster, Entity target) {
		World world = caster.worldObj;
		double x = target.posX;
		double y = target.posY + target.height * 0.5f;
		double z = target.posZ;
		float vx, vy, vz;
		for (int i = 0; i < 10; i++) {
			this.doEffectOnce(world, x, y, z);
			this.doEffectOnce(world, x, y, z);
			this.doEffectOnce(world, x, y, z);
			this.doEffectOnce(world, x, y, z);
			this.doEffectOnce(world, x, y, z);
			this.doEffectOnce(world, x, y, z);
			this.doEffectOnce(world, x, y, z);
			this.doEffectOnce(world, x, y, z);
			this.doEffectOnce(world, x, y, z);
		}
	}

	private void doEffectOnce(World world, double x, double y, double z) {
		float vx, vy, vz;

		vx = (float) (world.rand.nextFloat() - 0.5F);
		vy = (float) (world.rand.nextFloat() - 0.5F) * 0.5F;
		vz = (float) (world.rand.nextFloat() - 0.5F);

		world.spawnParticle("smoke", x, y, z, vx, vy, vz);
		EntityFX particle = new EntityFXSpell(world, x, y, z, vx, vy, vz, 1.0f, 5.0f, 0.0f, 0.0f);
		super.spawnParticle(particle);
		particle = new EntityFXSpell(world, x, y, z, vx, vy, vz, 1.0f, 0.0f, 5.0f, 0.0f);
		super.spawnParticle(particle);
	}

	@Override
	public void processSpell(Entity caster, Entity target) {
		if (target instanceof EntityLivingBase) {
			super.dealDamages(caster, target);
			if (caster instanceof EntityLivingBase) {
				((EntityLivingBase) caster).heal(1.0F + this.getEntityMagic(caster));
			}
		}
	}

	@Override
	public float getBaseDamages() {
		return (2.0F);
	}

	@Override
	public Entity getTarget(Entity caster) {
		Entity target = super.getLookingEntity((EntityLivingBase) caster, this.getRange());
		return (target);
	}

	@Override
	public String getUnlocalizedName() {
		return ("drain");
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
