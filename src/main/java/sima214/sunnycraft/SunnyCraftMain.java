package sima214.sunnycraft;

import sima214.core.Constants;
import sima214.core.SimaCoreMain;
import sima214.sunnycraft.common.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Constants.SUNCR_ID,name=Constants.SUNCR_NAME,version=Constants.SUNCR_VERSION,dependencies="required-after:CoFHCore;"+SimaCoreMain.dependStr)
public class SunnyCraftMain {
	@Mod.Instance(Constants.SUNCR_ID)
	public static SunnyCraftMain instance;
	@SidedProxy(clientSide="sima214.sunnycraft.client.ClientProxy",serverSide="sima214.sunnycraft.common.CommonProxy")
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