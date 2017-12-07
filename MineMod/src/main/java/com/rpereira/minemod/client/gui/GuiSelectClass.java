package com.rpereira.minemod.client.gui;

import com.rpereira.mineclass.common.classes.EntityClass;
import com.rpereira.minemod.common.classes.MineModClasses;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiSelectClass extends GuiScreen {

	private GuiButtonSelectClass classesButtons[];

	class GuiButtonSelectClass extends GuiButton {

		EntityClass entityClass;

		public GuiButtonSelectClass(EntityClass entityClass, int x, int y, int w, int h, String text) {
			super(entityClass.getID() + 42, x, y, w, h, text);
			this.entityClass = entityClass;
		}
	}

	@Override
	public void initGui() {
		EntityClass[] entityClasses = new EntityClass[] { MineModClasses.CHAMPION, MineModClasses.PRIEST,
				MineModClasses.NECROMANCER, MineModClasses.ROGUE, MineModClasses.RANGER, MineModClasses.MAGE };
		this.classesButtons = new GuiButtonSelectClass[entityClasses.length];
		int x = this.width / 4 - 34;
		int y = this.height / 4 - 22;
		int w = 80;
		int h = 20;
		int i = 0;
		for (EntityClass entityClass : entityClasses) {
			this.classesButtons[i] = new GuiButtonSelectClass(entityClass, x, y, w, h, entityClass.getName());
			this.buttonList.add(this.classesButtons[i]);
			x += this.width / 4;
			++i;
			if (i % 3 == 0) {
				x = this.width / 4 - 34;
				y = this.height / 4 * 3 - 48;
			}
		}
	}

	/**
	 * Fired when a key is typed. This is the equivalent of
	 * KeyListener.keyTyped(KeyEvent e).
	 */
	protected void keyTyped(char c, int i) {
		super.keyTyped(c, i);
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int x, int y, float dunno) {
		int tx = this.width / 4 - 26;
		int ty = this.height / 4;

		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, I18n.format("class.choose"), this.width / 2, 14, 0xAAAAAAAA);

		super.drawScreen(x, y, dunno);

		int i = 0;
		for (GuiButtonSelectClass button : this.classesButtons) {
			this.mc.getTextureManager().bindTexture(button.entityClass.getResourceLocation());
			this.drawTexturedModalRect(tx, ty, 0, 192, 64, 64);

			tx += this.width / 4;
			++i;
			if (i % 3 == 0) {
				tx = this.width / 4 - 26;
				ty = this.height / 4 * 3 - 26;
			}
		}

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
		if (b instanceof GuiButtonSelectClass) {
			this.mc.displayGuiScreen(new GuiClassInformation(((GuiButtonSelectClass) b).entityClass));
		}
	}
}