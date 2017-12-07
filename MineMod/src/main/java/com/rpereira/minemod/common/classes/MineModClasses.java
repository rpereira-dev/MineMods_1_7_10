package com.rpereira.minemod.common.classes;

import com.rpereira.mineclass.MineClass;
import com.rpereira.mineclass.common.MineClassProxy;
import com.rpereira.mineclass.common.classes.EntityClass;
import com.rpereira.minemod.common.IMineModProxy;

public class MineModClasses implements IMineModProxy {

	public static final EntityClass CHAMPION = new EntityClassChampion();
	public static final EntityClass ROGUE = new EntityClassRogue();
	public static final EntityClass NECROMANCER = new EntityClassNecromancer();
	public static final EntityClass RANGER = new EntityClassRanger();
	public static final EntityClass PRIEST = new EntityClassPriest();
	public static final EntityClass MAGE = new EntityClassMage();

	public static final EntityClass HOLY_BLAZE = new EntityClassHolyBlaze();

	@Override
	public void preInit() {
	}

	@Override
	public void init() {
		MineClassProxy proxy = MineClass.proxy();
		proxy.registerClass(CHAMPION);
		proxy.registerClass(ROGUE);
		proxy.registerClass(NECROMANCER);
		proxy.registerClass(RANGER);
		proxy.registerClass(PRIEST);
		proxy.registerClass(MAGE);

		proxy.registerClass(HOLY_BLAZE);
	}
}
