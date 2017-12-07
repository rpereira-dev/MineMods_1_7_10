package com.rpereira.minemod.client.gui;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.rpereira.mineclass.common.classes.EntityClass;
import com.rpereira.mineclass.common.classes.EntityClassInstance;
import com.rpereira.minespells.common.Spell;
import com.rpereira.minestats.common.Stat;
import com.rpereira.mineutils.ChatColor;
import com.rpereira.mineutils.GuiUtils;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.util.ResourceLocation;

public class GuiStats extends GuiScreen {

	public static final ResourceLocation GUI = new ResourceLocation("minemod:textures/gui/stats.png");

	private StatLine statLines[];
	private SpellSlot spellSlots[];
	private EntityClassInstance entityClassInstance;

	public GuiStats(EntityClassInstance entityClassInstance) {
		super();
		this.entityClassInstance = entityClassInstance;
	}

	@Override
	public void initGui() {
		super.initGui();

		Set<Entry<Stat, Float>> defaultStats = this.entityClassInstance.getStats().getValues();
		this.statLines = new StatLine[defaultStats.size()];
		int i = 0;
		int h = 16;
		for (Entry<Stat, Float> entry : defaultStats) {
			Stat stat = entry.getKey();
			String name = stat.getName();
			float value = entry.getValue();
			float defvalue = this.entityClassInstance.getDefaultStats().get(stat, 0);
			String desc = stat.getDescription();
			int x = this.width / 2 - 92;
			int y = 116 + h * i;
			this.statLines[i] = new StatLine(name, value, defvalue, desc, x, y);
			++i;
		}

		EntityClass entityClass = this.entityClassInstance.getEntityClass();
		this.spellSlots = new SpellSlot[entityClass.getSpells().size()];

		for (i = 0; i < this.spellSlots.length; i++) {
			Spell spell = entityClass.getSpell(i);
			this.spellSlots[i] = new SpellSlot(spell, this.width / 2 + 86, 47 + 22 * i);
		}
	}

	/** Draws the screen and all the components in it. */
	@Override
	public void drawScreen(int mouseX, int mouseY, float partial) {

		this.drawDefaultBackground();
		this.drawGuiContainerBackgroundLayer();

		this.drawStats();
		this.drawSpells();

		this.checkStatsHover(mouseX, mouseY);
		this.checkSpellHover(mouseX, mouseY);

		GuiUtils.drawEntity(this.width / 4 - 64, 206, 64, -(mouseX - this.width / 4 + 64), -(mouseY - this.height / 2),
				this.mc.thePlayer);
		this.drawTamed(mouseX, mouseY);

		super.drawScreen(mouseX, mouseY, partial);
	}

	private void drawTamed(int mouseX, int mouseY) {
		List<?> lst = Spell.getEntitiesAround(this.mc.thePlayer, 64, 64, 64);
		int y0 = 24;
		int offy = 0;
		int scale = 32;
		int i = 0;
		for (Object obj : lst) {
			if (obj instanceof EntityTameable) {
				EntityTameable tamed = (EntityTameable) obj;
				if (tamed.getOwner() == this.mc.thePlayer) {
					offy += 20;
					String name = tamed.getCommandSenderName() + " : " + tamed.getHealth() + "/" + tamed.getMaxHealth();
					this.fontRendererObj.drawString(name, this.width / 2 + 140, y0 + offy, Integer.MAX_VALUE);
					offy += scale + 16;
					GuiUtils.drawEntity(this.width / 2 + 184, y0 + offy, scale, this.width / 2 - mouseX + 152 + scale,
							y0 + offy - mouseY - scale, tamed);
					++i;
					if (i >= 3) {
						break;
					}
				}
			}
		}
	}

	protected void checkStatsHover(int x, int y) {
		for (int i = 0; i < this.statLines.length; i++) {
			boolean bool = x > this.statLines[i].x && x < this.statLines[i].x + this.width / 4
					&& y > this.statLines[i].y && y < this.statLines[i].y + 14;

			if (bool) {
				int x1 = this.fontRendererObj.getStringWidth(this.statLines[i].desc) / 2 + 4;
				this.drawRect(x - x1, y - 20, x + x1, y + 5, Integer.MIN_VALUE);
				this.drawCenteredString(this.fontRendererObj, this.statLines[i].desc, x, y - 12, Integer.MAX_VALUE);
			}
		}
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return (false);
	}

	protected void drawGuiContainerBackgroundLayer() {
		this.mc.getTextureManager().bindTexture(GUI);
		this.drawTexturedModalRect(this.width / 2 - 128, 0, 0, 0, 256, 256);

		String title = this.entityClassInstance.getEntityClass().getChatColor()
				+ this.entityClassInstance.getEntityClass().getName() + ChatColor.RESET + " "
				+ this.entityClassInstance.getEntity().getCommandSenderName();

		FontRenderer font = this.fontRendererObj;
		font.drawStringWithShadow(title, this.width / 2 - 66, 16, Integer.MAX_VALUE);

		String level = ChatColor.YELLOW + "" + ChatColor.UNDERLINE + "Level: " + this.entityClassInstance.getLevel()
				+ ChatColor.RESET;
		font.drawStringWithShadow(level, this.width / 2 - 44, 50, Integer.MAX_VALUE);
	}

	protected void drawStats() {
		FontRenderer font = this.fontRendererObj;
		String str = ChatColor.YELLOW + "" + ChatColor.UNDERLINE + "Stats:" + ChatColor.RESET;
		font.drawStringWithShadow(str, this.width / 2 - 96, 96, Integer.MAX_VALUE);

		for (StatLine line : this.statLines) {
			font.drawStringWithShadow(line.text, line.x, line.y, Integer.MAX_VALUE);
		}
	}

	private void checkSpellHover(int x, int y) {
		for (int i = 0; i < this.spellSlots.length; i++) {

			if (x >= this.spellSlots[i].x && x <= this.spellSlots[i].x + 16 && y >= this.spellSlots[i].y
					&& y <= this.spellSlots[i].y + 16) {

				Spell spell = this.spellSlots[i].spell;
				String[] description = spell.getDescription(this.entityClassInstance.getEntity());
				int x1 = this.fontRendererObj.getStringWidth(description[0]) / 2 + 10;
				int y1 = 10 * description.length + 6;
				drawRect(0, 0, this.width, this.height, Integer.MIN_VALUE);
				drawRect(x - x1, y - y1 - 15, x + x1, y - 2, Integer.MAX_VALUE);

				this.drawCenteredString(this.fontRendererObj,
						ChatColor.AQUA + "" + ChatColor.UNDERLINE + spell.getName() + ChatColor.RESET, x, y - y1 - 10,
						Integer.MAX_VALUE);
				for (int j = 0; j < description.length; j++) {
					this.drawCenteredString(this.fontRendererObj, description[j], x, y - y1 + 2 + j * 10,
							Integer.MAX_VALUE);
				}

				String str = "";
				// str = ChatColor.YELLOW + "Cost " + spell.getCost();
				this.drawCenteredString(this.fontRendererObj, str, x, y - y1 + 2 + y1 - 10, Integer.MAX_VALUE);

				// str = (this.pm.getLevel() >= spell.getRequiredLevel()) ? "" +
				// ChatColor.GREEN : "" + ChatColor.RED;
				// str += "Level required: " + spell.getRequiredLevel();
				this.drawCenteredString(this.fontRendererObj, str, x, y - y1 + 2 + y1, Integer.MAX_VALUE);
			}
		}
	}

	public final void drawSpells() {
		int x = 0;
		for (SpellSlot slot : this.spellSlots) {
			this.mc.getTextureManager().bindTexture(this.entityClassInstance.getEntityClass().getResourceLocation());
			this.drawTexturedModalRect(slot.x, slot.y, x, 0, 16, 16);
			x += 16;
		}
	}
}

class StatLine {

	String text;
	String desc;
	int x;
	int y;

	public StatLine(String name, float value, float defvalue, String desc, int x, int y) {
		String strvalue = Float.toString(value);
		if (strvalue.length() > 5) {
			strvalue = strvalue.subSequence(0, 5).toString();
		}

		float diff = value - defvalue;
		String strdiff = Float.toString(diff);
		if (strdiff.length() > 5) {
			strdiff = strvalue.subSequence(0, 5).toString();
		}
		this.text = name + " : " + strvalue + ((diff > 0) ? (ChatColor.GREEN.toString() + " (+ " + strdiff + ")")
				: (value < 0) ? (ChatColor.RED.toString() + " (- " + strdiff + ")") : (ChatColor.WHITE.toString()))
				+ ChatColor.RESET;
		this.x = x;
		this.y = y;
		this.desc = desc;
	}
}

class SpellSlot {

	Spell spell;
	int x;
	int y;

	public SpellSlot(Spell spell, int x, int y) {
		this.spell = spell;
		this.x = x;
		this.y = y;
	}
}