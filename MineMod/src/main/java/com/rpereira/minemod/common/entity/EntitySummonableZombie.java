package com.rpereira.minemod.common.entity;

import com.rpereira.minemod.client.render.entity.RenderSummonedZombie;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;

public class EntitySummonableZombie extends EntitySummonable {

	private final int type;

	public EntitySummonableZombie(World world) {
		super(world);
		this.type = world.rand.nextInt(RenderSummonedZombie.TEXTURES.length);

		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));

		this.setSize(0.6F, 1.8F);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
		return (null);
	}

	public int getType() {
		return (this.type);
	}

	@Override
	public boolean isAIEnabled() {
		return (true);
	}

	@Override
	protected String getLivingSound() {
		return "mob.zombie.say";
	}

	@Override
	protected String getHurtSound() {
		return "mob.zombie.hurt";
	}

	@Override
	protected String getDeathSound() {
		return "mob.zombie.death";
	}

	@Override
	protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
		this.playSound("mob.zombie.step", 0.15F, 1.0F);
	}

}
