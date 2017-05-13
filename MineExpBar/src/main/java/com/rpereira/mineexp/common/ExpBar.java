package com.rpereira.mineexp.common;

import com.rpereira.mineexp.client.ExpBarInstanceClient;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ExpBar {

	private static int nextID = 0;

	/** the bar id */
	private int id;

	public ExpBar() {
		this.id = nextID++;
	}

	/** get the maximum amount of exp needed for passing to the next level */
	public abstract int getMaximumExpForLevel(int level);

	/** the bar id */
	public int getID() {
		return (this.id);
	}

	public abstract void saveExpBarInstance(ExpBarInstance expBarInstance);

	public abstract void loadExpBarInstance(ExpBarInstance expBarInstance);

	public void onExpBarInstanceLevelUp(ExpBarInstance expBarInstance, int newlevel, int oldlevel) {
	}

	public void afterExpAddToInstance(ExpBarInstance expBarInstance) {
	}

	public void beforeExpAddToInstance(ExpBarInstance expBarInstance) {
	}

	public void onExpBarInstanceUpdate(ExpBarInstance expBarInstance) {

	}

	@SideOnly(Side.CLIENT)
	public abstract void render(ExpBarInstance expBarInstance, int x, int y, int width, int height,
			Object... attributes);

	@SideOnly(Side.CLIENT)
	public abstract int getMaxToAddPerUpdate(ExpBarInstanceClient expBarInstanceClient);
}
