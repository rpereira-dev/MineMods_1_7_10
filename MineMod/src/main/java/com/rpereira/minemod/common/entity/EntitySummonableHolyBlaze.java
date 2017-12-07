package com.rpereira.minemod.common.entity;

import com.rpereira.mineclass.MineClass;
import com.rpereira.mineclass.common.classes.EntityClassInstance;
import com.rpereira.minemod.common.classes.MineModClasses;
import com.rpereira.minespells.common.MineSpellProxyServer;
import com.rpereira.minespells.common.Spell;
import com.rpereira.mineteam.MineTeam;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntitySummonableHolyBlaze extends EntitySummonable {

	public EntitySummonableHolyBlaze(World world) {
		super(world);
		this.setSize(0.6F, 1.8F);
		// the AI to launch spells
		this.tasks.addTask(0, new EntityAIHealer(this));
	}

	@Override
	public void onSummoned(Entity summoner) {
		super.onSummoned(summoner);
		EntityClassInstance entityClassInstance = new EntityClassInstance(this);
		MineClass.proxy().spawnEntityClassInstance(entityClassInstance);
		entityClassInstance.setClass(MineModClasses.HOLY_BLAZE);
	}

	class EntityAIHealer extends EntityAIBase {

		EntitySummonableHolyBlaze entity;
		private EntityLivingBase toHeal;
		private boolean healed;

		public EntityAIHealer(EntitySummonableHolyBlaze entity) {
			super();
			this.entity = entity;
			this.setMutexBits(8);
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		public boolean shouldExecute() {
			EntityLivingBase owner = this.entity.getOwner();
			this.toHeal = owner;
			if (owner instanceof EntityPlayer) {
				EntityPlayer[] teamMembers = MineTeam.getTeamMembers((EntityPlayer) owner);
				float healthRatio = owner.getHealth() / owner.getMaxHealth();
				for (EntityPlayer member : teamMembers) {
					float ratio = member.getHealth() / member.getMaxHealth();
					if (ratio < healthRatio) {
						this.toHeal = member;
						healthRatio = ratio;
					}
				}
			}

			return (this.toHeal != null && this.toHeal.getHealth() < this.toHeal.getMaxHealth());
		}

		public boolean continueExecuting() {
			return (!this.healed);
		}

		public void startExecuting() {
			this.healed = false;
		}

		public void resetTask() {
			this.healed = false;
			this.toHeal = null;
		}

		public void updateTask() {

			this.healed = true;
			if (this.toHeal != null) {
				EntityClassInstance entityClassInstance = MineClass.proxy().getEntityClassInstance(this.entity);
				Spell spell = entityClassInstance.getRandomSpell();
				if (entityClassInstance != null && entityClassInstance.canLaunch(spell)) {
					entityClassInstance.launchSpell(this.entity, spell);
					MineSpellProxyServer.launchSpell(this.entity, this.toHeal, spell);
				}
			}
		}
	}
}
