package com.rpereira.minemod.common.classes;

import com.rpereira.mineclass.MineClass;
import com.rpereira.mineclass.common.MineClassProxy;
import com.rpereira.mineclass.common.classes.EntityClass;
import com.rpereira.minemod.common.IMineModProxy;

public class MineModClasses implements IMineModProxy {

	public static final EntityClass CLASS_CHAMPION = new EntityClassChampion();
	public static final EntityClass CLASS_ROGUE = new EntityClassRogue();
	public static final EntityClass CLASS_NECROMANCER = new EntityClassNecromancer();
	public static final EntityClass CLASS_RANGER = new EntityClassRanger();
	public static final EntityClass CLASS_PRIEST = new EntityClassPriest();
	public static final EntityClass CLASS_MAGE = new EntityClassMage();

	@Override
	public void preInit() {
	}

	@Override
	public void init() {
		MineClassProxy proxy = MineClass.proxy();
		proxy.registerClass(CLASS_CHAMPION);
		proxy.registerClass(CLASS_ROGUE);
		proxy.registerClass(CLASS_NECROMANCER);
		proxy.registerClass(CLASS_RANGER);
		proxy.registerClass(CLASS_PRIEST);
		proxy.registerClass(CLASS_MAGE);
	}
}
