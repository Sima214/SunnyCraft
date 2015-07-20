package sima214.sunnycraft.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import sima214.sunnycraft.client.render.SunRenderer;
import sima214.sunnycraft.core.CommonProxy;
import sima214.sunnycraft.entities.SunEntity;

public class ClientProxy extends CommonProxy {
	static SunRenderer sun=new SunRenderer();
	@Override
	public void RegisterRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(SunEntity.class, sun);
	}
	@Override
	public void init()
	{
		sun.init();
	}
}
