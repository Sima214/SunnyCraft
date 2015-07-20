package sima214.sunnycraft.client.render;

import java.io.IOException;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import sima214.sunnycraft.client.Resourses;
import sima214.sunnycraft.client.shaders.FragmentShaderObject;
import sima214.sunnycraft.client.shaders.ShaderProgram;
import sima214.sunnycraft.client.shaders.UniformHelper;
import sima214.sunnycraft.client.shaders.VBOHelper;
import sima214.sunnycraft.client.shaders.VertexShaderObject;
import sima214.sunnycraft.core.LogHelper;
import sima214.sunnycraft.core.utils.IOUtils;
import sima214.sunnycraft.entities.SunEntity;

public class SunRenderer extends Render {
	private static final ResourceLocation Alive_resource = new ResourceLocation("sunnycraft","textures/models/sun.png");
	private VBOHelper vbo=new VBOHelper();
	private ShaderProgram shader=new ShaderProgram();
	private UniformHelper time=new UniformHelper("time");
	private UniformHelper timers=new UniformHelper("timers");
	//private Random random=new Random();
	public SunRenderer() {
		this.shadowOpaque=0f;
		this.shadowSize=0f;
	}

	@Override
	public void doRender(Entity entity, double x,double y, double z, float p_76986_8_,float p_76986_9_)
	{
		doRender((SunEntity) entity, x, y, z, p_76986_8_, p_76986_9_);
	}
	public void doRender(SunEntity entity, double x,double y, double z, float p_76986_8_,float p_76986_9_)
	{
		int scale=(int) entity.width;
		GL11.glPushMatrix();
		shader.activate();
		time.updatei((int) entity.worldObj.getWorldTime());
		timers.update3f(0f, entity.getDeathTimer()/100f, entity.getHitTimer()/20f);//TODO
		this.bindEntityTexture(entity);
		GL11.glTranslated(x,y+0.5,z);//Why so much casting?(Bye)
		GL11.glScalef(scale*0.5F,scale*0.5F,scale*0.5F);
		//GL11.glRotatef(entity.worldObj.getTotalWorldTime()%360, 0f, 1f, 0f);
		GL11.glRotatef(entity.worldObj.getTotalWorldTime()%3600, 0.1f, 0f, 0.1f);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		//GL11.glDisable(GL11.GL_CULL_FACE);
		vbo.useVBO();
		//GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_BLEND);
		shader.deactivate();
		GL11.glPopMatrix();
		int error = GL11.glGetError();
		if(error!=0){
			LogHelper.info("OPEN_GL ERROR : "+error);};
	}
	@Override
	protected ResourceLocation getEntityTexture(Entity ent)
	{
		return Alive_resource;
	}
	public void init()
	{
		FragmentShaderObject frag=new FragmentShaderObject();
		VertexShaderObject vert=new VertexShaderObject();
		try {
			frag.init((IOUtils.loadSource(Resourses.Sun_Fragment)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			vert.init((IOUtils.loadSource(Resourses.Sun_Vertex)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		shader.init(frag, vert);
		shader.activate();
		time.init(shader);
		timers.init(shader);
		shader.deactivate();
		vbo.initVBO(Resourses.getModel(),20 , true, 0, true, 12);
	}
}
