package com.rpereira.minemod.common.spells.champion;

import com.rpereira.minespells.client.EntityFXSpell;
import com.rpereira.minespells.client.SpellClientUtils;
import com.rpereira.minespells.common.Spell;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class SpellCharge extends Spell {

	@Override
	public void playAnimation(Entity caster, Entity target) {

		if (caster == null) {
			return;
		}

		World world = Minecraft.getMinecraft().theWorld;
		for (int i = 0; i < 100; i++) {
			double velx = world.rand.nextFloat();
			double velz = world.rand.nextFloat();
			if (world.rand.nextInt(2) == 0) {
				velx = -velx;
			} else if (world.rand.nextInt(2) == 0) {
				velz = -velz;
			}
			EntityFX particle = new EntityFXSpell(world, caster.posX, caster.posY, caster.posZ, velx, 0.0F, velz, 2.5f,
					0, 1.0f, 5.0f);
			SpellClientUtils.spawnParticle(particle);
		}
	}

	@Override
	public void processSpell(Entity caster, Entity target) {
		if (caster == null) {
			return;
		}
		Vec3 vec = caster.getLookVec();
		caster.motionX = vec.xCoord * 3.5d;
		caster.motionY += 1.2d;
		caster.motionZ = vec.zCoord * 3.5d;
	}
}
