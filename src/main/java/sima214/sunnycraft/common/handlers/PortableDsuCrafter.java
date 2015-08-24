package sima214.sunnycraft.common.handlers;

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
		boolean hasPDSU=false;
		ItemBlock stored = null;
		Item inTable = null;
		for(int i=0;i<crafting.getSizeInventory();i++){
			ItemStack curStack=crafting.getStackInSlot(i);
			if(curStack==null){
				continue;
			}
			Item item=curStack.getItem();
			if(item instanceof PortableDeepStorageUnit){
				if(stored!=null)
					return false;
				hasPDSU=true;
				PortableDeepStorageUnit pdsu=(PortableDeepStorageUnit) item;
				stored=pdsu.getStack(curStack);
			}
			else if(item instanceof ItemBlock){
				if(inTable!=null)
					return false;
				inTable=item;
			} else
				return false;
		}
		return hasPDSU&& inTable != null && (stored==null||stored.equals(inTable));
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting crafting) {
		ItemStack pdsu=null;
		ItemStack blocks=null;
		ItemStack EndStack;
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
				blocks=curStack;
			}
		}
		EndStack=pdsu.copy();
		PortableDeepStorageUnit ItemPDSU = (PortableDeepStorageUnit) pdsu.getItem();
		ItemPDSU.setStack(EndStack, blocks);
		if(ItemPDSU.addToStoredAmount(EndStack, 1) == 0){
			return null;
		}
		return EndStack;
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
