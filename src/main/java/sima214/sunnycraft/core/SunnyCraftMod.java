package sima214.sunnycraft.core;

import sima214.sunnycraft.client.Resourses;
import sima214.sunnycraft.entities.SunEntity;
import sima214.sunnycraft.items.SunSpawner;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Constants.MODID,name="SunnyCraft",version=Constants.VERSION)
public class SunnyCraftMod {
	@Mod.Instance("sunnycraft")
	public static SunnyCraftMod instance;
	@SidedProxy(clientSide="sima214.sunnycraft.client.ClientProxy",serverSide="sima214.sunnycraft.core.CommonProxy")
	public static CommonProxy proxy;
	//Objects to be registered
	public static final SunSpawner sunSpawner=new SunSpawner();
	//Registration-Startup events
	@Mod.EventHandler
	public void preinit(FMLPreInitializationEvent event)
	{
		LogHelper.info("Start of preinit");
		LogHelper.info("End of preinit");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		LogHelper.info("Start of init");
		GameRegistry.registerItem(sunSpawner, sunSpawner.getUnlocalizedName());
		EntityRegistry.registerModEntity(SunEntity.class, "Sun", 0, this, 128, 2, true);
		proxy.RegisterRenders();
		LogHelper.info("End of init");
	}

	@Mod.EventHandler
	public void postinit(FMLPostInitializationEvent event)
	{
		LogHelper.info("Start of postinit");
		proxy.init();
		LogHelper.trace(Resourses.getModel().length);
		LogHelper.info("End of postinit");
	}

}
