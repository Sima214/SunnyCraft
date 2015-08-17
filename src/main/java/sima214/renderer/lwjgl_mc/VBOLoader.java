package sima214.renderer.lwjgl_mc;

import java.io.BufferedReader;
import java.nio.FloatBuffer;

import net.minecraft.util.ResourceLocation;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.Util;

import sima214.core.DataTypes;
import sima214.core.Logger;
import sima214.core.SimaCoreMain;
import sima214.core.client.IOUtils;
import sima214.core.client.IResourcePackChangeListener;
import sima214.renderer.lwjgl_mc.InterleavedBufferHelper.OpenGLClientStates;

public class VBOLoader implements IResourcePackChangeListener {
	private final static String sub=" ";
	private final static SmodelFormatType types[]=new SmodelFormatType[]
			{
		new SmodelFormatType(new InterleavedData[]{new InterleavedData(OpenGLClientStates.VERTEX, DataTypes.FLOAT, (byte) 3),new InterleavedData(OpenGLClientStates.TEXTURE, DataTypes.FLOAT, (byte) 2),new InterleavedData(OpenGLClientStates.NORMAL, DataTypes.FLOAT, (byte) 3)}, "VER3_TEX2_NOR3"),
		new SmodelFormatType(new InterleavedData[]{new InterleavedData(OpenGLClientStates.VERTEX, DataTypes.FLOAT, (byte) 3),new InterleavedData(OpenGLClientStates.TEXTURE, DataTypes.FLOAT, (byte) 2)}, "VER3_TEX2"),
		new SmodelFormatType(new InterleavedData[]{new InterleavedData(OpenGLClientStates.VERTEX, DataTypes.FLOAT, (byte) 3),new InterleavedData(OpenGLClientStates.NORMAL, DataTypes.FLOAT, (byte) 3)}, "VER3_NOR3")//A bit better here maybe?
			};
	private SmodelFormatType curType;
	private long curBufferOffset;
	protected ResourceLocation location;
	public InterleavedBufferHelper buffer;
	FloatBuffer floatBuffer;
	int numTriangles;
	byte state=0x00;//First bit is numTriangles, second is for current type and third for counts if we have gone threw the first face
	public VBOLoader(ResourceLocation location) {
		SimaCoreMain.registerPostClientLoad(this);
		this.location=location;
	}
	@Override
	public void onReload() {
		curBufferOffset=0;
		state=0;
		BufferedReader reader;
		try {
			reader=IOUtils.getReaderByResource(location);
			String curLine;
			while((curLine=reader.readLine()) !=null) {
				if(curLine.startsWith("//")){
					continue;
				}
				else if(curLine.startsWith("%")){
					setNumTriangles(Integer.parseInt(curLine.substring(1, curLine.length())));
				}
				else if(curLine.startsWith("#")){
					setTypeByString(curLine.substring(1, curLine.length()));
				}
				else {
					loadLine(curLine);
				}
			}
			reader.close();
			Util.checkGLError();
			buffer.setReady();
			Logger.info("Succesfully loaded the smodel");
		} catch (Exception e) {
			Logger.exception("Error while loading a vbo", e);
		}
	}
	private void setTypeByString(String substring) {
		for(SmodelFormatType type:types){
			if(substring.equals(type.match)){
				curType=type;
				Logger.info("Detected "+curType.match+" smodel format");
				state=(byte) (state|0x2);
				return;
			}
		}
	}
	/**
	 * @param numTriangles the numTriangles to set
	 */
	private void setNumTriangles(int numTriangles) {
		Logger.info("Smodel hint for total faces: "+numTriangles);
		this.numTriangles = numTriangles;
		state=(byte) (state|0x1);

	}
	void loadLine(String curLine){
		if(state==0x3)
		{
			initializeBuffer(curLine);
		}
		else if(state==0x7)
		{
			doLine(curLine);
		}
		else throw new IllegalStateException("Loader has not gotten the necessary hints to initialize the buffer.");
	}
	void initializeBuffer(String curLine){
		int newBufferSize=numTriangles*curType.numOfElementsPerFace;
		floatBuffer=BufferUtils.createFloatBuffer(curType.numOfElementsPerFace);
		if(buffer==null){
			buffer=new InterleavedBufferHelper(GL15.GL_STATIC_DRAW, GL15.GL_ARRAY_BUFFER, curType.interleaved);
			buffer.load(newBufferSize, DataTypes.FLOAT);
		}
		buffer.interleaved=curType.interleaved;
		buffer.bakeDrawInfo();
		state=(byte) (state|0x4);
		doLine(curLine);
	}
	private void doLine(String curLine) {
		floatBuffer.clear();
		String[] splitted=curLine.split(sub);
		for(String str:splitted){
			floatBuffer.put(Float.parseFloat(str));
		}
		buffer.bind();
		floatBuffer.rewind();
		GL15.glBufferSubData(buffer.getTarget(), curBufferOffset, floatBuffer);
		buffer.unbind();
		curBufferOffset+=curType.numOfBytesPerFace;
	}
}