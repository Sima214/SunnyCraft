package sima214.sunnycraft;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import sima214.core.Constants;
import sima214.core.common.CraftingHelper;
import sima214.sunnycraft.common.handlers.PortableDsuCrafter;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {
	public static void init(FMLInitializationEvent event){
		CraftingHelper.addSmeltingRecipe(new ItemStack(Registry.lavaore), new ItemStack(Registry.lavadust), 100f);
		CraftingHelper.addTEPulverizer(16000, new ItemStack(Registry.lavaore), new ItemStack(Registry.lavadust,2), new ItemStack(Registry.lavadust), 5);
		CraftingHelper.addTEInductionSmelter(4000, new ItemStack(Registry.lavadust), new ItemStack(Items.iron_ingot, 4), new ItemStack(Registry.lavaalloy), null, 0);
		GameRegistry.addRecipe(new PortableDsuCrafter());
		RecipeSorter.register(Constants.SUNCR_ID+":PDSUHandler", PortableDsuCrafter.class, Category.SHAPELESS, "");
	}
}
