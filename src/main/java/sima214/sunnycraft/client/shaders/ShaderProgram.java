package sima214.sunnycraft.client.shaders;

import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;

import sima214.sunnycraft.core.LogHelper;
import sima214.sunnycraft.core.utils.IOUtils;

public class ShaderProgram {
	int id;

	public void init(String basename)
	{
		FragmentShaderObject frag=new FragmentShaderObject();
		VertexShaderObject vert=new VertexShaderObject();
		frag.init((IOUtils.loadText(basename+".frag")));
		vert.init((IOUtils.loadText(basename+".vert")));
		frag.compile();
		vert.compile();
		id=ARBShaderObjects.glCreateProgramObjectARB();
		ARBShaderObjects.glAttachObjectARB(this.id,frag.id );
		ARBShaderObjects.glAttachObjectARB(this.id,vert.id );
		ARBShaderObjects.glLinkProgramARB(this.id);
		ARBShaderObjects.glLinkProgramARB(this.id);
		if (ARBShaderObjects.glGetObjectParameteriARB(this.id, ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL11.GL_FALSE) {
			getLogInfo();
			return;
		}

		ARBShaderObjects.glValidateProgramARB(this.id);
		if (ARBShaderObjects.glGetObjectParameteriARB(this.id, ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL11.GL_FALSE) {
			getLogInfo();
			return;
		}

	}
	private  void getLogInfo() {
		LogHelper.warn(ARBShaderObjects.glGetInfoLogARB(this.id, ARBShaderObjects.glGetObjectParameteriARB(this.id, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB)));
	}
	public void activate()
	{
		ARBShaderObjects.glUseProgramObjectARB(this.id);
	}
	public void deactivate()
	{
		ARBShaderObjects.glUseProgramObjectARB(0);
	}
}
