package com.rpereira.minemod.client.gui;

import com.rpereira.mineclass.common.classes.EntityClass;
import com.rpereira.mineclass.common.packet.PacketChangeClass;
import com.rpereira.mineclass.common.packet.Packets;
import com.rpereira.mineutils.ChatColor;
import com.rpereira.mineutils.GuiUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class GuiClassInformation extends GuiScreen {

	private final EntityClass entityClass;
	private final String[] description;

	public GuiClassInformation(EntityClass entityClass) {
		super();
		this.entityClass = entityClass;
		this.description = entityClass.getDescription();
	}

	@Override
	public void initGui() {
		this.buttonList.add(new GuiButton(42, this.width / 2 - 40, 34, 80, 20, I18n.format("button.selection")));
		this.buttonList.add(new GuiButton(43, 10, this.height - 26, 160, 20,
				ChatColor.RESET + I18n.format("button.back_selection")));
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int x, int y, float dunno) {

		int color = this.entityClass.getRGBColor();
		float darkfactor = 0.2f;
		int darker = 0xCC000000 | ((int) (((color >> 16) & 0xFF) * darkfactor)) << 16
				| ((int) (((color >> 8) & 0xFF) * darkfactor)) << 8 | ((int) (((color >> 0) & 0xFF) * darkfactor)) << 0;
		this.drawGradientRect(0, 0, this.width, this.height, color, darker);
		this.drawCenteredString(this.fontRendererObj,
				ChatColor.UNDERLINE + this.entityClass.getName() + ChatColor.RESET, this.width / 2, 14, 0xFFFFFFFF);

		GuiUtils.drawEntity(this.width / 2, this.height / 2, 28, 0, 0, this.mc.thePlayer);

		int lx = this.width / 2;
		int ly = this.height / 2;
		for (String line : this.description) {
			ly += 16;
			this.drawCenteredString(this.fontRendererObj, line, lx, ly, 0xFFFFFFFF);
		}

		int ax = this.width / 4 * 3 + 10;
		this.drawString(this.fontRendererObj, ChatColor.UNDERLINE + "Advices:", ax, 28, 0xFFFFFFFF);

		int ay = 46;
		String[] advices = this.entityClass.getAdvices();
		int i = 0;
		for (String advice : advices) {
			this.drawString(this.fontRendererObj, advice, ax, ay, 0xFFFFFFFF);
			ay += 14;
		}

		this.mc.renderEngine.bindTexture(this.entityClass.getResourceLocation());
		this.drawTexturedModalRect(20, 14, 0, 192, 64, 64);

		super.drawScreen(x, y, dunno);
	}

	/**
	 * Called from the main game loop to update the screen.
	 */
	@Override
	public void updateScreen() {
		super.updateScreen();
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	protected void actionPerformed(GuiButton b) {
		if (b.id == 42) {
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;

			PacketChangeClass packet = new PacketChangeClass(this.entityClass.getID());
			Packets.network.sendToServer(packet);

			player.addChatMessage(new ChatComponentText("You can press " + ChatColor.RED + "P" + ChatColor.RESET
					+ " on your keyboard to see more informations about " + this.entityClass.getName() + ChatColor.RESET
					+ " and it spells."));
			player.addChatMessage(new ChatComponentText("Don't forget to configure your controls!"));
			this.mc.displayGuiScreen(null);
		} else if (b.id == 43) {
			GuiSelectClass gui = new GuiSelectClass();
			this.mc.displayGuiScreen(gui);
		}
	}
}