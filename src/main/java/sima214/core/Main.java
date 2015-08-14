package sima214.core;

import java.util.ArrayList;

import net.minecraft.item.EnumRarity;
import sima214.core.common.CItem;
import sima214.core.common.CommonProxy;
import sima214.core.common.config.ConfigHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Constants.CORE_ID,name=Constants.CORE_NAME,version=Constants.CORE_VERSION)
public class Main {
	@Mod.Instance(Constants.CORE_ID)
	public static Main instance;
	@SidedProxy(clientSide="sima214.core.client.ClientProxy",serverSide="sima214.core.common.CommonProxy")
	public static CommonProxy proxy;
	private ArrayList<ConfigHelper> configs=new ArrayList<ConfigHelper>(2);
	public CItem citem=new CItem(1, EnumRarity.common, "citem", "citem", Constants.CORE_ID, new String[0]);
	@Mod.EventHandler
	public void preinit(FMLPreInitializationEvent event)
	{
		for(ConfigHelper CH:configs){
			CH.init();
		}
	}
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		GameRegistry.registerItem(citem, citem.getUnlocalizedName());
		EntityRegistry.registerModEntity(TestEntity.class, "Sun", 0, this, 128, 2, true);
		proxy.init();
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
		instance.configs.add(config);
	}
}