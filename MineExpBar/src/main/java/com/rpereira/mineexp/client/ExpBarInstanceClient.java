package com.rpereira.mineexp.client;

import com.rpereira.mineexp.common.ExpBar;
import com.rpereira.mineexp.common.ExpBarInstance;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ExpBarInstanceClient extends ExpBarInstance {

	/** exp to add for the bar animation */
	private int expToAdd = 0;

	public ExpBarInstanceClient(ExpBar expBar, int uuid, Object... attributes) {
		super(expBar, uuid, attributes);
	}

	/** delayed exp */
	@SideOnly(Side.CLIENT)
	public void addExpToAdd(int exp) {
		this.expToAdd += exp;
	}

	@SideOnly(Side.CLIENT)
	public int getExpToAdd() {
		return (this.expToAdd);
	}

	@SideOnly(Side.CLIENT)
	public void setExpToAdd(int expToAdd) {
		this.expToAdd = expToAdd;
	}

	public void update() {
		super.update();
		if (this.expToAdd != 0) {
			int absexp = Math.abs(this.expToAdd);
			int addInThisUpdate = Math.min(super.getExpBar().getMaxToAddPerUpdate(this), this.expToAdd);
			this.expToAdd = this.expToAdd - Integer.signum(absexp) * addInThisUpdate;
			this.addExp(addInThisUpdate);
		}
	}
}
