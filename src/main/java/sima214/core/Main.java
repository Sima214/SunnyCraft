package sima214.core;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import sima214.core.common.CommonProxy;
import sima214.core.common.ResourceBlock;
import sima214.core.common.ResourceItem;
import sima214.core.common.config.ConfigHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Constants.CORE_ID,name=Constants.CORE_NAME,version=Constants.CORE_VERSION)
public class Main {
	@Mod.Instance(Constants.CORE_ID)
	public static Main instance;
	@SidedProxy(clientSide="sima214.core.client.ClientProxy",serverSide="sima214.core.common.CommonProxy")
	public static CommonProxy proxy;
	private ArrayList<ConfigHelper> configs=new ArrayList<ConfigHelper>(2);
	public ResourceBlock testBlock=new ResourceBlock(new float[]{0f,0f,0f,1f,0.25f,1f},0f, 1f, "tester", "random_bitmap", Block.soundTypeGrass, "blockRandom", Constants.CORE_ID);
	public ResourceItem testItem=new ResourceItem(16, EnumRarity.epic, "testing", "random_item", "glowstone", Constants.CORE_ID);
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
		GameRegistry.registerBlock(testBlock,testBlock.getUnlocalizedName());
		GameRegistry.registerItem(testItem, testItem.getUnlocalizedName());
	}

	@Mod.EventHandler
	public void postinit(FMLPostInitializationEvent event)
	{
	}
	public static void registerConfig(ConfigHelper config)
	{
		instance.configs.add(config);
	}
}