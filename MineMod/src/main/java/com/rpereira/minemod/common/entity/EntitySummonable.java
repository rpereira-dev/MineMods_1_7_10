package com.rpereira.minemod.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.world.World;

public abstract class EntitySummonable extends EntityTameable {

	private EntityLivingBase owner;

	public EntitySummonable(World world) {
		super(world);

		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIFollowOwner(this, 1.0D, 6.0F, 4.0F));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(3, new EntityAILookIdle(this));
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		this.owner = super.getOwner();
		if (this.owner == null) {
			this.setDead();
		}
	}

	@Override
	public EntityLivingBase getOwner() {
		return (this.owner);
	}

	public void onSummoned(Entity summoner) {
		if (summoner != null) {
			this.setTamed(true);
			this.setPathToEntity((PathEntity) null);
			this.setAttackTarget((EntityLivingBase) null);
			this.func_152115_b(summoner.getUniqueID().toString());
			this.worldObj.setEntityState(this, (byte) 7);
		}
	}

	@Override
	public EntityAgeable createChild(EntityAgeable parent) {
		return (null);
	}
}
