package com.rpereira.minemod.common.spells.mage;

import com.rpereira.minemod.common.spells.SpellMineMod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class SpellFieldDestruction extends SpellMineMod {
	
	public static final int WIDTH = 4;

	@Override
	public void playAnimation(Entity caster, Entity target) {

	}

	private static int getTopBlock(World world, int x, int z, int y0) {
		int k;
		for (k = y0; !world.isAirBlock(x, k + 1, z); ++k) {
			;
		}
		return k;
	}

	@Override
	public void processSpell(Entity caster, Entity target) {
		World world = caster.worldObj;
		for (int i = -WIDTH; i <= WIDTH; i++) {
			for (int j = -WIDTH; j <= WIDTH; j++) {
				int x = (int) target.posX + i;
				int z = (int) target.posZ + j;
				int y = getTopBlock(world, x, z, (int) (target.posY - 2));
				Block block = world.getBlock(x, y, z);
				if (block == Blocks.grass || block instanceof BlockBush) {
					block = Blocks.dirt;
				}

				EntityFallingBlock entityFallingBlock = new EntityFallingBlock(world, x, y + 3 + world.rand.nextInt(3),
						z, block) {
					@Override
					public void onUpdate() {

						this.prevPosX = this.posX;
						this.prevPosY = this.posY;
						this.prevPosZ = this.posZ;
						++this.field_145812_b;
						this.motionY -= 0.03999999910593033D;
						this.moveEntity(this.motionX, this.motionY, this.motionZ);
						this.motionX *= 0.9800000190734863D;
						this.motionY *= 0.9800000190734863D;
						this.motionZ *= 0.9800000190734863D;

						if (this.onGround || this.field_145812_b > 200) {
							world.createExplosion(this, this.posX, this.posY, this.posZ, 0.6f, true);
							this.setDead();
							world.removeEntity(this);

						}
					}
				};
				world.spawnEntityInWorld(entityFallingBlock);
			}
		}
	}

	@Override
	public Entity getTarget(Entity caster) {
		Entity target = super.getLookingEntity((EntityLivingBase) caster, this.getRange());
		return (target);
	}

	@Override
	public String getUnlocalizedName() {
		return ("fielddestruction");
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
