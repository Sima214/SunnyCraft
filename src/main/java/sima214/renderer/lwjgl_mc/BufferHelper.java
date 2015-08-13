package sima214.renderer.lwjgl_mc;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL15;

import sima214.core.DataTypes;


public class BufferHelper{
	protected int id;//Internal name/id used by the OpenGl implementation
	protected int usage=GL15.GL_STATIC_DRAW;
	protected int target=GL15.GL_ARRAY_BUFFER;
	protected boolean ready;
	protected int size;//Total amount of elements in the buffer(not bytes)
	public BufferHelper(int usage,int target) {
		this.usage=usage;
		this.target=target;
	}
	/**
	 * @return i.e. GL_STATIC_DRAW
	 */
	public int getUsage() {
		return usage;
	}
	/**
	 * @param usage
	 */
	public void setUsage(int usage) {
		this.usage = usage;
	}
	/**
	 * @return The current target for this opengl buffer()
	 */
	public int getTarget() {
		return target;
	}
	/**
	 * @param target
	 */
	public void setTarget(int target) {
		this.target = target;
	}
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	/**
	 * @return true if this buffer is ready to be used(has data it can render)
	 */
	public boolean isReady() {
		return ready;
	}
	/**
	 * Sets this buffer ready to be used
	 */
	void setReady() {
		this.ready = true;
	}
	/**
	 * @return Internal name/id used by the OpenGl implementation.
	 */
	public int getName() {
		return id;
	}
	//************************
	// Start of main functions
	//************************
	public void reset(DataTypes type){
		GL15.glBufferData(target, type.getBytes()*size, usage);//TODO Bit shift?
		ready=false;
	}
	public void delete(){
		GL15.glDeleteBuffers(id);
		ready=false;
	}
	public void load(FloatBuffer buffer){
		genBind();
		buffer.rewind();
		size=buffer.remaining();
		GL15.glBufferData(target, buffer, usage);
		unbind();
		ready=true;
	}
	public void load(int initialSize,DataTypes type){
		genBind();
		size=initialSize;
		reset(type);
		unbind();
	}
	public void genBind(){
		id=GL15.glGenBuffers();
		bind();
	}
	public void bind(){
		GL15.glBindBuffer(target, id);
	}
	/**
	 * Unbinds the currently bound buffer
	 */
	public void unbind(){
		GL15.glBindBuffer(target, 0);
	}
	public static void unbind(int target){
		GL15.glBindBuffer(target, 0);
	}

}
