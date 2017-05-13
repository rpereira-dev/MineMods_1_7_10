package com.rpereira.minemod.client.team;

import com.rpereira.minemod.common.IMineModProxy;
import com.rpereira.mineteam.MineTeam;
import com.rpereira.mineteam.client.gui.GuiTeamOverlay;

public class MineModTeam implements IMineModProxy {

	@Override
	public void init() {
		GuiTeamOverlay guiTeamOverlay = MineTeam.guiTeamOverlay();
		guiTeamOverlay.setPlayerRender(new MineModPlayerRenderer());

	}

	@Override
	public void preInit() {
		// TODO Auto-generated method stub

	}

}
