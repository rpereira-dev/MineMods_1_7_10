package com.rpereira.mineteam.common.packets;

import io.netty.buffer.ByteBuf;

public class PacketUtils {

	/** read a string from the given byte buffer */
	public static String readString(ByteBuf buf) {
		int strlen = buf.readInt();
		char[] chars = new char[strlen];
		for (int i = 0; i < strlen; i++) {
			chars[i] = buf.readChar();
		}
		return (new String(chars));
	}

	/** write a string to the given byte buffer */
	public static void writeString(ByteBuf buf, String str) {
		int length = str.length();
		buf.writeInt(length);
		for (int i = 0; i < length; i++) {
			buf.writeChar(str.charAt(i));
		}
	}

}
