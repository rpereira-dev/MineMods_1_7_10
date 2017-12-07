package com.rpereira.mineclass.common.exp;

import com.rpereira.mineexp.MineExp;

public class MineClassExp {

	public static final LevelExpBar EXP_BAR_LEVEL = new LevelExpBar();

	public static void preInit() {
	}

	public static void init() {
		MineExp.registerExpBar(EXP_BAR_LEVEL);
	}
}
