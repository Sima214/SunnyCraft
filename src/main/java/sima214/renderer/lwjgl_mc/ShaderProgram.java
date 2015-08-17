package sima214.renderer.lwjgl_mc;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.OpenGLException;

import sima214.core.SimaCoreMain;
import sima214.core.client.IOUtils;
import sima214.core.client.IResourcePackChangeListener;

public class ShaderProgram implements IResourcePackChangeListener {
	public int id;
	public Map<String, UniformHelper> uniforms=new HashMap<String, UniformHelper>();
	protected ResourceLocation location;
	private final Shader vert=new Shader(ShaderTypes.VERTEX);
	private final Shader frag=new Shader(ShaderTypes.FRAGMENT);
	public ShaderProgram(ResourceLocation loc) {
		this.location=loc;
		SimaCoreMain.registerPostClientLoad(this);
	}
	public void addUniform(String name){
		uniforms.put(name, new UniformHelper(this));
	}
	public UniformHelper getUniform(String name){
		return uniforms.get(name);
	}
	public void activate(){
		ARBShaderObjects.glUseProgramObjectARB(this.id);
	}
	public void deactivate(){
		ARBShaderObjects.glUseProgramObjectARB(0);
	}
	@Override
	public void onReload() {
		vert.loadCompile(location);
		frag.loadCompile(location);
		this.id=ARBShaderObjects.glCreateProgramObjectARB();
		ARBShaderObjects.glAttachObjectARB(this.id, vert.id);
		ARBShaderObjects.glAttachObjectARB(this.id, frag.id);
		ARBShaderObjects.glLinkProgramARB(this.id);
		handleInfo();
		initUniforms();
	}
	private void handleInfo(){
		if(ARBShaderObjects.glGetObjectParameteriARB(this.id, ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL11.GL_FALSE || ARBShaderObjects.glGetObjectParameteriARB(this.id, ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL11.GL_FALSE)
			throw new OpenGLException("PROGRAM ERROR \n"+ARBShaderObjects.glGetInfoLogARB(this.id, ARBShaderObjects.glGetObjectParameteriARB(this.id, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB)));
	}
	private void initUniforms(){
		activate();
		for(Map.Entry<String, UniformHelper> pair:uniforms.entrySet()){
			pair.getValue().init(pair.getKey());
		}
		deactivate();
	}
	class Shader{
		protected ShaderTypes type;
		protected int id;
		public Shader(ShaderTypes type) {
			this.type=type;
		}
		protected void loadCompile(ResourceLocation loc){
			id=ARBShaderObjects.glCreateShaderObjectARB(type.getType());
			String source=IOUtils.loadText(new ResourceLocation(loc.getResourceDomain(), loc.getResourcePath()+type.getExtension()));
			ARBShaderObjects.glShaderSourceARB(id, source);
			ARBShaderObjects.glCompileShaderARB(id);
			handleInfo();
		}
		private void handleInfo(){
			boolean success=ARBShaderObjects.glGetObjectParameteriARB(this.id, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB)==GL11.GL_TRUE;
			if(!success)
				throw new OpenGLException(type.toString()+" COMPILE ERROR \n"+ARBShaderObjects.glGetInfoLogARB(id, ARBShaderObjects.glGetObjectParameteriARB(id, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB)));
		}
	}
	enum ShaderTypes{
		VERTEX(GL20.GL_VERTEX_SHADER,".vert"),
		FRAGMENT(GL20.GL_FRAGMENT_SHADER,".frag");
		private final int type;
		private final String extension;
		ShaderTypes(int type,String extension){
			this.type=type;
			this.extension=extension;
		}
		/**
		 * @return the Enum used for this shader type
		 */
		protected int getType() {
			return type;
		}
		/**
		 * @return the extension
		 */
		protected String getExtension() {
			return extension;
		}
	}
}
