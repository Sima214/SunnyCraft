package sima214.sunnycraft.client.shaders;

import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL11;

import sima214.sunnycraft.core.LogHelper;

public class VertexShaderObject {


	String source;
	int id;

	public void init(String source) {
		this.source=source;

	}

	public void compile() {
		id=ARBShaderObjects.glCreateShaderObjectARB(ARBVertexShader.GL_VERTEX_SHADER_ARB);
		ARBShaderObjects.glShaderSourceARB(id, source);
		ARBShaderObjects.glCompileShaderARB(id);
		if (ARBShaderObjects.glGetObjectParameteriARB(this.id, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_FALSE)
		{
			LogHelper.error("Something went horribly wrong with the vertex shader");
		}
	}

}
