package com.rpereira.minemod.common.spells.priest;

import java.util.Random;

import com.rpereira.minemod.common.spells.SpellMineMod;
import com.rpereira.minespells.client.EntityFXSpell;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SpellRegeneration extends SpellMineMod {

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
		}
	}

	private void spawnParticleOnce(World world, Random rng, double x, double y, double z) {
		float vx = (rng.nextInt(2) == 0 ? rng.nextFloat() : -rng.nextFloat()) * 0.1f;
		float vy = rng.nextFloat() * 0.5f;
		float vz = (rng.nextInt(2) == 0 ? rng.nextFloat() : -rng.nextFloat()) * 0.1f;
		world.spawnParticle("fireworksSpark", x, y - 1.0f, z, vx, vy, vz);

		float scale = 2.0f + rng.nextFloat() * 0.5f;
		float r = 1.0f;
		float g = 0.2f;
		float b = 1.0f;
		EntityFX particle = new EntityFXSpell(world, x, y, z, vz, vy, vx, scale, r, g, b);
		super.spawnParticle(particle);
	}

	@Override
	public void processSpell(Entity caster, Entity target) {
		if (target instanceof EntityLivingBase) {
			int duration = (int) (this.getBaseDamages() + this.getMagicRatio() * this.getEntityMagic(caster));
			((EntityLivingBase) target).addPotionEffect(new PotionEffect(Potion.regeneration.id, duration, 1));
		}
	}

	@Override
	public float getBaseDamages() {
		return (60);
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
