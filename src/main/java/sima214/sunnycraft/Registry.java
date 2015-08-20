package sima214.sunnycraft;

import net.minecraft.block.Block;
import sima214.core.Constants;
import sima214.core.Logger;
import sima214.core.common.WorldGenSimple;
import sima214.sunnycraft.common.blocks.LavaOre;
import cpw.mods.fml.common.registry.GameRegistry;

public class Registry {
	public static LavaOre lavaore=new LavaOre(null, 25, 46, "lavastoneore", "lavastone_ore", Block.soundTypeStone, Constants.SUNCR_ID);
	public static WorldGenSimple lavaore_gen=new WorldGenSimple(lavaore,new int[]{0}, (short)2, (short)5, (short)0, (short)20);


	public static void init() {
		GameRegistry.registerBlock(lavaore, lavaore.getUnlocalizedName());
		lavaore.registerToOreDict("oreLavastone");
	}


	public static void loadUp() {
		Logger.info("Registry has been loaded");
	}

}
