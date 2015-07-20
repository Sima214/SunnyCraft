package sima214.sunnycraft.client.shaders;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class VBOHelper {
	public int id;
	int size;
	boolean hasVertexArrays;
	boolean hasTextureCoords;
	int stride;
	long vertoffset;
	long texoffset;
	public void initVBO(float[] data,int stride,boolean hasVertexArrays,long vertoffset,boolean hasTextureCoords,long texoffset)
	{
		this.stride=stride;
		this.hasVertexArrays=hasVertexArrays;
		this.hasTextureCoords=hasTextureCoords;
		this.vertoffset=vertoffset;
		this.texoffset=texoffset;
		this.id=GL15.glGenBuffers();
		size=data.length/5;
		FloatBuffer buffer=BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.rewind();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, id);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);//Probably this unbinds what there was at "target"
	}
	public void useVBO()
	{
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.id);
		if(this.hasVertexArrays)
		{
			GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
			GL11.glVertexPointer(3, GL11.GL_FLOAT, this.stride, this.vertoffset);
		};
		if(this.hasTextureCoords)
		{
			GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
			GL11.glTexCoordPointer(2, GL11.GL_FLOAT, this.stride, this.texoffset);
		};
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, this.size);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		if(this.hasVertexArrays)
		{
			GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
		};
		if(this.hasTextureCoords)
		{
			GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
		};
	}
}

