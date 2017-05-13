package com.rpereira.minemod.common.exp;

import com.rpereira.mineexp.MineExp;
import com.rpereira.minemod.common.IMineModProxy;

public class MineModExp implements IMineModProxy {

	public static final LevelExpBar EXP_BAR_LEVEL = new LevelExpBar();

	@Override
	public void preInit() {
	}

	@Override
	public void init() {
		MineExp.registerExpBar(EXP_BAR_LEVEL);
	}
}
