package com.rpereira.mineclass.common.stats;

import com.rpereira.minestats.common.Stat;

public abstract class StatResource extends Stat {

	private final Stat statmax;

	public StatResource(Stat statmax) {
		super();
		this.statmax = statmax;
	}

	public Stat getStatmax() {
		return (this.statmax);
	}
}
