package sima214.renderer.lwjgl_mc;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import net.minecraft.util.ResourceLocation;
import sima214.core.Logger;
import sima214.core.client.IOUtils;
import sima214.core.client.IResourcePackChangeListener;
import sima214.core.client.ResourceReloader;

public class VBOLoader implements IResourcePackChangeListener {
	private final static String sub=" ";
	private final static SmodelFormatType types[]=new SmodelFormatType[]{};
	protected ResourceLocation location;
	public InterleavedBufferHelper buffer;
	FloatBuffer floatBuffer;
	int numTriangles;
	public VBOLoader(ResourceLocation location) {
		ResourceReloader.register(this);
		this.location=location;
	}
	@Override
	public void onReload() {
		BufferedReader reader;
		try {
			reader=IOUtils.getReaderByResource(location);
			String curLine;
			String[] splited;
			while((curLine=reader.readLine()) !=null) {
				if(curLine.startsWith("//")){
					continue;
				}
				else if(curLine.startsWith("@")){
					numTriangles=Integer.parseInt(curLine.substring(1, curLine.length()));
				}
				else if(curLine.startsWith("#")){
					setTypeByString(curLine.substring(1, curLine.length()));
				}
				else {
					splited=curLine.split(sub);
					for(String str:splited){
						floatBuffer.put(Float.parseFloat(str));
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			Logger.exception("Error while loading a vbo", e);
		}
	}
	private void setTypeByString(String substring) {
		for(SmodelFormatType type:types){
			if(substring.equals(type.match)){
				return;
			}
		}
	}
	/**
	 * @param numTriangles the numTriangles to set
	 */
	void setNumTriangles(int numTriangles) {
		this.numTriangles = numTriangles;
	}

}
