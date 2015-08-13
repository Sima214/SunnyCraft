package sima214.renderer.lwjgl_mc;

import org.lwjgl.opengl.GL11;

import sima214.core.Logger;

public class InterleavedBufferHelper extends BufferHelper {

	protected InterleavedData[] interleaved;
	protected int stride;
	protected int EndIndex;
	public InterleavedBufferHelper(int usage, int target,InterleavedData[] data) {
		super(usage, target);
		this.interleaved=data;
	}
	public void bakeDrawInfo(){
		byte previous=0;
		byte totalNumOfElements=0;
		for(InterleavedData data:interleaved){
			Logger.debug(previous+" bytes offset");
			previous=data.bake(previous);
			totalNumOfElements+=data.vectorSize;
		}
		stride=previous;
		EndIndex=size/totalNumOfElements;
		Logger.debug("Stride: "+stride+" ,Total Indexes: "+EndIndex);
	}
	public void drawTriangles(){
		if(!ready)return;
		bind();
		for(InterleavedData data:interleaved){
			data.enablePointers(this);
		}
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, EndIndex);
		for(InterleavedData data:interleaved){
			data.disablePointers();
		}
		unbind();
	}
	public enum OpenGLClientStates {
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
