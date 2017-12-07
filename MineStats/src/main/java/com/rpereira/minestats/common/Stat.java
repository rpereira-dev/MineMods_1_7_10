package com.rpereira.minestats.common;

import net.minecraft.client.resources.I18n;

public abstract class Stat {

	/** the unlocalized name of this stat */
	public abstract String getUnlocalizedName();

	public float getDefaultValue() {
		return (0);
	}

	public String getName() {
		String key = "stats." + this.getUnlocalizedName();
		return (I18n.format(key));
	}

	public String getDescription() {
		String key = "stats." + this.getUnlocalizedName() + ".desc";
		return (I18n.format(key));
	}

}
