package com.rpereira.minemod.common.spells.priest;

import java.util.Random;

import com.rpereira.minemod.common.spells.SpellMineMod;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class SpellHeal extends SpellMineMod {

	@Override
	public void playAnimation(Entity caster, Entity target) {
		World world = Minecraft.getMinecraft().theWorld;
		Random rng = world.rand;
		double x = target.posX;
		double y = target.posY;
		double z = target.posZ;
		for (int i = 0; i < 5; i++) {
			this.spawnParticleOnce(world, rng, x, y, z);
			this.spawnParticleOnce(world, rng, x, y, z);
			this.spawnParticleOnce(world, rng, x, y, z);
			this.spawnParticleOnce(world, rng, x, y, z);
			this.spawnParticleOnce(world, rng, x, y, z);
			this.spawnParticleOnce(world, rng, x, y, z);
			this.spawnParticleOnce(world, rng, x, y, z);
			this.spawnParticleOnce(world, rng, x, y, z);
			this.spawnParticleOnce(world, rng, x, y, z);
		}
	}

	private void spawnParticleOnce(World world, Random rng, double x, double y, double z) {
		float vx = (rng.nextInt(2) == 0 ? rng.nextFloat() : -rng.nextFloat()) * 0.1f;
		float vy = rng.nextFloat() * 0.5f;
		float vz = (rng.nextInt(2) == 0 ? rng.nextFloat() : -rng.nextFloat()) * 0.1f;
		world.spawnParticle("fireworksSpark", x, y - 1.0f, z, vx, vy, vz);
	}

	@Override
	public void processSpell(Entity caster, Entity target) {
		if (target instanceof EntityLivingBase) {
			float heal = this.getBaseDamages() + this.getMagicRatio() * this.getEntityMagic(caster);
			((EntityLivingBase) target).heal(heal);
		}
	}

	@Override
	public float getBaseDamages() {
		return (1.0F);
	}

	@Override
	public Entity getTarget(Entity caster) {
		Entity target = super.getLookingEntity((EntityLivingBase) caster, this.getRange());
		return (target != null ? target : caster);
	}

	@Override
	public String getUnlocalizedName() {
		return ("heal");
	}

	public int getCost() {
		return (140);
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
