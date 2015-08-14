package sima214.core.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import sima214.core.TestEntity;
import sima214.core.common.CommonProxy;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{
	TestEntityRender ter=new TestEntityRender();
	public static final ResourceReloader reloadHandler=new ResourceReloader();
	public ClientProxy() {
		IReloadableResourceManager manager=(IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager();
		manager.registerReloadListener(reloadHandler);
	}
	@Override
	public void init() {
		RenderingRegistry.registerEntityRenderingHandler(TestEntity.class, ter);
	}
	@Override
	public void post() {
		ter.init();
	}
}
