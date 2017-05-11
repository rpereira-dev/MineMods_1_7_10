package com.rpereira.mineteam.client;

import org.lwjgl.input.Keyboard;

import com.rpereira.mineteam.client.gui.GuiCreateGroup;
import com.rpereira.mineteam.client.gui.GuiTeamOverlay;
import com.rpereira.mineteam.client.gui.GuiTeam;
import com.rpereira.mineteam.common.MineTeamProxy;
import com.rpereira.mineutils.Logger;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class MineTeamProxyClient extends MineTeamProxy {

	public static KeyBinding KEY_TEAM_GUI;

	@Override
	public void preInit() {

		super.preInit();

		Logger.get().log(Logger.Level.FINE, "Init MineTeamProxyClient");

		KEY_TEAM_GUI = new KeyBinding("key.team", Keyboard.KEY_G, "key.categories.team");
		ClientRegistry.registerKeyBinding(KEY_TEAM_GUI);
		FMLCommonHandler.instance().bus().register(new KeyInputHandler());
		MinecraftForge.EVENT_BUS.register(new GuiTeamOverlay(Minecraft.getMinecraft()));
	}

	public class KeyInputHandler {

		public KeyInputHandler() {

		}

		@SubscribeEvent
		@SideOnly(Side.CLIENT)
		public void onKeyInput(InputEvent.KeyInputEvent event) {
			Minecraft minecraft = Minecraft.getMinecraft();
			EntityPlayer player = minecraft.thePlayer;
			if (KEY_TEAM_GUI.isPressed()) {
				if (player.getTeam() != null) {
					minecraft.displayGuiScreen(new GuiTeam());
				} else {
					minecraft.displayGuiScreen(new GuiCreateGroup());
				}
			}
		}
	}
}
