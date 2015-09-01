package sima214.sunnycraft;

import sima214.core.Constants;
import sima214.core.SimaCoreMain;
import sima214.sunnycraft.common.CommonProxy;
import sima214.sunnycraft.common.handlers.SunnyCraftCommands;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLConstructionEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Constants.SUNCR_ID,name=Constants.SUNCR_NAME,version=Constants.SUNCR_VERSION,dependencies=SimaCoreMain.dependStr)
public class SunnyCraftMain {
	@Mod.Instance(Constants.SUNCR_ID)
	public static SunnyCraftMain instance;
	@SidedProxy(clientSide="sima214.sunnycraft.client.ClientProxy",serverSide="sima214.sunnycraft.common.CommonProxy")
	public static CommonProxy proxy;
	@Mod.EventHandler
	public void construct(FMLConstructionEvent event)
	{	//We need that class to load up as soon as possible.
		Registry.loadUp();
	}
	@Mod.EventHandler
	public void preinit(FMLPreInitializationEvent event)
	{
	}
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		Registry.init();
		Recipes.init(event);
	}

	@Mod.EventHandler
	public void postinit(FMLPostInitializationEvent event)
	{
		proxy.loadRenderers();
	}
	@Mod.EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new SunnyCraftCommands());
	}
}
