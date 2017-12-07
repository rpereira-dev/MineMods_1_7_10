package com.rpereira.minemod.client.input;

import org.lwjgl.input.Keyboard;

import com.rpereira.mineclass.MineClass;
import com.rpereira.mineclass.common.classes.EntityClassInstance;
import com.rpereira.minemod.client.gui.GuiClassInformation;
import com.rpereira.minemod.client.gui.GuiSelectClass;
import com.rpereira.minemod.client.gui.GuiStats;
import com.rpereira.minemod.common.IMineModProxy;
import com.rpereira.minespells.client.MineSpellProxyClient;
import com.rpereira.minespells.common.Spell;
import com.rpereira.mineutils.Logger;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;

public class MineModInput implements IMineModProxy {

	private static KeyBinding KEY_CLASS_SELECTION;
	private static KeyBinding KEY_STATS_GUI;
	private static final KeyBinding[] KEY_SPELLS = new KeyBinding[10];

	@Override
	public void init() {
		KEY_CLASS_SELECTION = new KeyBinding("key.minemod.class", Keyboard.KEY_C, "key.categories.minemod");
		KEY_STATS_GUI = new KeyBinding("key.minemod.stats", Keyboard.KEY_P, "key.categories.minemod");
		ClientRegistry.registerKeyBinding(KEY_CLASS_SELECTION);
		for (int i = 0; i < KEY_SPELLS.length; i++) {
			KEY_SPELLS[i] = new KeyBinding("key.minemod.spellshortcut", Keyboard.KEY_1 + i, "key.categories.minemod");
			ClientRegistry.registerKeyBinding(KEY_SPELLS[i]);
		}
		FMLCommonHandler.instance().bus().register(new KeyInputHandler());
	}

	public class KeyInputHandler {

		public KeyInputHandler() {

		}

		@SubscribeEvent
		@SideOnly(Side.CLIENT)
		public void onKeyInput(InputEvent.KeyInputEvent event) {
			if (KEY_CLASS_SELECTION.isPressed()) {
				Minecraft mc = Minecraft.getMinecraft();
				if (!(mc.currentScreen instanceof GuiSelectClass)
						&& !(mc.currentScreen instanceof GuiClassInformation)) {
					mc.displayGuiScreen(new GuiSelectClass());
				}
			} else if (KEY_STATS_GUI.isPressed()) {
				Minecraft mc = Minecraft.getMinecraft();
				if (!(mc.currentScreen instanceof GuiStats)) {
					EntityClassInstance entityClassInstance = MineClass.proxyClient().getEntityClassInstance();
					mc.displayGuiScreen(new GuiStats(entityClassInstance));
				}
			} else {
				for (int i = 0; i < KEY_SPELLS.length; i++) {

					KeyBinding keyBinding = KEY_SPELLS[i];
					if (keyBinding.isPressed()) {
						EntityClassInstance entityClassInstance = MineClass.proxyClient().getEntityClassInstance();
						Spell spell = entityClassInstance.getEntityClass().getSpell(i);
						if (spell != null) {
							Entity caster = entityClassInstance.getEntity();
							if (caster == null) {
								break;
							}
							Entity target = spell.getTarget(caster);

							if (target == null && spell.requireNonNullTarget()) {
								break;
							}

							if (entityClassInstance.canLaunch(spell)) {
								MineSpellProxyClient.launchSpell(caster, target, spell);
								entityClassInstance.launchSpell(target, spell);
							}
						} else {
							Logger.get().log(Logger.Level.ERROR, "Tried to launch null spell", entityClassInstance);
						}
						break;
					}
				}
			}
		}
	}

	@Override
	public void preInit() {
		// TODO Auto-generated method stub

	}

}
