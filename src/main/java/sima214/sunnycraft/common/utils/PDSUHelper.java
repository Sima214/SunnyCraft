package sima214.sunnycraft.common.utils;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import sima214.sunnycraft.common.items.PortableDeepStorageUnit;

public class PDSUHelper {
	public static boolean pushContents(ItemStack itemStack, IInventory tile, int side) {
		boolean doneSomething=false;
		ItemStack containedStack=PortableDeepStorageUnit.getStack(itemStack);
		for(int i=0;i<tile.getSizeInventory() && PortableDeepStorageUnit.getStoredAmount(itemStack)>0;i++){
			if(tile.isItemValidForSlot(i, containedStack)){
				containedStack.stackSize=Math.min(tile.getInventoryStackLimit(),PortableDeepStorageUnit.getStoredAmount(itemStack));
				ItemStack curStack=tile.getStackInSlot(i);
				if(curStack==null){
					tile.setInventorySlotContents(i, containedStack.copy());
					//ItemStack added=tile.getStackInSlot(i);//it is very possible for other mods to be able to break this and as a result items would be voided if we don't check.
					//if(added!=null){However this seems to dupe with TE's caches so it is removed for now.
					PortableDeepStorageUnit.addToStoredAmount(itemStack, -containedStack.stackSize);
					doneSomething=true;
					continue;
				}
				else if(PortableDeepStorageUnit.isItemValid(containedStack, curStack) && curStack.stackSize<tile.getInventoryStackLimit()){
					int diff=tile.getInventoryStackLimit()-curStack.stackSize;
					curStack.stackSize=tile.getInventoryStackLimit();
					PortableDeepStorageUnit.addToStoredAmount(itemStack, -diff);
					doneSomething=true;
					tile.markDirty();
				}
			}
		}
		return doneSomething;
	}
	public static boolean pullContents(ItemStack itemStack, IInventory tile, int side) {
		boolean doneSomething=false;
		ItemStack containedStack=PortableDeepStorageUnit.getStack(itemStack);
		for(int i=0;i<tile.getSizeInventory();i++){
			ItemStack curStack=tile.getStackInSlot(i);
			if(curStack!=null){
				if(PortableDeepStorageUnit.isItemValid(containedStack, curStack)){
					int transferedAmount=PortableDeepStorageUnit.addToStoredAmount(itemStack, curStack.stackSize);
					tile.decrStackSize(i, transferedAmount);
					if(transferedAmount!=0){
						doneSomething=true;
					}
				}
			}
		}
		return doneSomething;
	}

}
