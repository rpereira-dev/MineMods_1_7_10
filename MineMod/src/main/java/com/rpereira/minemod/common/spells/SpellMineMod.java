package com.rpereira.minemod.common.spells;

import java.util.ArrayList;

import com.rpereira.mineclass.MineClass;
import com.rpereira.mineclass.common.classes.EntityClassInstance;
import com.rpereira.minemod.common.stats.MineModStats;
import com.rpereira.minespells.common.Spell;
import com.rpereira.mineutils.ChatColor;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public abstract class SpellMineMod extends Spell {

	public final void dealDamages(Entity caster, Entity target) {
		this.dealDamages(caster, target, this.getDamages(caster, target));
	}

	public final float getDamages(Entity caster, Entity target) {
		// TODO : resistance, armor, mr
		return (this.getBaseDamages() + this.getMagicRatio() * this.getEntityMagic(caster) / 20.0f
				+ this.getPowerRatio() * this.getEntityPower(caster) / 20.0f);
	}

	protected final float getEntityPower(Entity caster) {
		return (this.getEntityPower(MineClass.proxy().getEntityClassInstance((EntityLivingBase) caster)));
	}

	protected final float getEntityPower(EntityClassInstance entityClassInstance) {
		if (entityClassInstance == null) {
			return (0);
		}
		return (entityClassInstance.getStats().get(MineModStats.STAT_POWER, 0.0F));
	}

	protected final float getEntityMagic(Entity caster) {
		return (this.getEntityPower(MineClass.proxy().getEntityClassInstance((EntityLivingBase) caster)));
	}

	protected final float getEntityMagic(EntityClassInstance entityClassInstance) {
		if (entityClassInstance == null) {
			return (0);
		}
		return (entityClassInstance.getStats().get(MineModStats.STAT_MAGIC, 0.0F)
				+ entityClassInstance.getStats().get(MineModStats.STAT_CLARITY, 0.0F) * 5.0F);
	}

	@Override
	public String[] getDescription(Entity caster) {
		String str = I18n.format("spell." + this.getUnlocalizedName() + "." + "desc");
		ArrayList<String> lst = new ArrayList<String>();
		String[] words = str.split("\\s+");
		int j = 1;
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < words.length; i++) {
			float powerRatio = this.getPowerRatio();
			float magicRatio = this.getMagicRatio();

			if (words[i].length() == 3 && words[i].charAt(0) == '@' && words[i].charAt(1) == 'd') {
				boolean isPhysical = words[i].charAt(2) == 'p';
				buffer.append(isPhysical ? ChatColor.GOLD : ChatColor.AQUA);
				buffer.append(this.getBaseDamages());
				buffer.append(ChatColor.RESET);

				if (powerRatio != 0.0f) {
					String strpower = String.valueOf(powerRatio);
					if (strpower.length() > 5) {
						strpower.substring(0, 5);
					}
					buffer.append(ChatColor.GOLD);
					buffer.append(ChatColor.ITALIC);
					buffer.append(" (+");
					buffer.append(powerRatio * this.getEntityPower(caster));
					buffer.append(")");
					buffer.append(ChatColor.RESET);
				}

				if (magicRatio != 0.0f) {
					String strmagic = String.valueOf(magicRatio);
					if (strmagic.length() > 5) {
						strmagic.substring(0, 5);
					}
					buffer.append(ChatColor.AQUA);
					buffer.append(ChatColor.ITALIC);
					buffer.append(" (+");
					buffer.append(magicRatio * this.getEntityMagic(caster));
					buffer.append(")");
					buffer.append(ChatColor.RESET);
				}

				buffer.append(ChatColor.RESET);
			} else {
				buffer.append(words[i]);
			}

			if (buffer.length() > 22) {
				lst.add(buffer.toString());
				buffer = new StringBuffer();
			} else {
				buffer.append(' ');
			}
		}

		if (!buffer.toString().isEmpty())

		{
			lst.add(buffer.toString());
		}

		return (lst.toArray(new String[lst.size()]));
	}
}
