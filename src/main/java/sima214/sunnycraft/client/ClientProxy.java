package sima214.sunnycraft.client;

import net.minecraftforge.client.MinecraftForgeClient;
import sima214.sunnycraft.Registry;
import sima214.sunnycraft.client.item.PDSUItemRenderer;
import sima214.sunnycraft.common.CommonProxy;

public class ClientProxy extends CommonProxy {
	PDSUItemRenderer portDSURenderer=new PDSUItemRenderer();

	@Override
	public void loadRenderers() {
		MinecraftForgeClient.registerItemRenderer(Registry.portDsu, portDSURenderer);
	}
	
}
