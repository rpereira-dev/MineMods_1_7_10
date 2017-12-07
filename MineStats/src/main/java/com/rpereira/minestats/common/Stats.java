package com.rpereira.minestats.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.rpereira.mineutils.ChatColor;

import net.minecraft.client.resources.I18n;

/** represent the stats (for an Entity, an Item...) */
public class Stats implements Iterable<Stat>, Iterator<Stat> {

	/** the stats */
	private final HashMap<Stat, Float> stats;
	private Iterator<Stat> iterator;

	public Stats() {
		this.stats = new HashMap<Stat, Float>();
	}

	public final HashMap<Stat, Float> getStats() {
		return (this.stats);
	}

	/** get the given stat value, return null if not set */
	public Float get(Stat stat) {
		return (this.stats.get(stat));
	}

	public Float get(Stat stat, float defaultValue) {
		Float f = this.stats.get(stat);
		return (f != null ? f : defaultValue);
	}

	/** add a stat */
	public void addStat(Stat stat, float fadd) {
		float f = this.get(stat, 0.0f) + fadd;
		if (f <= 0.0f && this.stats.containsKey(stat)) {
			this.stats.remove(stat);
		} else {
			this.stats.put(stat, f);
		}
	}

	/** set the stat */
	public void setStat(Stat stat, float value) {
		this.stats.put(stat, value);
	}

	/** add the given stats to this instance */
	public void combine(Stats stats) {
		for (Entry<Stat, Float> entry : stats.stats.entrySet()) {
			Float f = this.stats.get(entry.getKey());
			this.stats.put(entry.getKey(), (f == null ? 0.0F : f.floatValue()) + entry.getValue());
		}
	}

	public Set<Entry<Stat, Float>> getValues() {
		return (this.stats.entrySet());
	}

	public ArrayList<String> toStringList() {

		ArrayList<String> list = new ArrayList<String>();
		for (Entry<Stat, Float> entry : this.stats.entrySet()) {
			float value = entry.getValue();
			String name = I18n.format("stats.name." + entry.getKey().getUnlocalizedName());
			if (value != 0.0F) {
				if (value > 0.0F) {
					list.add(ChatColor.GREEN + "+ " + value + " " + name + ChatColor.RESET);
				} else {
					list.add(ChatColor.RED + "- " + value + " " + name + ChatColor.RESET);
				}
			}
		}
		return list;
	}

	public void reset() {
		this.stats.clear();
	}

	@Override
	public boolean hasNext() {
		return (this.iterator.hasNext());
	}

	@Override
	public Stat next() {
		return (this.iterator.next());
	}

	@Override
	public Iterator<Stat> iterator() {
		this.iterator = this.stats.keySet().iterator();
		return (this.iterator);
	}
}
