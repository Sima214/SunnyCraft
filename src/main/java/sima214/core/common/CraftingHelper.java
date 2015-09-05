package sima214.core.common;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;


public class CraftingHelper {
	public static void registerShapedRecipe(ItemStack output, Object... params){
		GameRegistry.addShapedRecipe(output, params);
	};
	public static void registerShapelessRecipe(ItemStack output, Object... params){
		GameRegistry.addShapelessRecipe(output, params);
	};
	public static void registerRecipe(IRecipe recipe){
		GameRegistry.addRecipe(recipe);
	};
	public static void addSmeltingRecipe(ItemStack input, ItemStack output, float xp){
		GameRegistry.addSmelting(input, output, xp);
	}
	public static void addTEPulverizer(int energy,ItemStack in,ItemStack out,ItemStack bonus,int bonusChance){
		if(Loader.isModLoaded("ThermalExpansion")){
			NBTTagCompound data=new NBTTagCompound();
			data.setInteger("energy", energy);
			data.setTag("input", in.writeToNBT(new NBTTagCompound()));
			data.setTag("primaryOutput", out.writeToNBT(new NBTTagCompound()));

			if (bonus != null) {
				data.setTag("secondaryOutput", bonus.writeToNBT(new NBTTagCompound()));
				data.setInteger("secondaryChance", bonusChance);
			}

			FMLInterModComms.sendMessage("ThermalExpansion", "PulverizerRecipe", data);
		}

	};
	public static void addTEInductionSmelter(int energy, ItemStack in1, ItemStack in2, ItemStack out, ItemStack bonus, int bonusChance){
		if(Loader.isModLoaded("ThermalExpansion")){
			NBTTagCompound data=new NBTTagCompound();
			data.setInteger("energy", energy);
			data.setTag("primaryInput", in1.writeToNBT(new NBTTagCompound()));
			data.setTag("secondaryInput", in2.writeToNBT(new NBTTagCompound()));
			data.setTag("primaryOutput", out.writeToNBT(new NBTTagCompound()));

			if (bonus != null) {
				data.setTag("secondaryOutput", bonus.writeToNBT(new NBTTagCompound()));
				data.setInteger("secondaryChance", bonusChance);
			}

			FMLInterModComms.sendMessage("ThermalExpansion", "SmelterRecipe", data);
		}
	};
}
