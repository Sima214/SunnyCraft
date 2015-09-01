package sima214.sunnycraft;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import sima214.core.Constants;
import sima214.core.Logger;
import sima214.core.SimaCoreMain;
import sima214.core.common.ResourceItem;
import sima214.core.common.WorldGenSimple;
import sima214.core.common.config.ConfigHelper;
import sima214.core.common.config.IConfigElement;
import sima214.core.common.config.IntConfigElement;
import sima214.sunnycraft.common.blocks.FernSpawner;
import sima214.sunnycraft.common.blocks.LavaOre;
import sima214.sunnycraft.common.items.PortableDeepStorageUnit;
import cpw.mods.fml.common.registry.GameRegistry;

public class Registry {
	//Blocks
	public static LavaOre lavaore=new LavaOre(null, 25, 46, "lavastoneore", "lavastone_ore", Block.soundTypeStone, Constants.SUNCR_ID);
	public static FernSpawner fernspawner=new FernSpawner();
	//Items
	public static ResourceItem lavadust=new ResourceItem(64, EnumRarity.uncommon, "lavadust", "lava_dust", Constants.SUNCR_ID,  null);
	public static ResourceItem lavaalloy=new ResourceItem(64, EnumRarity.uncommon, "lavaalloy", "lava_alloy", Constants.SUNCR_ID,  null);
	public static PortableDeepStorageUnit portDsu=new PortableDeepStorageUnit();
	//Worldgen
	static final WorldGenSimple lavaore_gen=new WorldGenSimple(lavaore,new int[]{0}, (short)2, (short)5, (short)0, (short)20);
	//Configuration
	private static ConfigHelper config=new ConfigHelper(Constants.SUNCR_ID);
	public static IntConfigElement portDsu_size=new IntConfigElement("Portable DSU settings", "size", "Sets the max number of items it can hold", Integer.MAX_VALUE);
	public static IntConfigElement portDsu_tickrate=new IntConfigElement("Portable DSU settings", "tick", "How often should one pdsu check the players's inventory.\nSet to 0 to disable.", 10);
	public static void init() {
		GameRegistry.registerBlock(lavaore, lavaore.getUnlocalizedName());
		lavaore.registerToOreDict("oreLavastone");
		GameRegistry.registerBlock(fernspawner, fernspawner.getUnlocalizedName());
		GameRegistry.registerItem(lavadust, lavadust.getUnlocalizedName());
		lavadust.addToOreDict("dustLavastone");
		GameRegistry.registerItem(lavaalloy, lavaalloy.getUnlocalizedName());
		lavaalloy.addToOreDict("alloyLavastone");
		GameRegistry.registerItem(portDsu, portDsu.getUnlocalizedName());
	}


	public static void loadUp() {
		SimaCoreMain.registerConfig(config);
		config.addElements(new IConfigElement[] {portDsu_size,portDsu_tickrate});
		Logger.trace("Registry has been loaded");
	}

}
