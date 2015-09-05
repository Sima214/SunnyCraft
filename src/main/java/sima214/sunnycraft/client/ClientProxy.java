package sima214.sunnycraft.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.Render;
import net.minecraftforge.client.MinecraftForgeClient;
import sima214.sunnycraft.Registry;
import sima214.sunnycraft.client.entities.ModFireballRenderer;
import sima214.sunnycraft.client.item.PDSUItemRenderer;
import sima214.sunnycraft.common.CommonProxy;
import sima214.sunnycraft.common.entities.EntityModFireball;

public class ClientProxy extends CommonProxy {
	PDSUItemRenderer portDSURenderer=new PDSUItemRenderer();
	Render fireballRenderer=new ModFireballRenderer();
	@Override
	public void loadRenderers() {
		MinecraftForgeClient.registerItemRenderer(Registry.portDsu, portDSURenderer);
		RenderingRegistry.registerEntityRenderingHandler(EntityModFireball.class, fireballRenderer);
	}

}
