package com.rpereira.minespells.client;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public class EntityFXSpell extends EntityFX {

	public EntityFXSpell(World w, double x, double y, double z, double velX, double velY, double velZ, float scale,
			float r, float g, float b) {
		super(w, x, y, z, 0.0D, 0.0D, 0.0D);
		this.motionX *= 0.10000000149011612D;
		this.motionY *= 0.10000000149011612D;
		this.motionZ *= 0.10000000149011612D;
		this.motionX += velX;
		this.motionY += velY;
		this.motionZ += velZ;
		this.particleRed = r;
		this.particleGreen = g;
		this.particleBlue = b;
		this.particleScale *= 0.75F;
		this.particleScale *= scale;
		this.particleMaxAge = (int) (8.0D / (Math.random() * 0.8D + 0.2D));
		this.particleMaxAge = (int) ((float) this.particleMaxAge * scale);
		this.noClip = false;
	}
}
