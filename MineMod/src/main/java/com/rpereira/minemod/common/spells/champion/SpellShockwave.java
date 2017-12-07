package com.rpereira.minemod.common.spells.champion;

import java.util.Random;

import com.rpereira.minemod.common.spells.SpellMineMod;
import com.rpereira.minespells.client.EntityFXSpell;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class SpellShockwave extends SpellMineMod {

	@Override
	public void playAnimation(Entity caster, Entity target) {
		World world = caster.worldObj;
		Random rng = world.rand;
		double x = caster.posX;
		double y = caster.posY - 0.2f;
		double z = caster.posZ;
		double vx, vz;
		for (int i = 0; i < 25; i++) {
			this.spawnParticle(world, rng, x, y, z);
			this.spawnParticle(world, rng, x, y, z);
			this.spawnParticle(world, rng, x, y, z);
			this.spawnParticle(world, rng, x, y, z);
			this.spawnParticle(world, rng, x, y, z);
			this.spawnParticle(world, rng, x, y, z);
			this.spawnParticle(world, rng, x, y, z);
			this.spawnParticle(world, rng, x, y, z);
			this.spawnParticle(world, rng, x, y, z);
			this.spawnParticle(world, rng, x, y, z);
		}
	}

	private void spawnParticle(World world, Random rng, double x, double y, double z) {
		float vx = rng.nextFloat();
		float vz = rng.nextFloat();

		if (rng.nextInt(2) == 0) {
			vx = -vx;
		}

		if (rng.nextInt(2) == 0) {
			vz = -vz;
		}

		EntityFX particle = new EntityFXSpell(world, x, y, z, vx, -0.1f, vz, 2.0f, 0, 2.5f, 5.0f);
		super.spawnParticle(particle);
	}

	@Override
	public void processSpell(Entity caster, Entity target) {
		World world = caster.worldObj;
		world.createExplosion(caster, caster.posX + 3, caster.posY, caster.posZ + 3, 1.0f, false);
		world.createExplosion(caster, caster.posX + 3, caster.posY, caster.posZ - 3, 1.0f, false);
		world.createExplosion(caster, caster.posX - 3, caster.posY, caster.posZ + 3, 1.0f, false);
		world.createExplosion(caster, caster.posX - 3, caster.posY, caster.posZ - 3, 1.0f, false);
		world.createExplosion(caster, caster.posX + 3, caster.posY, caster.posZ, 1.0f, false);
		world.createExplosion(caster, caster.posX - 3, caster.posY, caster.posZ, 1.0f, false);
		world.createExplosion(caster, caster.posX, caster.posY, caster.posZ + 3, 1.0f, false);
		world.createExplosion(caster, caster.posX, caster.posY, caster.posZ - 3, 1.0f, false);
	}

	@Override
	public int getCost() {
		return (20);
	}

	@Override
	public int getRequiredLevel() {
		return (4);
	}

	@Override
	public String getUnlocalizedName() {
		return ("shockwave");
	}
}
