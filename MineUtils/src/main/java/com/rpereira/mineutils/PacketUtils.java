package com.rpereira.mineutils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

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

	/**
	 * write the given hashmap to the buffer
	 * 
	 * @throws Exception
	 */
	public static void writeHashMap(ByteBuf buf, HashMap<?, ?> attributes) throws Exception {

		if (attributes == null) {
			throw new Exception("null hashmap");
		}

		// Convert Map to byte array
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOut);
		out.writeObject(attributes);

		// Parse byte array to Map
		ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
		ObjectInputStream in = new ObjectInputStream(byteIn);
		int length = in.available();
		buf.writeInt(length);
		buf.writeBytes(in, length);
		byteIn.close();
		in.close();
		byteOut.close();
		out.close();
	}

	/**
	 * read a hashmap from the buffer
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static HashMap<?, ?> readHashMap(ByteBuf buf) throws Exception {
		int length = buf.readInt();
		if (length == 0) {
			throw new Exception("null HashMap");
		}
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		buf.readBytes(byteOut, length);
		ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
		ObjectInputStream in = new ObjectInputStream(byteIn);
		HashMap<Object, Object> map = (HashMap<Object, Object>) in.readObject();
		byteIn.close();
		in.close();
		byteOut.close();
		return (map);
	}
}
