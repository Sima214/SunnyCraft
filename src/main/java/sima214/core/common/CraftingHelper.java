package sima214.core.common;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import cpw.mods.fml.common.registry.GameRegistry;


public class CraftingHelper {
	public static void registerShapedRecipe(ItemStack output, Object params){
		GameRegistry.addShapedRecipe(output, params);
	};
	public static void registerShapelessRecipe(ItemStack output, Object params){
		GameRegistry.addShapelessRecipe(output, params);
	};
	public static void registerRecipe(IRecipe recipe){
		GameRegistry.addRecipe(recipe);
	};
	public static void addSmeltingRecipe(ItemStack input, ItemStack output, float xp){
		GameRegistry.addSmelting(input, output, xp);
	}
	public static void addTEPulverizer(){};//I would have to find documentantion on this, or ask someone.
	public static void addTEInductionSmelter(){};
}
