package com.rpereira.mineteam.common.packets;

import java.math.BigInteger;
import java.security.SecureRandom;

import net.minecraft.entity.player.EntityPlayerMP;

public class InvitationToken {

	private static final SecureRandom random = new SecureRandom();

	public String value;
	public final EntityPlayerMP invited;
	public final EntityPlayerMP inviter;
	public final long timestamp;
	public final String teamName;

	public InvitationToken(EntityPlayerMP inviter, EntityPlayerMP invited, String teamName) {
		this.invited = invited;
		this.inviter = inviter;
		this.value = null;
		this.timestamp = System.currentTimeMillis();
		this.teamName = teamName;
	}

	public String newValue() {
		this.value = new BigInteger(130, random).toString(32);
		return (this.value);
	}
}
