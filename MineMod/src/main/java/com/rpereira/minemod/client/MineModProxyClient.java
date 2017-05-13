package com.rpereira.minemod.client;

import com.rpereira.minemod.client.team.MineModTeam;
import com.rpereira.minemod.common.MineModProxyCommon;

public class MineModProxyClient extends MineModProxyCommon {

	public MineModProxyClient() {
		super();
		super.addProxy(new MineModTeam());
	}
}
