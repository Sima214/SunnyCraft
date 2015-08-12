package sima214.core;

import org.lwjgl.opengl.GL11;

public enum DataTypes {
	BYTE((byte)8),
	SHORT((byte)16),
	INT((byte)32),
	FLOAT((byte)32),
	LONG((byte)64),
	DOUBLE((byte)64);
	private byte bitsPer;

	DataTypes(byte bitsPer) {
		this.bitsPer=bitsPer;
	}

	/**
	 * @return The amount of bits this primitive data type uses in memory
	 */
	public byte getBits() {
		return bitsPer;
	}
	/**
	 * @return The amount of bytes this primitive data type uses in memory
	 */
	public byte getBytes() {
		return (byte) (bitsPer/8);
	}
	public int getForGL()
	{
		switch (this) {
		case BYTE:return GL11.GL_BYTE;
		case SHORT:return GL11.GL_SHORT;
		case INT:return GL11.GL_INT;
		case FLOAT:return GL11.GL_FLOAT;
		case DOUBLE:return GL11.GL_DOUBLE;
		default:return 0;
		}
	}
}
