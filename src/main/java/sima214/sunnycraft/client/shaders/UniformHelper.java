package sima214.sunnycraft.client.shaders;

import org.lwjgl.opengl.GL20;

public class UniformHelper {
	int loc;
	String name;
	public UniformHelper(String name){
		this.name=name;
	}
	public void init(ShaderProgram program)
	{
		this.loc=GL20.glGetUniformLocation(program.id, name);
	}
	public void updatei(int value)
	{
		GL20.glUniform1i(loc, value);
	}
	public void update3f(float value1,float value2,float value3)
	{
		GL20.glUniform3f(loc, value1, value2, value3);
	}
}
