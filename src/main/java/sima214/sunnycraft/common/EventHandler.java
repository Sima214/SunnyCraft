package sima214.sunnycraft.common;

import sima214.sunnycraft.common.items.PortableDeepStorageUnit;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;

public class EventHandler {
public EventHandler() {
	MinecraftForge.EVENT_BUS.register(this);
}
public void PDSUPickupHandler(ItemPickupEvent event){
	ItemStack toPickup=event.pickedUp.getEntityItem();
	for(int i=0;i<event.player.inventory.getSizeInventory();i++){
		ItemStack curItem=event.player.inventory.getStackInSlot(i);
		if(curItem==null){
			continue;
		}
		Item item=curItem.getItem();
		if(item instanceof PortableDeepStorageUnit){
			PortableDeepStorageUnit pdsu=(PortableDeepStorageUnit) item;
			ItemBlock containedItem=pdsu.getStack(curItem);
			if(containedItem == toPickup.getItem()){
				int transferedAmount=pdsu.addToStoredAmount(curItem, toPickup.stackSize);
				if(transferedAmount==toPickup.stackSize){
					event.pickedUp.setDead();
					event.setCanceled(true);
				}
				else
				{
					toPickup.stackSize-=transferedAmount;
					event.pickedUp.setEntityItemStack(toPickup);//You might need to do this as the itemStack is stored in a Watchable thingy or you might not
				}
			}
		}
	}
}
}
