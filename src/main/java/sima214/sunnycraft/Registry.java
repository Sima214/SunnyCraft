package sima214.sunnycraft;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import sima214.core.Constants;
import sima214.core.Logger;
import sima214.core.common.ResourceItem;
import sima214.core.common.WorldGenSimple;
import sima214.sunnycraft.common.blocks.LavaOre;
import cpw.mods.fml.common.registry.GameRegistry;

public class Registry {
	public static LavaOre lavaore=new LavaOre(null, 25, 46, "lavastoneore", "lavastone_ore", Block.soundTypeStone, Constants.SUNCR_ID);
	static final WorldGenSimple lavaore_gen=new WorldGenSimple(lavaore,new int[]{0}, (short)2, (short)5, (short)0, (short)20);
	public static ResourceItem lavadust=new ResourceItem(64, EnumRarity.uncommon, "lavadust", "lava_dust", Constants.SUNCR_ID,  new String[0]);
	public static ResourceItem lavaalloy=new ResourceItem(64, EnumRarity.uncommon, "lavaalloy", "lava_alloy", Constants.SUNCR_ID,  new String[0]);


	public static void init() {
		GameRegistry.registerBlock(lavaore, lavaore.getUnlocalizedName());
		lavaore.registerToOreDict("oreLavastone");
		GameRegistry.registerItem(lavadust, lavadust.getUnlocalizedName());
		lavadust.addToOreDict("dustLavastone");
		GameRegistry.registerItem(lavaalloy, lavaalloy.getUnlocalizedName());
		lavaalloy.addToOreDict("alloyLavastone");
	}


	public static void loadUp() {
		Logger.trace("Registry has been loaded");
	}

}
