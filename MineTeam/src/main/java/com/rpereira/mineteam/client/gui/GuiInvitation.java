package com.rpereira.mineteam.client.gui;

import com.rpereira.mineteam.common.packets.Packets;
import com.rpereira.mineteam.common.packets.client.PacketTeamInvitation;
import com.rpereira.mineteam.common.packets.server.PacketTeamAcceptInvitation;
import com.rpereira.mineteam.common.packets.server.PacketTeamRefuseInvitation;
import com.rpereira.mineutils.ChatColor;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiInvitation extends GuiScreen {

	private PacketTeamInvitation message;

	public GuiInvitation(PacketTeamInvitation message) {
		this.message = message;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		this.buttonList.add(
				new GuiButton(42, this.width / 4, this.height - 80, this.width / 2, 20, ChatColor.RESET + "Accept"));
		this.buttonList.add(
				new GuiButton(43, this.width / 4, this.height - 40, this.width / 2, 20, ChatColor.RESET + "Decline"));
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int x, int y, float useless) {
		this.drawCenteredString(this.fontRendererObj,
				ChatColor.UNDERLINE + this.message.inviter + " has invited you in his team: " + this.message.teamName,
				this.width / 2, 14, Integer.MAX_VALUE);
		super.drawScreen(x, y, useless);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	protected void actionPerformed(GuiButton b) {
		if (b.id == 42) { // accept

			IMessage packet = new PacketTeamAcceptInvitation(this.message.token);
			Packets.network.sendToServer(packet);

			this.mc.displayGuiScreen((GuiScreen) null);
			this.mc.setIngameFocus();
		} else if (b.id == 43) { // refuses

			IMessage packet = new PacketTeamRefuseInvitation(this.message.token);
			Packets.network.sendToServer(packet);

			this.mc.displayGuiScreen((GuiScreen) null);
			this.mc.setIngameFocus();
		}
	}
}