package sima214.sunnycraft;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import sima214.core.Constants;
import sima214.core.Logger;
import sima214.core.common.ResourceBlock;
import sima214.core.common.WorldGenSimple;

public class Registry {
	public static ResourceBlock lavaore=new ResourceBlock(null, 3, 7, "lavastoneore", "lavastone_ore", Block.soundTypeStone, Constants.SUNCR_ID);
	public static WorldGenSimple lavaore_gen=new WorldGenSimple(lavaore,new int[]{0}, (short)2, (short)5, (short)0, (short)20);


	public static void init() {
		GameRegistry.registerBlock(lavaore, lavaore.getUnlocalizedName());
	}


	public static void loadUp() {
		lavaore.setHarvestLevel("pickaxe", 3);//Watch for tinkers?
		Logger.info("Registry has been loaded");
	}

}
