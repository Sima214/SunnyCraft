package sima214.core;

import java.util.ArrayList;

import sima214.core.common.CommonProxy;
import sima214.core.common.OreGenHelper;
import sima214.core.common.WorldGenSimple;
import sima214.core.common.config.ConfigHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Constants.CORE_ID,name=Constants.CORE_NAME,version=Constants.CORE_VERSION)
public class SimaCoreMain {
	public static final String dependStr="required-after:"+Constants.CORE_ID+"@[" + Constants.CORE_VERSION + ",)";
	@Mod.Instance(Constants.CORE_ID)
	public static SimaCoreMain instance;
	@SidedProxy(clientSide="sima214.core.client.ClientProxy",serverSide="sima214.core.common.CommonProxy")
	public static CommonProxy proxy;
	private static ArrayList<ConfigHelper> configs=new ArrayList<ConfigHelper>(2);
	private OreGenHelper worldGen=new OreGenHelper();

	@Mod.EventHandler
	public void preinit(FMLPreInitializationEvent event)
	{
		worldGen.prepareConfig();//before everythings crashes or at least used to.
		for(ConfigHelper CH:SimaCoreMain.configs){
			CH.init();
		}
		GameRegistry.registerWorldGenerator(worldGen, 211);//I wonder what other people put as the weight parameter
	}
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
	}

	@Mod.EventHandler
	public void postinit(FMLPostInitializationEvent event)
	{
		proxy.post();
	}
	/**
	 * Registers a ConfigHelper object to be later loaded at post init.
	 * @param config
	 */
	public static void registerConfig(ConfigHelper config)
	{
		SimaCoreMain.configs.add(config);
	}
	public static void registerSimpleOre(WorldGenSimple simple)
	{
		instance.worldGen.simple.add(simple);
	}
	public static void registerVanillaOre(){}//TODO
}