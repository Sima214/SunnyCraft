package sima214.renderer.lwjgl_mc;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.Util;

import sima214.core.Logger;

public class UniformHelper {

	private ShaderProgram program;
	private int id;
	/**
	 * @return the Location of this uniform
	 */
	protected int getId() {
		return id;
	}
	public UniformHelper(ShaderProgram prog) {
		this.program=prog;
	}
	public void init(String name){
		id=GL20.glGetUniformLocation(program.id, name);
		Util.checkGLError();
		Logger.info("Uniform named: "+name+" loaded succesfully."+id);
	}
	public void update(float... value){
		switch (value.length) {
		case 1 : GL20.glUniform1f(id, value[0]);
		break;
		case 2 : GL20.glUniform2f(id, value[0], value[1]);
		break;
		case 3 : GL20.glUniform3f(id, value[0], value[1], value[2]);
		break;
		default : throw new IllegalArgumentException("Invalid amount of arguments: "+value.length);
		}
	}
	public void update(int... value){
		switch (value.length) {
		case 1 : GL20.glUniform1i(id, value[0]);
		break;
		case 2 : GL20.glUniform2i(id, value[0], value[1]);
		break;
		case 3 : GL20.glUniform3i(id, value[0], value[1], value[2]);
		break;
		default : throw new IllegalArgumentException("Invalid amount of arguments: "+value.length);
		}
	}
}
