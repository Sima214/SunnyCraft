package sima214.renderer.lwjgl_mc;

import org.lwjgl.opengl.GL11;

import sima214.core.DataTypes;

public class InterleavedBufferHelper extends BufferHelper {
	
	protected InterleavedData[] interleaved;
	protected int stride;
	protected int totalTriangles;
	public InterleavedBufferHelper(int usage, int target,InterleavedData[] data) {
		super(usage, target);
		this.interleaved=data;
		bakeDrawInfo();
	}
	private void bakeDrawInfo(){
		stride=0;
	}
	public void drawTriangles(){
		
	}
class InterleavedData {
	OpenGLClientStates bindPointType;
	DataTypes dataType;
	byte vectorSize;
	public InterleavedData(OpenGLClientStates bindPointType,DataTypes dataType,byte vectorSize) 
	{
		this.bindPointType=bindPointType;
		this.dataType=dataType;
		this.vectorSize=vectorSize;
	}
	public void enablePointers(InterleavedBufferHelper IBH)
	{
		int notdone = 0;//TODO
		bindPointType.enableClientState();
		switch (bindPointType) {
		case VERTEX:GL11.glVertexPointer(vectorSize, dataType.getForGL(), IBH.stride, notdone);
			break;
		case TEXTURE:GL11.glTexCoordPointer(vectorSize, dataType.getForGL(), IBH.stride, notdone);
			break;
		case NORMAL:GL11.glNormalPointer(dataType.getForGL(), IBH.stride, notdone);
			break;
		default:throw new RuntimeException("Because unknown stuff and things");
		}
	}
	public void disablePointers()
	{
		bindPointType.disableClientState();
	}
	
}

enum OpenGLClientStates {
	VERTEX(GL11.GL_VERTEX_ARRAY),
	TEXTURE(GL11.GL_TEXTURE_COORD_ARRAY),
	NORMAL(GL11.GL_NORMAL_ARRAY);
	int vertexAtrribute;
	private OpenGLClientStates(int i) {
		this.vertexAtrribute=i;
	}
	public void enableClientState(){
		GL11.glEnableClientState(vertexAtrribute);
	}
	public void disableClientState(){
		GL11.glDisableClientState(vertexAtrribute);
	}
}
}
