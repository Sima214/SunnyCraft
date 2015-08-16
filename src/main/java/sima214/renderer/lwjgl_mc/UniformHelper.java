package sima214.renderer.lwjgl_mc;

import org.lwjgl.opengl.GL20;

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
	}
	public void update(float value){
		GL20.glUniform1f(id, value);
	}
}
