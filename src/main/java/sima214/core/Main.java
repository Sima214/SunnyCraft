package sima214.core;

import sima214.core.common.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Constants.CORE_ID,name=Constants.CORE_NAME,version=Constants.CORE_VERSION)
public class Main {
	@Mod.Instance(Constants.CORE_ID)
	public static Main instance;
	@SidedProxy(clientSide="sima214.core.client.ClientProxy",serverSide="sima214.core.common.CommonProxy")
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preinit(FMLPreInitializationEvent event)
	{
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
	}

	@Mod.EventHandler
	public void postinit(FMLPostInitializationEvent event)
	{
	}

}