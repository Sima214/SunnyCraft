package sima214.sunnycraft;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import sima214.core.common.CraftingHelper;
import cpw.mods.fml.common.event.FMLInitializationEvent;

public class Recipes {
	public static void init(FMLInitializationEvent event){
		CraftingHelper.addSmeltingRecipe(new ItemStack(Registry.lavaore), new ItemStack(Registry.lavadust), 100f);
		CraftingHelper.addTEPulverizer(16000, new ItemStack(Registry.lavaore), new ItemStack(Registry.lavadust,2), new ItemStack(Registry.lavadust), 5);
		CraftingHelper.addTEInductionSmelter(4000, new ItemStack(Registry.lavadust), new ItemStack(Items.iron_ingot, 4), new ItemStack(Registry.lavaalloy), null, 0);
	}
}
