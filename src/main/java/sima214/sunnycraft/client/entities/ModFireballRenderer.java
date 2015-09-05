package sima214.sunnycraft.client.entities;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import sima214.renderer.lwjgl_mc.ShaderProgram;
import sima214.renderer.lwjgl_mc.VBOLoader;
import sima214.sunnycraft.client.Resources;
import sima214.sunnycraft.common.entities.EntityModFireball;

public class ModFireballRenderer extends Render {
	private final static String UNIFORM_TIMERS="timers";
	private final static String UNIFORM_TIME="time";
	private final static float scale=0.5f;
	private final static VBOLoader vbo=new VBOLoader(Resources.fireballModel);
	private final static ShaderProgram shader=new ShaderProgram(Resources.fireballShader);//Static because apparently something ended up making three sets of these
	private int time;
	public ModFireballRenderer() {
		shader.addUniform(UNIFORM_TIMERS);
		shader.addUniform(UNIFORM_TIME);
	}
	private void doRender(EntityModFireball entity, double x, double y, double z, float yaw, float pitch) {
		time=(int) (entity.worldObj.getTotalWorldTime()%24000);//Used for animating.
		GL11.glPushMatrix();
		bindEntityTexture(null);
		GL11.glTranslatef(0, 0.5f, 0);//TODO bake the transformations
		GL11.glTranslated(x, y, z);
		GL11.glRotatef(time%360, 0.1f, 0.1f, 0.1f);
		GL11.glScalef(scale, scale, scale);
		shader.activate();
		shader.getUniform(UNIFORM_TIME).update(time);//Hope it doesn't overflow
		shader.getUniform(UNIFORM_TIMERS).update(entity.getDeathTimer()/100f, entity.getHitTimer()/20f,0f);
		vbo.buffer.drawTriangles();
		shader.deactivate();
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return Resources.fireballTexture;
	}
	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch) {//Maybe?
		doRender((EntityModFireball)entity,x,y,z,yaw,pitch);
	}
	@Override
	public void doRenderShadowAndFire(Entity entity, double x, double y, double z, float yaw,float pitch) {}
}
