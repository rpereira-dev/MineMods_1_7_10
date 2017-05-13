package com.rpereira.minemod.common.exp;

import com.rpereira.mineexp.client.ExpBarInstanceClient;
import com.rpereira.mineexp.common.ExpBar;
import com.rpereira.mineexp.common.ExpBarInstance;

public class LevelExpBar extends ExpBar {

	private static final double damping = 0.4D;
	private static final int scale = 100;

	@Override
	public int getMaximumExpForLevel(int level) {
		int x = Math.abs(level);
		return (int) (Math.log(x + 1) * Math.pow(x, x / 400) * 1000 + 600);
	}

	@Override
	public void saveExpBarInstance(ExpBarInstance expBarInstance) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadExpBarInstance(ExpBarInstance expBarInstance) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(ExpBarInstance expBarInstance, int x, int y, int width, int height, Object... attributes) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getMaxToAddPerUpdate(ExpBarInstanceClient expBarInstanceClient) {
		return (this.getMaximumExpForLevel(expBarInstanceClient.getLevel()) / 100);
	}

}
