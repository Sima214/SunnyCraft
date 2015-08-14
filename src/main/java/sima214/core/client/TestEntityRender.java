package sima214.core.client;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import sima214.core.Constants;
import sima214.renderer.lwjgl_mc.VBOLoader;

public class TestEntityRender extends Render {
	private final ResourceLocation texLoc=new ResourceLocation("sunnycraft","textures/models/sun.png");
	private final ResourceLocation modelLoc=new ResourceLocation(Constants.SUNCR_ID, "/models/fireball.smodel");
	private VBOLoader vbo=new VBOLoader(modelLoc);;
	@Override
	public void doRender(Entity ent, double x, double y, double z, float p_76986_8_,float p_76986_9_) {
		vbo.tick();
		GL11.glPushMatrix();
		this.bindEntityTexture(ent);
		GL11.glTranslatef((float)x, (float)y, (float)z);
		vbo.buffer.drawTriangles();
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity ent) {
		return texLoc;
	}
	public void init(){
		vbo.tick();
	}
}
