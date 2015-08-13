package sima214.renderer.lwjgl_mc;
import org.lwjgl.opengl.GL11;

import sima214.core.DataTypes;
import sima214.renderer.lwjgl_mc.InterleavedBufferHelper.OpenGLClientStates;

public class InterleavedData {
	OpenGLClientStates bindPointType;
	DataTypes dataType;
	byte vectorSize;
	byte offset;
	public InterleavedData(OpenGLClientStates bindPointType,DataTypes dataType,byte vectorSize)
	{
		this.bindPointType=bindPointType;
		this.dataType=dataType;
		this.vectorSize=vectorSize;
	}
	public void enablePointers(InterleavedBufferHelper IBH)
	{
		bindPointType.enableClientState();
		switch (bindPointType) {
		case VERTEX:GL11.glVertexPointer(vectorSize, dataType.getForGL(), IBH.stride, offset);
		break;
		case TEXTURE:GL11.glTexCoordPointer(vectorSize, dataType.getForGL(), IBH.stride, offset);
		break;
		case NORMAL:GL11.glNormalPointer(dataType.getForGL(), IBH.stride, offset);
		break;
		default:throw new RuntimeException("Because unknown stuff and things");
		}
	}
	public void disablePointers()
	{
		bindPointType.disableClientState();
	}
	public byte bake(byte previous){
		this.offset=previous;
		previous+=vectorSize*dataType.getBytes();
		return previous;
	}
}
