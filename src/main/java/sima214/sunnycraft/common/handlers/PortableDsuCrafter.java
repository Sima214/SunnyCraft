package sima214.sunnycraft.common.handlers;

import sima214.sunnycraft.Registry;
import sima214.sunnycraft.common.items.PortableDeepStorageUnit;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class PortableDsuCrafter implements IRecipe {
	@Override
	public boolean matches(InventoryCrafting crafting, World world) {
		boolean hasPDSU=false;
		for(int i=0;i<crafting.getSizeInventory();i++){
			ItemStack curStack=crafting.getStackInSlot(i);
			if(curStack==null){
				continue;
			}
			Item item=curStack.getItem();
			if(item instanceof PortableDeepStorageUnit){
				if(hasPDSU)
					return false;
				hasPDSU=true;
			}
		}
		return hasPDSU;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting crafting) {
		ItemStack pdsu=null;
		ItemStack blocks=null;
		boolean clear = false;
		for(int i=0;i<crafting.getSizeInventory();i++){
			ItemStack curStack=crafting.getStackInSlot(i);
			if(curStack == null){
				continue;
			}
			Item item=curStack.getItem();
			if(item instanceof PortableDeepStorageUnit){
				pdsu=curStack;
			}
			else if(item instanceof ItemBlock){
				if(curStack.hasTagCompound() || blocks!=null)
					return null;//To avoid dupes with some mods
				blocks=curStack;
			}
			else if(item==Items.paper){
				clear = true;
			}
		}//For loop end
		if(clear && blocks==null)
			return new ItemStack(Registry.portDsu);
		else if(!clear && blocks==null)
			return PortableDeepStorageUnit.changeMode(pdsu);
		ItemStack contained=PortableDeepStorageUnit.getStack(pdsu);
		if(contained==null||PortableDeepStorageUnit.isItemValid(contained, blocks)){
			ItemStack EndStack = pdsu.copy();
			PortableDeepStorageUnit.setStack(EndStack, blocks);
			if(PortableDeepStorageUnit.addToStoredAmount(EndStack, 1) == 0)
				return null;
			return EndStack;
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
