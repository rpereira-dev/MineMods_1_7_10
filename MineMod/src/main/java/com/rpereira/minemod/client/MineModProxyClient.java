package com.rpereira.minemod.client;

import com.rpereira.minemod.client.input.MineModInput;
import com.rpereira.minemod.client.render.MineModRender;
import com.rpereira.minemod.client.team.MineModTeam;
import com.rpereira.minemod.common.MineModProxyCommon;
import com.rpereira.minemod.common.item.MineModItems;

import net.minecraft.client.settings.KeyBinding;

public class MineModProxyClient extends MineModProxyCommon {

	public MineModProxyClient() {
		super();
		super.addProxy(new MineModTeam());
		super.addProxy(new MineModRender());
		super.addProxy(new MineModItems());
		super.addProxy(new MineModInput());
	}
}
