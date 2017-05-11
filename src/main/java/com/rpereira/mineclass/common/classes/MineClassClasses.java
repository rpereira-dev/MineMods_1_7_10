package com.rpereira.mineclass.common.classes;

import com.rpereira.mineclass.MineClass;
import com.rpereira.mineclass.common.MineClassProxy;

public class MineClassClasses {

	public static final EntityClass CLASS_FARMER = new EntityClassFarmer();
	public static final EntityClass CLASS_CHAMPION = new EntityClassChampion();
	public static final EntityClass CLASS_ROGUE = new EntityClassRogue();
	public static final EntityClass CLASS_NECROMANCER = new EntityClassNecromancer();
	public static final EntityClass CLASS_RANGER = new EntityClassRanger();
	public static final EntityClass CLASS_PRIEST = new EntityClassPriest();
	public static final EntityClass CLASS_MAGE = new EntityClassMage();

	public static void preInit() {
		MineClassProxy proxy = MineClass.proxy();
		proxy.registerClass(CLASS_FARMER);
		proxy.registerClass(CLASS_CHAMPION);
		proxy.registerClass(CLASS_ROGUE);
		proxy.registerClass(CLASS_NECROMANCER);
		proxy.registerClass(CLASS_RANGER);
		proxy.registerClass(CLASS_PRIEST);
		proxy.registerClass(CLASS_MAGE);
	}

}
