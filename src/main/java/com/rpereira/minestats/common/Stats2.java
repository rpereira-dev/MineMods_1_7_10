package com.rpereira.minestats.common;

import java.util.ArrayList;

import com.rpereira.mineutils.ChatColor;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Stats2 {
	private float magic;
	private float stamina;
	private float strength;
	private float agility;
	private float clarity;
	private float spirit;
	private int mana;
	private int rage;
	private int energy;
	private int maxmana;
	private int maxrage;
	private int maxenergy;

	public Stats2() {
		this.magic = 0.0F;
		this.stamina = 0.0F;
		this.strength = 0.0F;
		this.agility = 0.0F;
		this.clarity = 0.0F;
		this.spirit = 0.0F;
		this.mana = 0;
		this.maxmana = 0;
		this.rage = 0;
		this.maxrage = 0;
		this.energy = 0;
		this.maxenergy = 0;
	}

	public void combine(Stats2 stat) {
		this.agility += stat.agility;
		this.clarity += stat.clarity;
		this.magic += stat.magic;
		this.stamina += stat.stamina;
		this.strength += stat.strength;
		this.spirit += stat.spirit;
		this.mana += stat.mana;
		this.maxmana += stat.maxmana;
		this.rage += stat.rage;
		this.maxrage += stat.maxrage;
		this.energy += stat.energy;
		this.maxenergy += stat.maxenergy;
	}

	public static Stats2 getEntityStats(EntityLivingBase entity) {
		return getEquipementStats(entity.getLastActiveItems());
	}

	public static Stats2 getEquipementStats(ItemStack... itemStacks) {
		Stats2 total = new Stats2();
		Stats2 stats = new Stats2();
		for (ItemStack itemStack : itemStacks) {
			total.combine(getItemStackStats(stats, itemStack));
		}
		return total;
	}

	public static Stats2 getItemStackStats(ItemStack itemStack) {
		return (getItemStackStats(new Stats2(), itemStack));
	}

	public static Stats2 getItemStackStats(Stats2 stats, ItemStack itemStack) {
		ItemStack is = itemStack;
		NBTTagCompound tag = is.getTagCompound();
		if (tag == null) {
			return (null);
		}
		stats.agility = tag.hasKey("agility") ? tag.getFloat("agility") : 0.0F;
		stats.clarity = tag.hasKey("clarity") ? tag.getFloat("clarity") : 0.0F;
		stats.strength = tag.hasKey("strength") ? tag.getFloat("strength") : 0.0F;
		stats.stamina = tag.hasKey("stamina") ? tag.getFloat("stamina") : 0.0F;
		stats.spirit = tag.hasKey("spirit") ? tag.getFloat("spirit") : 0.0F;
		stats.maxmana = tag.hasKey("maxmana") ? tag.getInteger("maxmana") : 0;
		stats.maxrage = tag.hasKey("maxrage") ? tag.getInteger("maxrage") : 0;
		stats.maxenergy = tag.hasKey("maxenergy") ? tag.getInteger("maxenergy") : 0;
		return (stats);
	}

	public static String getMagicDesc() {
		return I18n.format("stats.desc.magic", new Object[0]);
	}

	public static String getStrengthDesc() {
		return I18n.format("stats.desc.strength", new Object[0]);
	}

	public static String getStaminaDesc() {
		return I18n.format("stats.desc.stamina", new Object[0]);
	}

	public static String getAgilityDesc() {
		return I18n.format("stats.desc.agility", new Object[0]);
	}

	public static String getClarityDesc() {
		return I18n.format("stats.desc.clarity", new Object[0]);
	}

	public static String getSpiritDesc() {
		return I18n.format("stats.desc.spirit", new Object[0]);
	}

	public static String getMaxManaDesc() {
		return I18n.format("stats.desc.maxmana", new Object[0]);
	}

	public static String getMaxRageDesc() {
		return I18n.format("stats.desc.maxrage", new Object[0]);
	}

	public static String getMaxEnergyDesc() {
		return I18n.format("stats.desc.maxenergy", new Object[0]);
	}

	public static String getMagicName() {
		return I18n.format("stats.name.magic", new Object[0]);
	}

	public static String getStrengthName() {
		return I18n.format("stats.name.strength", new Object[0]);
	}

	public static String getStaminaName() {
		return I18n.format("stats.name.stamina", new Object[0]);
	}

	public static String getAgilityName() {
		return I18n.format("stats.name.agility", new Object[0]);
	}

	public static String getClarityName() {
		return I18n.format("stats.name.clarity", new Object[0]);
	}

	public static String getSpiritName() {
		return I18n.format("stats.name.spirit", new Object[0]);
	}

	public static String getManaName() {
		return I18n.format("stats.name.mana", new Object[0]);
	}

	public static String getRageName() {
		return I18n.format("stats.name.rage", new Object[0]);
	}

	public static String getMaxEnergyName() {
		return I18n.format("stats.name.maxenergy", new Object[0]);
	}

	public static String getMaxRageName() {
		return I18n.format("stats.name.maxrage", new Object[0]);
	}

	public static String getMaxManaName() {
		return I18n.format("stats.name.maxmana", new Object[0]);
	}

	public static String getEnergyName() {
		return I18n.format("stats.name.energy", new Object[0]);
	}

	public float getMagic() {
		return this.magic + this.clarity * 5.0F;
	}

	public float getStrength() {
		return this.strength;
	}

	public float getStamina() {
		return this.stamina;
	}

	public float getAgility() {
		return this.agility;
	}

	public float getClarity() {
		return this.clarity;
	}

	public float getSpirit() {
		return this.spirit;
	}

	public int getMana() {
		return this.mana;
	}

	public int getMaxMana() {
		return this.maxmana;
	}

	public int getRage() {
		return this.rage;
	}

	public int getMaxRage() {
		return this.maxrage;
	}

	public int getEnergy() {
		return this.energy;
	}

	public int getMaxEnergy() {
		return this.maxenergy;
	}

	public void setMagic(float value) {
		this.magic = value;
	}

	public void setStamina(float value) {
		this.stamina = value;
	}

	public void setStrength(float value) {
		this.strength = value;
	}

	public void setAgility(float value) {
		this.agility = value;
	}

	public void setClarity(float value) {
		this.clarity = value;
	}

	public void setSpirit(float value) {
		this.spirit = value;
	}

	public void setEnergy(int value) {
		this.energy = value;
	}

	public void setMaxEnergy(int value) {
		this.maxenergy = value;
	}

	public ArrayList<String> toStringList() {
		float[] values = { this.stamina, this.agility, this.strength, this.clarity, this.magic, this.spirit, this.mana,
				this.maxmana, this.rage, this.maxrage, this.energy, this.maxenergy };
		String[] names = { getStaminaName(), getAgilityName(), getStrengthName(), getClarityName(), getMagicName(),
				getSpiritName(), getManaName(), getMaxManaName(), getRageName(), getMaxRageName(), getEnergyName(),
				getMaxEnergyName() };

		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < values.length; i++) {
			if (values[i] != 0.0F) {
				if (values[i] > 0.0F) {
					list.add(ChatColor.GREEN + "+ " + values[i] + " " + names[i]);
				} else {
					list.add(ChatColor.RED + "- " + values[i] + " " + names[i]);
				}
			}
		}
		return list;
	}
}
