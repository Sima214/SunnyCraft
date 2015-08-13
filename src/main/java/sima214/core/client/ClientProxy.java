package sima214.core.client;

import net.minecraftforge.common.MinecraftForge;
import sima214.core.common.CommonProxy;

public class ClientProxy extends CommonProxy{
	public static final ResourceReloader reloadHandler=new ResourceReloader();
	public ClientProxy() {
		MinecraftForge.EVENT_BUS.register(reloadHandler);
	}
}
