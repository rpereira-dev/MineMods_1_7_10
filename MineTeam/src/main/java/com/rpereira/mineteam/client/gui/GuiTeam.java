package com.rpereira.mineteam.client.gui;

import com.rpereira.mineteam.common.packets.Packets;
import com.rpereira.mineteam.common.packets.server.PacketTeamInvitePlayer;
import com.rpereira.mineteam.common.packets.server.PacketTeamLeave;
import com.rpereira.mineteam.common.packets.server.PacketTeamRename;
import com.rpereira.mineutils.ChatColor;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;

public class GuiTeam extends GuiScreen {

	private GuiTextField playerNameTextfield;
	private GuiTextField prefixTextfield;
	private int index;
	private GuiButton color_button;
	private GuiButton prefixButton;
	private GuiButton inviteButton;

	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		this.index = ChatColor.YELLOW.ordinal();

		this.prefixTextfield = new GuiTextField(this.fontRendererObj, this.width / 2 - 120, 50, 150, 20);
		this.prefixTextfield.setMaxStringLength(12);
		this.prefixTextfield.setFocused(true);

		EntityPlayer player = this.mc.thePlayer;
		ScorePlayerTeam team = player.getWorldScoreboard().getPlayersTeam(player.getCommandSenderName());
		String str = ChatColor.stripColor(team.getColorPrefix());
		try {
			str = str.substring(1, str.length() - 2);
			this.prefixTextfield.setText(str);
		} catch (Exception e) {}

		this.playerNameTextfield = new GuiTextField(this.fontRendererObj, this.width / 2 - 120, 80, 150, 20);
		this.playerNameTextfield.setMaxStringLength(32);

		this.prefixButton = new GuiButton(41, this.width / 2 + 40, 50, 70, 20, "Prefix");
		this.buttonList.add(this.prefixButton);
		this.color_button = new GuiButton(40, this.width / 2 + 120, 50, 20, 20,
				ChatColor.getByID(this.index) + "o" + ChatColor.RESET);
		this.buttonList.add(this.color_button);

		this.inviteButton = new GuiButton(42, this.width / 2 + 40, 80, 100, 20, ChatColor.RESET + "Invite player");
		this.buttonList.add(this.inviteButton);
		this.buttonList.add(new GuiButton(43, this.width / 4, this.height - 70, this.width / 2, 20,
				ChatColor.RESET + "Leave group"));
		this.buttonList.add(
				new GuiButton(44, this.width / 4, this.height - 40, this.width / 2, 20, ChatColor.RESET + "Close"));

		this.checkButtonEnabled();
	}

	/**
	 * Fired when a key is typed. This is the equivalent of
	 * KeyListener.keyTyped(KeyEvent e).
	 */
	@Override
	protected void keyTyped(char c, int i) {
		super.keyTyped(c, i);
		if (this.playerNameTextfield.isFocused()) {
			this.playerNameTextfield.textboxKeyTyped(c, i);
		} else {
			this.prefixTextfield.textboxKeyTyped(c, i);
		}
		this.checkButtonEnabled();
	}

	private void checkButtonEnabled() {
		this.inviteButton.enabled = this.playerNameTextfield.getText().length() > 0;
//		this.prefixButton.enabled = this.prefixTextfield.getText().length() > 0;
	}

	/**
	 * Called when the mouse is clicked.
	 */
	@Override
	protected void mouseClicked(int x, int y, int p_73864_3_) {
		super.mouseClicked(x, y, p_73864_3_);
		this.playerNameTextfield.mouseClicked(x, y, p_73864_3_);
		this.prefixTextfield.mouseClicked(x, y, p_73864_3_);
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int x, int y, float dunno) {
		this.drawDefaultBackground();
		this.playerNameTextfield.drawTextBox();
		this.prefixTextfield.drawTextBox();
		this.drawCenteredString(this.fontRendererObj, ChatColor.UNDERLINE + "Configuring group...", this.width / 2, 14,
				Integer.MAX_VALUE);
		super.drawScreen(x, y, dunno);
	}

	/**
	 * Called from the main game loop to update the screen.
	 */
	@Override
	public void updateScreen() {
		this.playerNameTextfield.updateCursorCounter();
		this.prefixTextfield.updateCursorCounter();
		this.color_button.displayString = ChatColor.getByID(this.index) + "o" + ChatColor.RESET;
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	protected void actionPerformed(GuiButton b) {
		if (b.id == 40) { // color prefix
			this.index = (this.index + 1) % ChatColor.values().length;
		} else if (b.id == 41) { // prefix rename
			IMessage packet = new PacketTeamRename(this.prefixTextfield.getText(), this.index);
			Packets.network.sendToServer(packet);
			this.mc.displayGuiScreen((GuiScreen) null);
			this.mc.setIngameFocus();
		} else if (b.id == 42) { // invite
			IMessage packet = new PacketTeamInvitePlayer(this.playerNameTextfield.getText());
			Packets.network.sendToServer(packet);
			this.mc.displayGuiScreen((GuiScreen) null);
			this.mc.setIngameFocus();
		} else if (b.id == 43) { // leave group
			IMessage packet = new PacketTeamLeave();
			Packets.network.sendToServer(packet);
			this.mc.displayGuiScreen((GuiScreen) null);
			this.mc.setIngameFocus();
		} else {
			this.mc.displayGuiScreen((GuiScreen) null);
			this.mc.setIngameFocus();
		}
	}
}