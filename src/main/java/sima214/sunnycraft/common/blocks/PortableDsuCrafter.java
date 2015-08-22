package sima214.sunnycraft.common.blocks;

import sima214.sunnycraft.common.items.PortableDeepStorageUnit;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class PortableDsuCrafter implements IRecipe {

	@Override
	public boolean matches(InventoryCrafting crafting, World world) {
		boolean resultDSU = false;
		boolean resultBlock=false;
		for(int i=0;i<crafting.getSizeInventory();i++){
			ItemStack curStack=crafting.getStackInSlot(i);
			Item item=curStack.getItem();
			if(item instanceof PortableDeepStorageUnit){
				if(resultDSU){
					return false;
				}
				resultDSU=true;
			}
			else if(item instanceof ItemBlock){
				if(resultBlock){
					return false;
				}
				resultBlock=true;
			}

		}
		return resultDSU&&resultBlock;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting crafting) {
		for(int i=0;i<crafting.getSizeInventory();i++){
			ItemStack curStack=crafting.getStackInSlot(i);
			Item item=curStack.getItem();
		}
		return null;
	}

	@Override
	public int getRecipeSize() {
		return 10;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return null;
	}

}
